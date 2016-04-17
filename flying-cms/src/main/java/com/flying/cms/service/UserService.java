package com.flying.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flying.cms.mapper.UserMapper;
import com.flying.cms.pojo.UserInfo;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 得到所有用户列表
     * 
     * @return
     */
    public List<UserInfo> getAllUserInfo() {
        List<UserInfo> list = this.userMapper.queryAllUser();
        return list;
    }

    /**
     * 获取某用户详细信息
     * 
     * @param id
     * @return
     */
    public UserInfo checkUserDetail(Long id) {
        UserInfo userInfo = this.userMapper.selectByPrimaryKey(id);
        return userInfo;
    }

    /**
     * 获取用户文章列表
     * 
     * @param id
     * @return
     */
    public UserInfo queryEssayListByUserId(Long id) {
        UserInfo userInfo = this.userMapper.queryUserContainsEssayListByUserId(id);
        return userInfo;
    }

}
