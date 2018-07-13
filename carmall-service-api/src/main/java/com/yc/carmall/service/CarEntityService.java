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

package com.yc.carmall.service;

import com.yc.carmall.entity.CarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: CarEntityService
 * @description: 车辆信息服务接口
 * @date 2018年07月13日 15:10
 */
public interface CarEntityService {

    CarEntity addCarSource(CarEntity carSource);

    CarEntity updateCarSource(String id, CarEntity carSource);

    long delCarSources(String[] carSourcesId);

    Page<CarEntity> carSourcePage(Pageable pageable);

    CarEntity updateStatus(String id, int status);

    List<CarEntity> carSources();
}
