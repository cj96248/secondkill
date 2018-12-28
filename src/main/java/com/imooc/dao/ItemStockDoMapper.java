package com.imooc.dao;

import com.imooc.dataobject.ItemStockDo;
import org.apache.ibatis.annotations.Param;

public interface ItemStockDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemStockDo record);

    int insertSelective(ItemStockDo record);

    /**
     * 更新库存，返回受影响的条目数
     */
    int decreaseStock(@Param("itemId") Integer id, @Param("amount") Integer amount);

    ItemStockDo selectByPrimaryKey(Integer id);

    ItemStockDo selectByItemId(Integer id);

    int updateByPrimaryKeySelective(ItemStockDo record);

    int updateByPrimaryKey(ItemStockDo record);
}