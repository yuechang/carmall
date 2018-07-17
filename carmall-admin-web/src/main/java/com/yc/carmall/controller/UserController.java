/*!
 * Copyright(c) 2017 Yue Chang
 * MIT Licensed
 */
package com.yc.carmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yue Chang
 * @ClassName: UserController
 * @Description: 用户登录控制器
 * @date 2018/7/14 17:03
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/signin",method = RequestMethod.GET)
    public String signIn(ModelMap map) {
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "signin";
    }

    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String signUp(ModelMap map) {
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "signup";
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

