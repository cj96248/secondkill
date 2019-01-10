package com.imooc.dao;

import com.imooc.dataobject.PromoDo;

public interface PromoDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PromoDo record);

    int insertSelective(PromoDo record);

    PromoDo selectByPrimaryKey(Integer id);

    PromoDo selectByItemId(Integer itemId);

    int updateByPrimaryKeySelective(PromoDo record);

    int updateByPrimaryKey(PromoDo record);
}