package com.imooc.controller;

import com.imooc.error.BusinessException;
import com.imooc.error.EnumError;
import com.imooc.response.CommonReturnType;
import com.imooc.service.OrderService;
import com.imooc.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping("/create")
    public CommonReturnType create(Integer itemId, Integer amount) throws BusinessException {
        Boolean login = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if(login == null || !login){
            throw  new BusinessException(EnumError.USER_NOT_LOGIN);
        }
        UserModel user = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        orderService.createOrder(user.getId(), itemId,amount);
        return new CommonReturnType(null);
    }
}
