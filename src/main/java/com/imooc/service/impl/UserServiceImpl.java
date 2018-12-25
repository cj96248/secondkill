package com.imooc.service.impl;

import com.imooc.controller.viewobject.UserVO;
import com.imooc.dao.UserDoMapper;
import com.imooc.dao.UserPasswordDoMapper;
import com.imooc.dataobject.UserDo;
import com.imooc.dataobject.UserPasswordDo;
import com.imooc.error.BusinessException;
import com.imooc.error.EnumError;
import com.imooc.service.model.UserModel;
import com.imooc.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDoMapper userDoMapper;

    @Autowired
    private UserPasswordDoMapper userPasswordDoMapper;

    @Override
    public UserVO getUserById(Integer id) {
        UserDo userDo = userDoMapper.selectByPrimaryKey(id);
        UserPasswordDo userPasswordDo = userPasswordDoMapper.selectByUserId(id);
        UserModel userModel = convertUser(userDo, userPasswordDo);
        return convertToUserVO(userModel);
    }

    @Override
    @Transactional
    public void register(UserModel model) throws BusinessException {
        if(model == null){
            throw new BusinessException(EnumError.PARAMETER_VALIDATION_ERROR);
        }
        UserDo userDo = convertToUserDO(model);
        try {
            userDoMapper.insertSelective(userDo);
            model.setId(userDo.getId());
        }catch (DuplicateKeyException ex){
            throw new BusinessException(EnumError.USER_ALREADY_EXIST);
        }
        UserPasswordDo userPasswordDo = convertToUserPasswordDO(model);
        userPasswordDoMapper.insertSelective(userPasswordDo);
    }

    @Override
    public UserModel validateLogin(String telephone, String password) throws BusinessException {
        // get user telephone by telephone
        UserDo userDo = userDoMapper.selectByTelephone(telephone);
        if(userDo == null){
            throw new BusinessException(EnumError.USER_LOGIN_ERROR);
        }
        UserPasswordDo userPasswordDo = userPasswordDoMapper.selectByUserId(userDo.getId());
        // compare the user's password with the input password
        UserModel userModel = convertUser(userDo, userPasswordDo);

        if(password.equals(userPasswordDo.getEncryptPassword())){
            return userModel;
        }else {
            throw new BusinessException(EnumError.USER_LOGIN_ERROR);
        }
    }

    private UserModel convertUser(UserDo userDo, UserPasswordDo userPasswordDo){
        UserModel userModel = new UserModel();
        if(userDo == null){
            return null;
        }
        BeanUtils.copyProperties(userDo,userModel);
        if(userPasswordDo != null){
            userModel.setEncryptPassword(userPasswordDo.getEncryptPassword());
        }
        return userModel;
    }

    private UserVO convertToUserVO(UserModel userModel){
        if(userModel == null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }
    private UserDo convertToUserDO(UserModel userModel){
        if(userModel == null){
            return null;
        }
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(userModel, userDo);
        return userDo;
    }
    private UserPasswordDo convertToUserPasswordDO(UserModel userModel){
        if(userModel == null){
            return null;
        }
        UserPasswordDo userPasswordDo = new UserPasswordDo();
        userPasswordDo.setEncryptPassword(userModel.getEncryptPassword());
        userPasswordDo.setUserId(userModel.getId());
        return userPasswordDo;
    }
}
