package com.imooc.dao;

import com.imooc.dataobject.UserPasswordDo;
import com.imooc.dataobject.UserPasswordDoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPasswordDoMapper {
    long countByExample(UserPasswordDoExample example);

    int deleteByExample(UserPasswordDoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserPasswordDo record);

    int insertSelective(UserPasswordDo record);

    List<UserPasswordDo> selectByExample(UserPasswordDoExample example);

    UserPasswordDo selectByPrimaryKey(Integer id);

    UserPasswordDo selectByUserId(Integer userId);

    int updateByExampleSelective(@Param("record") UserPasswordDo record, @Param("example") UserPasswordDoExample example);

    int updateByExample(@Param("record") UserPasswordDo record, @Param("example") UserPasswordDoExample example);

    int updateByPrimaryKeySelective(UserPasswordDo record);

    int updateByPrimaryKey(UserPasswordDo record);
}