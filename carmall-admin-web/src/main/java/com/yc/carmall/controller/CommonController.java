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

package com.yc.carmall.controller;

import com.yc.carmall.util.ResultUtil;
import com.yc.carmall.util.SystemPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: CommonController
 * @description: 公共服务控制器类
 * @date 2018年08月08日 11:18
 */
@Controller
@RequestMapping("/{lang}")
public class CommonController {

    private static Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private SystemPropertiesUtil systemPropertiesUtil;

    @RequestMapping("/upload")
    @ResponseBody
    public Map upload(MultipartFile file) {


        logger.info("file name:",file.getOriginalFilename());
        if(file.isEmpty()){
            return ResultUtil.getFailureResult("file is empty");
        }

        Map<String, String> ftpMap = systemPropertiesUtil.getFtpMap();



        long size = file.getSize();
        if(size > 1048576){
            logger.info("附件过大，请重启更新APP后再尝试");

            return null;
        }

        String fileName = file.getOriginalFilename();
        String fileExt = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()).toLowerCase();
        String imgTypes = "gif,jpeg,jpg,png,bmp";
        logger.info("fileName:{},fileExt{},fileSize{}",new Object[]{fileName,fileExt,size});

        if (!imgTypes.contains(fileExt)) {
            String msg = "不是允许的文件类型(gif,jpeg,jpg,png,bmp格式)";
            return null;
        }
        return null;
    }






}
