/*
 * Copyright (c) 2018, Yue Chang and/or its affiliates. All rights reserved.
 * Yue Chang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.yc.carmall.interceptor;


import com.yc.carmall.constants.BaseConstants;
import com.yc.carmall.enums.LanguageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: WebConfiguration
 * @description: 拦截器配置
 * @date 2018年07月16日 10:35
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private LanguageInterceptor languageInterceptor;

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cl = new CookieLocaleResolver();
        cl.setCookieMaxAge(BaseConstants.COOKIE_MAX_AGE);
        cl.setCookieName(BaseConstants.COOKIE_NAME);
        return cl;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(languageInterceptor);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /**
         * 配置路径跳转；将某个路径的请求映射到另外一个路径
         * 如将所有http://localhost/b/**的请求全部跳转到http://localhost/test上去
         */
        Locale locale = LocaleContextHolder.getLocale();
        String country = locale.getCountry();
        String viewName = "/global/home";
        if (Locale.CHINA.getCountry().equals(country) || Locale.CHINESE.getCountry().equals(country)) {
            viewName = "/".concat(LanguageEnum.ZH.getLang()).concat(viewName);
        } else {
            viewName = "/".concat(LanguageEnum.EN.getLang()).concat(viewName);
        }
        registry.addRedirectViewController("/", viewName);

        /**
         * 将路径映射到某个名称为指定值的视图上
         * 访问/c会返回a.html的视图
         * 一般与ViewResolver结合使用
         */
        //registry.addViewController("/c").setViewName("a");

        /**
         * 指定某个请求的状态码，而不返回任何的内容
         * 如下面将/badRequest请求返回状态码为400，而没有返回其它内容
         */
        //registry.addStatusController("/badRequest", HttpStatus.BAD_REQUEST);
    }
}
