package com.flying.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flying.web.pojo.Msg;
import com.flying.web.service.UserService;
import com.flying.web.utils.CookieUtils;

@RequestMapping(value = "user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public static final String FLYING_TICKET = "FLYING_TICKET";

    public static String cookieValue = null;

    /**
     * 跳转到登陆页面
     * 
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLoginPage() {
        return "login";
    }

    /**
     * 跳转到到注册页面
     * 
     * @return
     */
    @RequestMapping("/toRegist")
    public String toRegistPage() {
        return "regist";
    }

    /**
     * 跳转到主页
     * 
     * @return
     */
    @RequestMapping("/toIndex")
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("userName") String param) {
        ModelAndView mv = new ModelAndView();
        // cookieValue = CookieUtils.getCookieValue(request, FLYING_TICKET);
        System.out.println("cookieValue = " + cookieValue);
        if (null == CookieUtils.getCookieValue(request, FLYING_TICKET)) {
            // cookie失效或不存在后，执行登出
            logout(request, response, param);
            mv.setViewName("login");
            return mv;
        }
        cookieValue = CookieUtils.getCookieValue(request, FLYING_TICKET);
        String userName = this.userService.checkIfOutOfDate(cookieValue);
        if (null != userName) {
            System.out.println("正在登陆的用户名为：" + userName);
            mv.setViewName("index");
            mv.addObject("onLine_userName", userName);
        } else {
            mv.setViewName("login");
        }
        return mv;
    }

    /**
     * 执行退出
     * 
     * @param request
     * @param response
     * @param onLineUserName
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("onLineUserName") String onLineUserName) {
        ModelAndView mv = new ModelAndView("login");
        // String cookieValue = CookieUtils.getCookieValue(request, FLYING_TICKET);
        System.out.println("用户" + onLineUserName + "正在退出");
        Boolean bool = this.userService.doLogout(onLineUserName, cookieValue);
        if (bool) {
            Cookie cookie = new Cookie("FLYING_TICKET", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "login";
        }
        return "index";
    }

    /**
     * 执行登陆
     * 
     * @param userName
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "doLogin", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> doLogin(@RequestParam("userName") String userName,
            @RequestParam("password") String password, HttpServletResponse response) {
        String ticket = this.userService.doLogin(userName, password);
        System.out.println("ticket=" + ticket);
        Map<String, Object> result = new HashMap<String, Object>(1);
        if (null == ticket) {
            // 登录失败
            result.put("status", "500");
        } else {
            // 成功
            result.put("status", "200");

            // 将ticket写入到cookie中
            // cookie是会话级别
            // CookieUtils.setCookie(request, response, FLYING_TICKET, ticket);
            Cookie cookie = new Cookie("FLYING_TICKET", ticket);
            cookie.setMaxAge(60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return result;
    }

    /**
     * 执行注册
     * 
     * @param userName
     * @param password
     * @param gender
     * @param age
     * @param degree
     * @param city
     * @param phone
     * @return
     */
    @RequestMapping(value = "doRegist", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> doRegist(@RequestParam("userName") String userName,
            @RequestParam("password") String password, @RequestParam("gender") int gender,
            @RequestParam("age") int age, @RequestParam("degree") String degree,
            @RequestParam("city") String city, @RequestParam("phone") String phone) {
        System.out.println("doRegist Entered");
        Boolean flag = this.userService.doRegist(userName, password, gender, age, phone, city, degree);
        Map<String, Object> result = new HashMap<String, Object>();
        System.out.println("flag = " + flag);
        if (flag) {
            result.put("status", "200");
        } else {
            result.put("status", "500");
        }
        return result;
    }

    @RequestMapping("/sendMsg")
    @ResponseBody
    public Map<String, Object> sendMsg(@RequestParam("receptor") String receptor,
            @RequestParam("msg") String msg, @RequestParam("sender") String sender) {
        System.out.println("receptor=" + receptor + " msg=" + msg);
        Msg newMsg = this.userService.saveMsg(receptor, sender, msg);
        Map<String, Object> msgResult = new HashMap<String, Object>();
        msgResult.put("status", "200");
        msgResult.put("newMsg", newMsg.getMsg());
        return msgResult;
    }

    /**
     * 文件上傳
     * 
     * @param request
     * @param response
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */

    /*
     * @RequestMapping("/fileUpload") public String upload2(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException { // 创建一个通用的多部分解析器 CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession() .getServletContext()); // 判断 request 是否有文件上传,即多部分请求 if (multipartResolver.isMultipart(request)) { // 转换成多部分request MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request; // 取得request中的所有文件名 Iterator<String> iter = multiRequest.getFileNames(); while (iter.hasNext()) { // 记录上传过程起始时的时间，用来计算上传时间 int pre = (int) System.currentTimeMillis(); // 取得上传文件 MultipartFile file = multiRequest.getFile(iter.next()); if (file != null) { // 取得当前上传文件的文件名称 String myFileName = file.getOriginalFilename(); // 如果名称不为“”,说明该文件存在，否则说明该文件不存在 if (myFileName.trim() != "") { System.out.println(myFileName); // 重命名上传后的文件名 String fileName = "uploaded-" + file.getOriginalFilename(); // 定义上传路径 // String path = "H:/" + fileName; String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload"); File localFile = new File(path, fileName); file.transferTo(localFile); } } // 记录上传该文件后的时间 int finaltime = (int) System.currentTimeMillis(); System.out.println(finaltime - pre); }
     * 
     * } return "index"; }
     */

}
