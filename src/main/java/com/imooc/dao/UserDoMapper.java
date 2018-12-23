package com.imooc.dao;

import com.imooc.dataobject.UserDo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDo record);

    int insertSelective(UserDo record);

    UserDo selectByPrimaryKey(Integer id);

    UserDo selectByTelephone(String telephone);

    int updateByPrimaryKeySelective(UserDo record);

    int updateByPrimaryKey(UserDo record);
}