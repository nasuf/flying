package com.flying.cms.mapper;

import java.util.List;

import com.flying.cms.pojo.UserInfo;
import com.github.abel533.mapper.Mapper;

public interface UserMapper extends Mapper<UserInfo> {

    /**
     * 查询所有用户信息
     * 
     * @return
     */
    public List<UserInfo> queryAllUser();

    /**
     * 查詢用戶的用戶列表
     * 
     * @param userId
     * @return
     */
    public UserInfo queryUserContainsEssayListByUserId(Long userId);

}
