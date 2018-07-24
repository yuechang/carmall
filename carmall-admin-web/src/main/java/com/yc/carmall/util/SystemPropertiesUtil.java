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

package com.yc.carmall.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: SystemPropertiesUtil
 * @description: 配置信息工具类
 * @date 2018年07月17日 11:24
 */
@Component
@ConfigurationProperties(prefix = "system")
public class SystemPropertiesUtil {

    private String temp;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
