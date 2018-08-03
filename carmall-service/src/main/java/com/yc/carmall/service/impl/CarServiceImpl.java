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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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


    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    private static final Lock lock = new ReentrantLock();

    @Autowired
    private RedisTemplate redisTemplate;


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

    @Override
    public void testRedis() {

        try {
            lock.lock();

            String key = "key";
            String value = "value";
            ValueOperations<Serializable, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);

            String valueInRedis = operations.get(key);
            logger.info("key:{},value:{},valueInRedis:{}", key, value, valueInRedis);
        } catch (Exception e) {
            logger.info("testRedis exception", e);
        } finally {
            lock.unlock();
        }
    }
/*
    @Override
    public synchronized void testRedis() {

        try {
            String key = "key";
            String value = "value";
            ValueOperations<Serializable, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);

            String valueInRedis = operations.get(key);
            logger.info("key:{},value:{},valueInRedis:{}", key, value, valueInRedis);
        } catch (Exception e) {
            logger.info("testRedis exception", e);
        }
    }
    */
}
