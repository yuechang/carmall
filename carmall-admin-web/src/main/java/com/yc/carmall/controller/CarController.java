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
import com.yc.carmall.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: CarController
 * @description: 汽车信息控制器类
 * @date 2018年07月11日 11:17
 */
@Controller
@RequestMapping("/{lang}")
public class CarController {


    @Autowired
    private CarService carService;

    @Autowired
    private MessageSource messageSource;


    @RequestMapping("/helloWorld")
    @ResponseBody
    public String helloWorld() {
        return "hello world!~";
    }


    @RequestMapping("/testRedis")
    @ResponseBody
    public String testRedis() {

        carService.testRedis();
        return "hello world!~";
    }

    @RequestMapping(value = "/addCarEntity", method = RequestMethod.GET)
    public String addCarEntity() {
        return "addcar";
    }

    @RequestMapping(value = "/addCarEntity", method = RequestMethod.POST)
    @ResponseBody
    public String addCarEntity(CarEntity carEntity) {

        carEntity.setCreateTime(System.currentTimeMillis());
        carEntity = carService.addCarSource(carEntity);
        return carEntity.toString();
    }

    @RequestMapping(value = "/updateCarEntityStatus", method = RequestMethod.GET)
    @ResponseBody
    public String updateCarEntityStatus(@RequestParam(required = true) String id, @RequestParam(required = true) int status) {

        CarEntity carEntity = carService.updateStatus(id, status);

        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("car.info.update.success",null,locale);
        return message;
    }

    @RequestMapping(value = "/global/home",method = RequestMethod.GET)
    public String index(ModelMap map) {
        List<CarEntity> carList = carService.findAllCarSource();

        map.put("carList", carList);
        return "index";
    }


}
