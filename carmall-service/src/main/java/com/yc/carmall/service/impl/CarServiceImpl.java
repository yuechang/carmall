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

package com.yc.carmall.service.impl;

import com.yc.carmall.entity.CarEntity;
import com.yc.carmall.repository.CarRepository;
import com.yc.carmall.service.CarService;
import com.yc.carmall.util.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: CarEntityServiceImpl
 * @description: 车辆信息服务实现类
 * @date 2018年07月12日 18:18
 */
@Transactional
@Service
public class CarServiceImpl implements CarService {


    @Autowired
    private CarRepository carRepository;

    @Override
    public CarEntity addCarSource(CarEntity carSource) {
        return carRepository.save(carSource);
    }

    @Override
    public CarEntity updateCarSource(String id, CarEntity carSource) {

        CarEntity targetEntity = carRepository.findById(id).get();
        UpdateUtil.copyProperties(carSource, targetEntity);
        return carRepository.save(targetEntity);
    }

    @Override
    public long delCarSource(String[] ids) {

        return carRepository.deleteByIds(ids);
    }

    @Override
    public Page<CarEntity> carSourcePage(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    public CarEntity updateStatus(String id, int status) {
        carRepository.updateStatusById(status, id);
        return null;
    }

    @Override
    public List<CarEntity> findAllCarSource() {
        return carRepository.findAll();
    }
}
