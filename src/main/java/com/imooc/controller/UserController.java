package com.imooc.controller;

import com.imooc.controller.viewobject.UserVO;
import com.imooc.error.BusinessException;
import com.imooc.error.EnumError;
import com.imooc.response.CommonReturnType;
import com.imooc.service.UserService;
import com.imooc.service.model.UserModel;
import com.imooc.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@RestController
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping("/get")
    public CommonReturnType getUser(Integer id) throws BusinessException {
        UserVO userVO = userServiceImpl.getUserById(id);
        if(userVO == null){
            throw new BusinessException(EnumError.USER_NOT_EXIST);
        }
        return new CommonReturnType(userVO);
    }

    @RequestMapping("/register")
    public CommonReturnType register(String telephone, String code, String name, Integer gender, Integer age, String password) throws BusinessException {
        String otpCode = (String) httpServletRequest.getSession().getAttribute(telephone);
        if(!com.alibaba.druid.util.StringUtils.equals(code, otpCode)){
            throw new BusinessException(EnumError.PARAMETER_VALIDATION_ERROR,"验证码不正确");
        }
        UserModel model = new UserModel();
        model.setTelephone(telephone);
        model.setName(name);
        model.setGender(gender.byteValue());
        model.setAge(age);
        model.setRegisterMode("byphone");
        model.setEncryptPassword(CommonUtil.md5(password));

        System.out.println(model);

        userServiceImpl.register(model);
        return new CommonReturnType(null);
    }

    @RequestMapping("/login")
    public CommonReturnType login(String telephone, String password) throws BusinessException {
        if(StringUtils.isEmpty(telephone) || StringUtils.isEmpty(password)){
            throw new BusinessException(EnumError.PARAMETER_NONNULL_ERROR);
        }

        UserModel model = userServiceImpl.validateLogin(telephone, CommonUtil.md5(password));

        if(model != null){
            httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
            httpServletRequest.getSession().setAttribute("LOGIN_USER", model);
        }
        return new CommonReturnType(null);
    }

    @RequestMapping("/otp")
    public CommonReturnType getOtp(String telephone) throws BusinessException {

        if(StringUtils.isEmpty(telephone)){
            throw new BusinessException(EnumError.PARAMETER_VALIDATION_ERROR);
        }
        // Generate opt verify code
        Random random = new Random();
        int value = random.nextInt(89999);
        value += 10000;
        String code = String.valueOf(value);
        // combine opt code with telephone number

        httpServletRequest.getSession().setAttribute(telephone,code);

        // Send opt code to user
        String result = String.format("%s | %s", telephone, code);
        System.out.println(result);
        return new CommonReturnType(code);
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Access";
    }
}
