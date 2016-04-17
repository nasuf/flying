package com.flying.web.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;

import com.flying.web.controller.FileController;

public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    /**
     * 
     * 单个文件上传
     * 
     * @param is
     * 
     * @param fileName
     * 
     * @param filePath
     */

    public static void upFile(InputStream is, String fileName, String filePath) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File f = new File(filePath + "/" + fileName);
        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(f);
            bos = new BufferedOutputStream(fos);
            byte[] bt = new byte[4096];
            int len;
            while ((len = bis.read(bt)) > 0) {
                bos.write(bt, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bos) {
                    bos.close();
                    bos = null;
                }
                if (null != fos) {
                    fos.close();
                    fos = null;
                }
                if (null != is) {
                    is.close();
                    is = null;
                }
                if (null != bis) {
                    bis.close();
                    bis = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解决浏览器附件名乱码问题
     * @param filename
     * @param browserAgent
     * @return
     */
    public static String getFileNameFromBrowser(String filename, String browserAgent) {

        if (browserAgent.contains("Firefox")) {
            // base64
            BASE64Encoder encoder = new BASE64Encoder();
            try {
                return "=?utf-8?B?" + encoder.encode(filename.getBytes("utf-8")) + "?=";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        // urlencoder ie chrome safari
        try {
            return URLEncoder.encode(filename, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("附件名错误");
        }
    }

    public static long getFileSize(String filePath, HttpServletRequest request) throws IOException {
        /*
         * String filePath = request .getSession() .getServletContext() .getRealPath( "/WEB-INF/upload/0acf7b008de64551b45e40dd7bac5ac4-uploadedIn-20151224160511-uploadedBy-nasuf-named-eclipse黑色主题插件.zip");
         */
        FileChannel fc = null;
        try {
            File f = new File(filePath);
            if (f.exists() && f.isFile()) {
                FileInputStream fis = new FileInputStream(f);
                fc = fis.getChannel();
            } else {
                LOGGER.info("file doesn't exist or is not a file");
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(e.toString());
        } catch (IOException e) {
            LOGGER.error(e.toString());
        } finally {
            if (null != fc) {
                try {
                    fc.close();
                } catch (IOException e) {
                    LOGGER.error(e.toString());
                }
            }
        }
        return fc.size();
    }
}
