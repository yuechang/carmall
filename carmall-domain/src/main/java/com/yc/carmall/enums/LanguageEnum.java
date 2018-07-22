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

package com.yc.carmall.enums;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: LanguageEnum
 * @description: 语言枚举类
 * @date 2018年07月10日 15:44
 */
public enum LanguageEnum {

    ZH(1,"cn"),
    EN(2,"en");

    private int code;
    private String lang;

    LanguageEnum(int code, String lang) {
        this.code = code;
        this.lang = lang;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
