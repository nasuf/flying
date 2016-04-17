package com.flying.sso.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flying.sso.pojo.UserInfo;
import com.flying.sso.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin")
    public String toLoginPage() {
        return "login";
    }

    @RequestMapping("/toRegist")
    public String toRegistPage() {
        return "regist";
    }

    /**
     * 登陆信息检查
     * 
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestParam("u") String username, @RequestParam("p") String password) {
        System.out.println("username=" + username + " password=" + password);
        // 密码加密后查询
        password = DigestUtils.md5Hex(password);
        try {
            String ticket = this.userService.login(username, password);
            if (null == ticket) {
                System.out.println("用户不存在");
                return ResponseEntity.ok(null);
            } else {
                System.out.println("用户存在");
                return ResponseEntity.ok(ticket);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 用户注册
     * 
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public ResponseEntity<String> regist(@RequestParam("userName") String userName,
            @RequestParam("password") String password, @RequestParam("gender") int gender,
            @RequestParam("age") int age, @RequestParam("degree") String degree,
            @RequestParam("city") String city, @RequestParam("phone") String phone) {
        System.out.println("regist entered");
        UserInfo param = new UserInfo(userName, password, gender, age, phone, city, degree);
        // 密码加密
        param.setPassword(DigestUtils.md5Hex(param.getPassword()));
        Boolean bool = this.userService.regist(param);
        if (bool == true) {
            System.out.println("注册成功");
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } else {
            System.out.println("注册失败");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * 检查用户登陆是否失效
     * 
     * @param redisKey
     * @return
     */
    @RequestMapping(value = "checkIfOutOfDate", method = RequestMethod.POST)
    public ResponseEntity<String> checkIfOutOfDate(@RequestParam("redis_key") String redisKey) {
        System.out.println("redisKey = " + redisKey);
        String onLineUserInfo = this.userService.checkIfOutOfDate(redisKey);
        if (null == onLineUserInfo) {
            System.out.println("用户登陆失效");
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            System.out.println("用户登陆有效，可以访问");
            return ResponseEntity.status(HttpStatus.OK).body(onLineUserInfo);
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ResponseEntity<String> logout(@RequestParam("onLineUserName") String onLineUserName,
            @RequestParam("redis_key") String redisKey) {
        System.out.println("用户正在退出");
        Boolean bool = this.userService.logout(onLineUserName, redisKey);
        if (bool) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * 检查新注册用户名是否可用
     * 
     * @param userName
     * @return
     */
    @RequestMapping(value = "/checkUserName", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkUserName(@RequestParam("userName") String userName) {
        Boolean bool = this.userService.checkUserName(userName);
        Map<String, Object> userNameValidation = new HashMap<String, Object>();
        if (bool == true) {
            System.out.println("该用户名可用");
            userNameValidation.put("validated", true);
        } else {
            System.out.println("该用户名不可用");
            userNameValidation.put("validated", false);
        }
        userNameValidation.put("result", "success");
        return userNameValidation;
    }

}
