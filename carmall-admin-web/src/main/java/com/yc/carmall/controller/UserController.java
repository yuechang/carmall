/*!
 * Copyright(c) 2017 Yue Chang
 * MIT Licensed
 */
package com.yc.carmall.controller;

import com.yc.carmall.constants.BaseConstants;
import com.yc.carmall.entity.UserEntity;
import com.yc.carmall.service.UserService;
import com.yc.carmall.util.PasswordUtil;
import com.yc.carmall.util.SystemPropertiesUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * @author Yue Chang
 * @ClassName: UserController
 * @Description: 用户登录控制器
 * @date 2018/7/14 17:03
 */
@Controller
@RequestMapping("/{lang}/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //@Autowired
    //private SystemPropertiesUtil systemPropertiesUtil;

    @Autowired
    private MessageSource messageSource;

    // 登录
    @RequestMapping(value = "/signin",method = RequestMethod.GET)
    public String signin(@PathVariable String lang, ModelMap map) {
        // return模板文件的名称，对应src/main/resources/templates/index.html
        map.put(BaseConstants.LANG_PARAMETER_NAME, lang);
        return "signin";
    }

    // 登录
    @RequestMapping(value = "/signin",method = RequestMethod.POST)
    public String signin(@PathVariable String lang, String username, String password, ModelMap map) {

        UserEntity userEntity = userService.login(username, password);
        if (null == userEntity) {

            Locale locale = LocaleContextHolder.getLocale();
            String message = messageSource.getMessage("sign.in.username.or.password.incorrect",null,locale);
            map.put(BaseConstants.CODE, BaseConstants.SUCCESS_CODE);
            map.put(BaseConstants.DATA, message);

            return lang.concat("/signin");
        }

        // return模板文件的名称，对应src/main/resources/templates/index.html
        // map.put("userInfo", userEntity);
        map.put("username", userEntity.getUsername());
        return "index";
    }

    // 注册
    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String signup(@PathVariable String lang, ModelMap map) {

        //String postUrl = lang.concat("/user/signup");
        map.put(BaseConstants.LANG_PARAMETER_NAME, lang);
        return "signup";
    }

    // 注册
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public String signup(String username, String password, ModelMap map, @PathVariable String lang) {

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
            String signInUrl = "/".concat(lang).concat("/user/signin");
            String signUpUrl = "/".concat(lang).concat("/user/signup");

            map.put(BaseConstants.CODE, code);
            map.put(BaseConstants.DATA, data);
            map.put("signInUrl", signInUrl);
            map.put("signUpUrl", signUpUrl);
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

