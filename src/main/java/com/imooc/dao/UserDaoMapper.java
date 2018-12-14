package com.imooc.dao;

import com.imooc.dataobkect.UserDao;

public interface UserDaoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDao record);

    int insertSelective(UserDao record);

    UserDao selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDao record);

    int updateByPrimaryKey(UserDao record);
}