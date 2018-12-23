package com.imooc.service;

import com.imooc.controller.viewobject.UserVO;
import com.imooc.error.BusinessException;
import com.imooc.service.model.UserModel;

public interface UserService {

    UserVO getUserById(Integer id);

    void register(UserModel model) throws BusinessException;

    UserModel validateLogin(String telephone, String password) throws BusinessException;
}
