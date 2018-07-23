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
/*

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        Locale locale = LocaleContextHolder.getLocale();
        String language = locale.getLanguage();
        String country = locale.getCountry();
        System.out.println(language + "," + country);
        String vimeName = "index";
        if (Locale.CHINA.getCountry().equals(country) || Locale.CHINESE.getCountry().equals(country)) {
            vimeName = LanguageEnum.ZH
        } else {

        }
        registry.addViewController("/").setViewName("forward:/cn/user/signup");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        this.addViewControllers(registry);
    }
*/


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /**
         * 配置路径跳转；将某个路径的请求映射到另外一个路径
         * 如将所有http://localhost/b/**的请求全部跳转到http://localhost/test上去
         */
        //registry.addRedirectViewController("/cn/**", "/**");
        //registry.addRedirectViewController("/en/**", "/**");
/*

        Locale locale = LocaleContextHolder.getLocale();
        String language = locale.getLanguage();
        String country = locale.getCountry();
        System.out.println(language + "," + country);
        String redirecUrl = "";
        if (Locale.CHINA.getCountry().equals(country) || Locale.CHINESE.getCountry().equals(country)) {
            redirecUrl = "/".concat(LanguageEnum.ZH.getLang()).concat("/**");
        } else {
            redirecUrl = "/".concat(LanguageEnum.EN.getLang()).concat("/**");
        }
        registry.addRedirectViewController("/**", redirecUrl);


        /**
         * 将路径映射到某个名称为指定值的视图上
         * 访问/c会返回a.html的视图
         * 一般与ViewResolver结合使用
         */
        //registry.addViewController("/c").setViewName("a");
        Locale locale = LocaleContextHolder.getLocale();
        String country = locale.getCountry();
        String forwardPrefix = "forward:/";
        String viewName = "index";
        String indexViewName = "";

        if (Locale.CHINA.getCountry().equals(country) || Locale.CHINESE.getCountry().equals(country)) {
            indexViewName = forwardPrefix.concat(LanguageEnum.ZH.getLang()).concat("/").concat(viewName);
        } else {
            indexViewName = forwardPrefix.concat(LanguageEnum.EN.getLang()).concat("/").concat(viewName);
        }
        registry.addViewController("/").setViewName(indexViewName);

        // 设置/cn和/en跳转地址
        registry.addViewController("/".concat(LanguageEnum.ZH.getLang()))
                .setViewName(forwardPrefix.concat(LanguageEnum.ZH.getLang()).concat("/").concat(viewName));
        registry.addViewController("/".concat(LanguageEnum.EN.getLang()))
                .setViewName(forwardPrefix.concat(LanguageEnum.EN.getLang()).concat("/").concat(viewName));

        /**
         * 指定某个请求的状态码，而不返回任何的内容
         * 如下面将/badRequest请求返回状态码为400，而没有返回其它内容
         */
        //registry.addStatusController("/badRequest", HttpStatus.BAD_REQUEST);
    }
}
