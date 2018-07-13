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
import com.yc.carmall.repository.CarEntityRepository;
import com.yc.carmall.service.CarEntityService;
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
 * @description: @TODO
 * @date 2018年07月12日 18:18
 */
@Transactional
@Service
public class CarEntityServiceImpl implements CarEntityService {


    @Autowired
    private CarEntityRepository carEntityRepository;

    @Override
    public CarEntity addCarSource(CarEntity carSource) {
        return carEntityRepository.save(carSource);
    }

    @Override
    public CarEntity updateCarSource(String id, CarEntity carSource) {

        CarEntity carEntityZh = carEntityRepository.findById(id).get();
        UpdateUtil.copyProperties(carSource, carEntityZh);
        return carEntityRepository.save(carEntityZh);
    }

    @Override
    public long delCarSources(String[] ids) {

        carEntityRepository.deleteByIds(ids);
        return 0;
    }

    @Override
    public Page<CarEntity> carSourcePage(Pageable pageable) {
        return carEntityRepository.findAll(pageable);
    }

    @Override
    public CarEntity updateStatus(String id, int status) {
        carEntityRepository.updateStatusById(status, id);
        return null;
    }

    @Override
    public List<CarEntity> carSources() {
        return carEntityRepository.findAll();
    }
}
