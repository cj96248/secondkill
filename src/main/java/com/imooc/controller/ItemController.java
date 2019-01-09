package com.imooc.controller;

import com.imooc.controller.viewobject.ItemVO;
import com.imooc.error.BusinessException;
import com.imooc.response.CommonReturnType;
import com.imooc.service.ItemService;
import com.imooc.service.model.ItemModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("item")
@CrossOrigin
public class ItemController {
    private Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemServiceImpl;

    @PostMapping("/create")
    public CommonReturnType createItem(ItemVO itemVO) throws BusinessException {
        logger.info("/r/n" + itemVO);
        ItemModel itemModel = convertToItemModel(itemVO);
        ItemModel item = itemServiceImpl.createItem(itemModel);
        ItemVO iv = convertToItemVO(item);
        return new CommonReturnType(iv);
    }

    @GetMapping("id")
    public CommonReturnType getById(Integer id) throws BusinessException {
        logger.info("Query item id {}", id);
        ItemModel itemModel = itemServiceImpl.getItemById(id);
        ItemVO itemVO = convertToItemVO(itemModel);
        return new CommonReturnType(itemVO);
    }
    @GetMapping("list")
    public CommonReturnType listAll(){
        List<ItemModel> itemModels = itemServiceImpl.listItem();
        List<ItemVO> itemVOs = itemModels.stream().map(m -> convertToItemVO(m)).collect(Collectors.toList());
        return new CommonReturnType(itemVOs);
    }

    private ItemModel convertToItemModel(ItemVO itemVO){
        if(itemVO == null){
            return null;
        }
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemVO, itemModel);
        itemModel.setPrice(new BigDecimal(itemVO.getPrice()));
        return itemModel;
    }
    private ItemVO convertToItemVO(ItemModel itemModel){
        if(itemModel == null){
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);
        itemVO.setPrice(itemModel.getPrice().doubleValue());
        if(itemModel.getPromoModel() != null){
           itemVO.setPromoPrice(itemModel.getPromoModel().getPromoPrice());
            LocalDateTime now = LocalDateTime.now();
            long start = Duration.between(now,itemModel.getPromoModel().getStartTime()).getSeconds();
            itemVO.setStartTime(start);
            long end = Duration.between(now,itemModel.getPromoModel().getEndTime()).getSeconds();
           itemVO.setEndTime(end);
           itemVO.setStatus(itemModel.getPromoModel().getStatus());
        }
        return itemVO;
    }

    public static void main(String[] args) throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        Thread.sleep(1003);
        LocalDateTime end = LocalDateTime.of(2019, 1, 9, 23, 10, 20);
        long between = Duration.between(now, end).getSeconds();
        System.out.println(between);
    }
}
