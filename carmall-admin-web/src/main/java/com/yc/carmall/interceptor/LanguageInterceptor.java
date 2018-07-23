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
import com.yc.carmall.util.SystemPropertiesUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: LanguageInterceptor
 * @description: 语言拦截器
 * @date 2018年07月16日 10:20
 */
@Component
public class LanguageInterceptor extends LocaleChangeInterceptor {

    private static Logger logger = LoggerFactory.getLogger(LanguageInterceptor.class);

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    private SystemPropertiesUtil systemPropertiesUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {

        //获取@pathvariable的参数/{lang}
        Map map = (Map)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String lang = MapUtils.getString(map, BaseConstants.LANG_PARAMETER_NAME, LanguageEnum.ZH.getLang());
        Locale locale = new Locale(lang);
        // 设置Locale
        localeResolver.setLocale(request, response, locale);
        return super.preHandle(request, response, handler);
    }
}

