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

import com.yc.carmall.entity.CarEntity;
import com.yc.carmall.service.CarEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: CarController
 * @description: 汽车信息控制器类
 * @date 2018年07月11日 11:17
 */
@Controller
public class CarController {


    @Autowired
    private CarEntityService carEntityZhService;


    @RequestMapping("/helloWorld")
    @ResponseBody
    public String helloWorld() {
        return "hello world!~";
    }



    @RequestMapping(value = "/addCarEntity", method = RequestMethod.POST)
    @ResponseBody
    public String addCarEntity(CarEntity carEntity) {

        carEntity.setCreateTime(System.currentTimeMillis());
        carEntity = carEntityZhService.addCarSource(carEntity);
        return carEntity.toString();
    }

    @RequestMapping(value = "/updateCarEntityStatus", method = RequestMethod.GET)
    @ResponseBody
    public String updateCarEntityStatus(@RequestParam(required = true) String id, @RequestParam(required = true) int status) {

        carEntityZhService.updateStatus(id, status);
        return "修改成功";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "https://yuech.net");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

}
