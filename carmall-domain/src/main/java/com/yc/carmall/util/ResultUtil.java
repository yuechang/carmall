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

import com.yc.carmall.constants.BaseConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: ResultUtil
 * @description: 返回结果工具类
 * @date 2018年08月09日 17:34
 */
public class ResultUtil {

    private ResultUtil() {}

    public static Map<String,Object> getSuccessResult(String data) {

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put(BaseConstants.CODE, BaseConstants.SUCCESS_CODE);
        resultMap.put(BaseConstants.DATA, data);
        return resultMap;
    }

    public static Map<String,Object> getFailureResult(String data) {

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put(BaseConstants.CODE, BaseConstants.SERVER_ERROR_CODE);
        resultMap.put(BaseConstants.DATA, data);
        return resultMap;
    }
}
