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
import com.yc.carmall.interceptor.LanguageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

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
}
