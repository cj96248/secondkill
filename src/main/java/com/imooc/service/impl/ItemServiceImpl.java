package com.imooc.service.impl;

import com.imooc.dao.ItemDoMapper;
import com.imooc.dao.ItemStockDoMapper;
import com.imooc.dataobject.ItemDo;
import com.imooc.dataobject.ItemStockDo;
import com.imooc.error.BusinessException;
import com.imooc.error.EnumError;
import com.imooc.service.ItemService;
import com.imooc.service.model.ItemModel;
import com.imooc.validator.ValidationResult;
import com.imooc.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ValidatorImpl validator;
    
    @Autowired
    private ItemDoMapper itemDoMapper;

    @Autowired
    private ItemStockDoMapper itemStockDoMapper;

    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        // 1.Parameter validation
        ValidationResult validate = validator.validate(itemModel);
        if(validate.isHasError()){
            throw new BusinessException(EnumError.PARAMETER_VALIDATION_ERROR,validate.getMessage());
        }

        // 2. transform item model to data object
        ItemDo itemDo = convertToItemDO(itemModel);

        // 3. save to database
        itemDoMapper.insertSelective(itemDo);
        itemModel.setId(itemDo.getId());

        // 3.2 transform item model to Item Stock
        ItemStockDo itemStockDo = convertToItemStockDO(itemModel);
        itemStockDoMapper.insertSelective(itemStockDo);

        // 4. return the saved item
        return getItemById(itemDo.getId());
    }

    @Override
    public List<ItemModel> listItem() {
        List<ItemDo> list = itemDoMapper.listItem();
        List<ItemModel> collect = list.stream().map(d -> {
            ItemStockDo itemStockDo = itemStockDoMapper.selectByItemId(d.getId());
            return convertToItemModel(d, itemStockDo);
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        // 1 get item by id
        ItemDo itemDo = itemDoMapper.selectByPrimaryKey(id);
        if(itemDo == null){
            return null;
        }
        // 2 get item stock by item id
        ItemStockDo itemStockDo = itemStockDoMapper.selectByItemId(id);

        ItemModel itemModel = convertToItemModel(itemDo, itemStockDo);
        return itemModel;
    }


    @Override
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount) {
        int rows = itemStockDoMapper.decreaseStock(itemId, amount);
        return rows>0;
    }

    @Override
    @Transactional
    public void increaseSales(Integer itemId, Integer amount) {
        itemDoMapper.increaseSales(itemId, amount);
    }

    private ItemDo convertToItemDO(ItemModel itemModel){
        if(itemModel == null){
            return null;
        }
        ItemDo itemDo = new ItemDo();
        BeanUtils.copyProperties(itemModel, itemDo);
        itemDo.setPrice(itemModel.getPrice().doubleValue());
        return itemDo;
    }
    private ItemStockDo convertToItemStockDO(ItemModel itemModel){
        if(itemModel == null){
            return null;
        }
        ItemStockDo itemStockDo = new ItemStockDo();
        itemStockDo.setStock(itemModel.getStock());
        itemStockDo.setItemId(itemModel.getId());
        return itemStockDo;
    }
    private ItemModel convertToItemModel(ItemDo itemDo, ItemStockDo itemStockDo){
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDo, itemModel);
        itemModel.setPrice(new BigDecimal(itemDo.getPrice()));
        itemModel.setStock(itemStockDo.getStock());
        return itemModel;
    }
}
