package com.flying.sso.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flying.common.service.RedisService;
import com.flying.sso.mapper.UserMapper;
import com.flying.sso.pojo.UserInfo;
import com.flying.sso.utils.DateFormatUtils;
import com.github.abel533.entity.Example;

@Service
public class UserService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserMapper userMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private static int USER_STATUS_ONLINE = 1; // 在线

    private static int USER_STATUS_OFFLINE = 0; // 离线

    private static int USER_STATUS_FREEZED = -1; // 冻结

    private static int USER_STATUS_LOGOFF = -2; // 注销

    private static final String REDIS_TICKET = "TICKET_";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 用户登陆
     * 
     * @param userName
     * @param password
     * @return
     * @throws JsonProcessingException
     */
    public String login(String userName, String password) throws JsonProcessingException {

        /*
         * UserInfo userInfo = new UserInfo(); userInfo.setId(1L); userInfo.setUserName(userName);
         * userInfo.setPassword(password); userInfo.setGender(1); userInfo.setAge(22);
         * 
         * UserInfo temp = this.userMapper.selectOne(userInfo);
         */

        Example example = new Example(UserInfo.class);
        example.createCriteria().andEqualTo("userName", userName).andEqualTo("password", password);
        List<UserInfo> list = this.userMapper.selectByExample(example);
        if (list.size() <= 0) {
            return null;
        } else {
            UserInfo param = list.get(0);
            // param.setLastLoginTime(param.getCurrentLoginTime()); // 设置上次登录时间(将当前的本次登陆时间设置为上次登陆时间)
            param.setCurrentLoginTime(System.currentTimeMillis()); // 设置本次登陆时间为当前系统时间
            param.setBonus(param.getBonus() + 10); // 用户积分加10
            param.setStatus(USER_STATUS_ONLINE); // 用户状态设置为在线
            this.userMapper.updateByExampleSelective(param, example);

            String ticket = DigestUtils.md5Hex(System.currentTimeMillis() + userName);
            System.out.println("sso-service:ticket = " + ticket);
            this.redisService.set(REDIS_TICKET + ticket, MAPPER.writeValueAsString(list.get(0)), 3600);
            System.out.println(list.get(0));
            return ticket;
        }

        /*
         * UserInfo param = new UserInfo(); param.setUserName(userName); UserInfo user =
         * this.userMapper.selectOne(param); if (!StringUtils.equals(user.getPassword(),
         * DigestUtils.md5Hex(password))) { // 登录失败 return null; } else { String ticket =
         * DigestUtils.md5Hex(System.currentTimeMillis() + userName);
         * this.redisService.set(REDIS_TICKET + ticket, MAPPER.writeValueAsString(user), 3600);
         * System.out.println(user); return true; }
         */

    }

    /**
     * 用户注册
     * 
     * @param userInfo
     * @return
     */
    public Boolean regist(UserInfo userInfo) {

        Example example = new Example(UserInfo.class);
        example.createCriteria().andEqualTo("userName", userInfo.getUserName());
        List<UserInfo> list = this.userMapper.selectByExample(example);
        if (list.size() > 0)
            return false;
        else {
            try {
                // 设置用户注册时间
                userInfo.setRegistTime(DateFormatUtils.parseDateToMillis(new Date()));
                // 设置用户上一次登陆时间
                userInfo.setLastLoginTime(0L);
                // 设置用户总在线时长
                userInfo.setTotalOnlineTime(0L);
                // 设置用户本次登录时间
                userInfo.setCurrentLoginTime(0L);
                // 设置用户状态-离线
                userInfo.setStatus(USER_STATUS_OFFLINE);
                // 设置用户积分
                userInfo.setBonus(10L);
                // 设置用户等级
                userInfo.setLevel("Level One");

            } catch (ParseException e) {
                e.printStackTrace();
                LOGGER.error("注册日期转换异常");
            }
            this.userMapper.insert(userInfo);
            return true;
        }
    }

    /**
     * 检查用户名是否可用
     * 
     * @param userName
     * @return
     */
    public Boolean checkUserName(String userName) {
        Example example = new Example(UserInfo.class);
        example.createCriteria().andEqualTo("userName", userName);
        List<UserInfo> list = this.userMapper.selectByExample(example);
        if (list.size() > 0)
            return false;
        else
            return true;
    }

    /**
     * 检查用户登陆是否失效
     * 
     * @param redisKey
     * @return
     */
    public String checkIfOutOfDate(String redisKey) {
        String redisValue = this.redisService.get(REDIS_TICKET + redisKey);
        // 重新设置过期时间为1小时
        this.redisService.expire(REDIS_TICKET + redisKey, 3600);
        System.out.println("redisValue = " + redisValue);
        if (null == redisValue)
            return null;
        else
            return redisValue;
    }

    /**
     * 执行用户退出
     * 
     * @param redisKey
     * 
     * @param redisKey
     * @return
     */
    public Boolean logout(String onLineUserName, String redisKey) {
        // 获取正在退出的用户名
        // String userName = MAPPER.readTree(redisValue).get("userName").asText();
        Example example = new Example(UserInfo.class);
        example.createCriteria().andEqualTo("userName", onLineUserName);
        List<UserInfo> list = this.userMapper.selectByExample(example);
        if (list.size() > 0) {
            UserInfo param = list.get(0);
            System.out.println("正在退出的用户：" + param);
            param.setStatus(USER_STATUS_OFFLINE); // 设置用户状态为离线
            param.setTotalOnlineTime(param.getTotalOnlineTime() + System.currentTimeMillis()
                    - param.getCurrentLoginTime()); // 更新用户的总在线时长
            System.out.println("test:当前登陆时间" + param.getCurrentLoginTime());
            param.setLastLoginTime(param.getCurrentLoginTime()); // 设置上一次登陆时间为本次登陆时间
            param.setCurrentLoginTime(0L); // 设置本次登陆时间为0
            this.userMapper.updateByExampleSelective(param, example);
            // 清除redis数据
            this.redisService.del(REDIS_TICKET + redisKey);
            return true;
        }
        return false;
    }
}
