package com.imooc.service;

import com.imooc.error.BusinessException;
import com.imooc.service.model.OrderModel;

public interface OrderService {
    OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException;
}
