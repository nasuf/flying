package com.flying.web.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flying.common.bean.HttpResult;
import com.flying.common.service.ApiService;
import com.flying.web.pojo.Msg;
import com.flying.web.utils.DateFormatUtils;

@Service
public class UserService {

    @Autowired
    private ApiService apiService;

    @Value("${SSO_FLYING_URL}")
    public String SSO_FLYING_URL;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public String doLogin(String userName, String password) {

        String url = SSO_FLYING_URL + "/user/login";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("u", userName);
        param.put("p", password);
        try {
            HttpResult httpResult = this.apiService.doPost(url, param);
            System.out.println("code = " + httpResult.getCode());
            if (httpResult.getCode() == 200) {
                String body = httpResult.getBody();
                System.out.println("body = " + body);
                if (null == body) {
                    // 登陆失败
                    return null;
                } else {
                    // 登陆成功
                    return body;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean doRegist(String userName, String password, int gender, int age, String phone, String city,
            String degree) {

        String url = SSO_FLYING_URL + "/user/regist";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userName", userName);
        param.put("password", password);
        param.put("gender", gender);
        param.put("age", age);
        param.put("degree", degree);
        param.put("city", city);
        param.put("phone", phone);
        try {
            HttpResult httpResult = this.apiService.doPost(url, param);
            System.out.println("code = " + httpResult.getCode());
            if (httpResult.getCode() == 201) {
                // 注册成功
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 注册失败
        return false;
    }

    /**
     * 判断用户是否登陆或者登陆时间是否超时
     * 
     * @param cookieValue
     * @return
     */
    public String checkIfOutOfDate(String cookieValue) {
        String url = SSO_FLYING_URL + "/user/checkIfOutOfDate";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("redis_key", cookieValue);
        try {
            HttpResult httpResult = this.apiService.doPost(url, param);
            if (httpResult.getCode() == 200) {
                String body = httpResult.getBody();
                if (null == body)
                    return null;
                else {
                    JsonNode node = MAPPER.readTree(body);
                    System.out.println("node = " + node);
                    return node.get("userName").asText();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行用户退出
     * 
     * @param cookieValue
     * 
     * @param cookieValue
     * @return
     */
    public Boolean doLogout(String onLineUserName, String cookieValue) {
        String url = SSO_FLYING_URL + "/user/logout";
        Map<String, Object> param = new HashMap<String, Object>();
        System.out.println("web-onlineUserName=" + onLineUserName);
        param.put("onLineUserName", onLineUserName);
        param.put("redis_key", cookieValue);
        try {
            HttpResult httpResult = this.apiService.doPost(url, param);
            System.out.println("web:正在退出 code = " + httpResult.getCode());
            if (httpResult.getCode() == 200) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存用户弹幕信息并返回
     * 
     * @param receptor
     * @param sender
     * @param msg
     * @return
     */
    public Msg saveMsg(String receptor, String sender, String msg) {
        Long sendDate = System.currentTimeMillis();
        Msg newMsg = new Msg();
        newMsg.setMsg(msg);
        newMsg.setReceptor(receptor);
        newMsg.setSender(sender);
        newMsg.setSendDate(sendDate);
        newMsg.setSendDate4Display(DateFormatUtils.parseDateToString(sendDate));
        System.out.println("正在保存弹幕信息：" + newMsg);
        return newMsg;
    }
}
