package com.imooc.service;

import com.imooc.error.BusinessException;
import com.imooc.service.model.ItemModel;

import java.util.List;

public interface ItemService {
    /**
     * 创建商品
     */
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    /**
     * 商品列表信息
     */
    List<ItemModel> listItem();

    /**
     * 商品详细信息浏览
     */
    ItemModel getItemById(Integer id) throws BusinessException;

    /**
     *  库存扣减， 如果成功返回true，失败返回0
     */
    boolean decreaseStock(Integer itemId, Integer amount);

    /**
     * 销量增加
     */
    void increaseSales(Integer itemId, Integer amount);

}
