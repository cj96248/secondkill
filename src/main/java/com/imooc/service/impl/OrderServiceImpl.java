package com.imooc.service.impl;

import com.imooc.controller.viewobject.UserVO;
import com.imooc.dao.OrderDoMapper;
import com.imooc.dao.SequenceDoMapper;
import com.imooc.dataobject.OrderDo;
import com.imooc.dataobject.SequenceDo;
import com.imooc.error.BusinessException;
import com.imooc.error.EnumError;
import com.imooc.service.ItemService;
import com.imooc.service.OrderService;
import com.imooc.service.UserService;
import com.imooc.service.model.ItemModel;
import com.imooc.service.model.OrderModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDoMapper orderDoMapper;

    @Autowired
    private SequenceDoMapper sequenceDoMapper;


    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException {
        //verify the order status: if user login/if the product exist
        ItemModel item = itemService.getItemById(itemId);
        if(item == null){
           throw new BusinessException(EnumError.PRODUCT_NOT_EXIST);
        }

        UserVO user = userService.getUserById(userId);
        if(user == null){
            throw new BusinessException(EnumError.USER_NOT_LOGIN);
        }

        //check the stock
        boolean success = itemService.decreaseStock(itemId, amount);
        if(!success){
            throw new BusinessException(EnumError.PRODUCT_NOT_ENOUGH);
        }

        //save to db
        OrderModel orderModel = new OrderModel();
        orderModel.setItemId(itemId);
        orderModel.setUserId(userId);
        orderModel.setAmount(amount);
        orderModel.setItemPrice(item.getPrice());
        orderModel.setOrderPrice(item.getPrice().multiply(new BigDecimal(amount)));
        orderModel.setId(generateOrderNo());

        OrderDo orderDo = conveterToOrderDo(orderModel);

        orderDoMapper.insertSelective(orderDo);
        return orderModel;
    }

    /**
     * 订单号16位
     * 前8位为时间信息，年月日
     * 中间6位自增序列
     * 后两位为分库分表
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generateOrderNo(){
        StringBuilder builder = new StringBuilder();
        // Part 1
        LocalDate date = LocalDate.now();
        String part1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        // Part 2
        SequenceDo sequenceDo = sequenceDoMapper.getByName("order_info");
        Integer sequence = sequenceDo.getCurrentValue();
        int nextValue = sequenceDo.getCurrentValue() + sequenceDo.getStep();
        if(nextValue > 900000){
            sequenceDo.setCurrentValue(0);
        }
        sequenceDo.setCurrentValue(nextValue);
        sequenceDoMapper.updateByPrimaryKeySelective(sequenceDo);
        builder.append((100000+sequence));
        // Part 3
        builder.append("00");
        return builder.toString();
    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        String part1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(part1);
    }

    private OrderDo conveterToOrderDo(OrderModel orderModel){
        if(orderModel == null){
            return null;
        }
        OrderDo orderDo = new OrderDo();
        BeanUtils.copyProperties(orderModel, orderDo);
        return orderDo;
    }
}
