package com.flying.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.flying.web.utils.DateFormatUtils;
import com.flying.web.utils.FileUtil;

@RequestMapping("file")
@Controller
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    /**
     * 弹出文件上传窗口
     * 
     * @return
     */
    @RequestMapping("toUpload")
    public ModelAndView openFileUpload(@RequestParam("uploaderName") String uploaderName) {
        ModelAndView mv = new ModelAndView("fileUpload");
        mv.addObject("uploaderName", uploaderName);
        return mv;
    }

    /**
     * 查看文件列表
     * @param uploaderName
     * @return
     */
    @RequestMapping("checkFiles")
    public ModelAndView checkFiles(@RequestParam("uploaderName") String uploaderName,
            @RequestParam("repoName") String repoName, HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("fileView");
        String repoPathStr = request.getSession().getServletContext()
                .getRealPath("/WEB-INF/upload/" + repoName + "/");
        File f = null;
        f = new File(repoPathStr); // 新建文件实例
        File[] list = f.listFiles(); /* 此处获取文件夹下的所有文件 */
        // List<String> fileNameList = new ArrayList<String>();
        Map<String, String> fileNameMap = new HashMap<String, String>();
        if (null == list) {
            fileNameMap = null;
        } else {
            for (int i = 0; i < list.length; i++) {
                String realName = list[i].getName().split("-")[list[i].getName().split("-").length - 1];
                fileNameMap.put(list[i].getName(), realName);
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info(uploaderName + " 获取到该文件: " + realName);
                }
                // System.out.println(list[i].getName());
            }
        }

        mv.addObject("fileNameMap", fileNameMap);
        mv.addObject("repoName", repoName);
        mv.addObject("uploaderName", uploaderName);
        return mv;
    }

    /**
     * 文件上传
     * 
     * @param file
     * @param uploaderName
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String uploadFile(@RequestParam("file") CommonsMultipartFile file,
            @RequestParam("uploaderName") String uploaderName,
            @RequestParam("toCommonRepo") Boolean toCommonRepo, HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String parentPathStr = null;
        String filePath = null;
        String fileName = file.getOriginalFilename();
        String fileType = null;
        String fileNameUploaded = null;
        String currentTimeStr = DateFormatUtils.parseDateToStringUnsplited(System.currentTimeMillis());
        // 为了避免文件名重复，在文件名前加UUID
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // String uuidFileName = uuid + fileName;

        fileNameUploaded = uuid + "-uploadedIn-" + currentTimeStr + "-uploadedBy-" + uploaderName + "-named-"
                + fileName;
        if (true == toCommonRepo) {
            parentPathStr = request.getSession().getServletContext()
                    .getRealPath("/WEB-INF/upload/commonRepo");
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(uploaderName + "正在上传该文件: " + fileName + " 到公共库");
            }
        } else {
            // 设置文件保存的本地路径
            filePath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
            // fileName = file.getOriginalFilename();
            fileType = fileName.split("[.]")[1];

            File parentPath = new File(filePath + "/" + uploaderName);
            if (!parentPath.exists())
                parentPath.mkdirs();
            parentPathStr = request.getSession().getServletContext()
                    .getRealPath("/WEB-INF/upload/" + uploaderName + "");
            File f = new File(parentPath + "/" + uuid + "." + fileType);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(uploaderName + "正在上传该文件: " + fileName + " 到私人库");
            }
        }

        // 将文件保存到服务器
        FileUtil.upFile(file.getInputStream(), fileNameUploaded, parentPathStr);
        return fileNameUploaded;
    }

    /**
     * 文件下载
     * @param fileName 文件名称
     * @param personalRepoName 私人库名称
     * @param request
     * @param response
     */
    @RequestMapping("downloadFiles")
    public void downloadFile(@RequestParam("fileName") String fileName,
            @RequestParam("repoName") String repoName, HttpServletRequest request,
            HttpServletResponse response) {
        // 将文件名解码
        try {
            // fileName = java.net.URLDecoder.decode(fileName, "utf-8");
            fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        System.out.println("filename=" + fileName);
        System.out.println("浏览器类型: " + request.getHeader("user-agent"));
        String path = request.getSession().getServletContext()
                .getRealPath("/WEB-INF/upload/" + repoName + "/" + fileName + "");
        File file = new File(path);
        if (file.exists()) {
            // 服务器通知浏览器下载 附件框形式 附件名 response 通知浏览器
            String filename = file.getName();
            filename = FileUtil.getFileNameFromBrowser(filename, request.getHeader("user-agent"));

            // 文件对应下载mime类型
            response.setContentType(request.getSession().getServletContext().getMimeType(filename));
            // 将文件名修改为原文件名
            filename = filename.split("-")[filename.split("-").length - 1];

            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            // 输出流下载
            try {
                InputStream in = new FileInputStream(file);
                int len = 0;
                byte[] bytes = new byte[1024 * 8];
                while ((len = in.read(bytes)) != -1) {
                    response.getOutputStream().write(bytes, 0, len);
                }
                // 关闭流
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
