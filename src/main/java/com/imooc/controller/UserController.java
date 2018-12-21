package com.imooc.controller;

import com.imooc.controller.viewobject.UserVO;
import com.imooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping("/get")
    public UserVO getUser(Integer id){
        return userServiceImpl.getUserById(id);
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHelo(){
        return "Access";
    }
}
