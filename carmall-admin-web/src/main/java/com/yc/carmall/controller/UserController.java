/*!
 * Copyright(c) 2017 Yue Chang
 * MIT Licensed
 */
package com.yc.carmall.controller;

import com.yc.carmall.constants.BaseConstants;
import com.yc.carmall.entity.UserEntity;
import com.yc.carmall.interceptor.LanguageInterceptor;
import com.yc.carmall.service.UserService;
import com.yc.carmall.util.PasswordUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.reflect.annotation.ExceptionProxy;

import java.util.List;

/**
 * @author Yue Chang
 * @ClassName: UserController
 * @Description: 用户登录控制器
 * @date 2018/7/14 17:03
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    // 登录
    @RequestMapping(value = "/signin",method = RequestMethod.GET)
    public String signin(ModelMap map) {
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "signin";
    }

    // 登录
    @RequestMapping(value = "/signin",method = RequestMethod.POST)
    public String signin(String username, String password, ModelMap map) {

        UserEntity userEntity = userService.login(username, password);
        // return模板文件的名称，对应src/main/resources/templates/index.html
        map.put("userInfo", userEntity);

        return "index";
    }

    // 注册
    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String signup() {
        return "signup";
    }

    // 注册
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public String signup(String username, String password, String confirmPassword, ModelMap map) {


        int code = BaseConstants.SERVER_ERROR_CODE;
        String data = "";
        try {


            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(password);
            userEntity.setSalt(PasswordUtil.generate(password));

            userService.addUserEntity(userEntity);
            code = BaseConstants.SUCCESS_CODE;
        } catch (Exception e) {
            data = e.getMessage();
            logger.info("注册失败。", e);
        } finally {
            map.put(BaseConstants.CODE, code);
            map.put(BaseConstants.DATA, data);
        }
        return "signupmessage";
    }

    // 注册
    @RequestMapping(value = "/checkusername",method = RequestMethod.GET)
    @ResponseBody
    public boolean checkUsername(String username) {

        if (StringUtils.isBlank(username)) {
            return false;
        }

        List<UserEntity> userList = userService.findUserByUsername(username);
        return CollectionUtils.isEmpty(userList);
    }



    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "https://yuech.net");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "Hello !";
    }
}

