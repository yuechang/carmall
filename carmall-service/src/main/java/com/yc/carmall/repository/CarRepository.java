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

package com.yc.carmall.repository;

import com.yc.carmall.entity.CarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: CarEntityRepository
 * @description: 车辆信息数据库查询接口
 * @date 2018年07月12日 17:05
 */
@Repository
public interface CarRepository extends JpaRepository<CarEntity,String> {

    @Override
    @EntityGraph("CarEntity.find")
    Page<CarEntity> findAll(Pageable pageable);

    @Modifying//更新查询
    @Transactional//开启事务
    @Query("update CarEntity c set c.status = ?1 where c.id = ?2")
    int updateStatusById(int status, String id);

    @Modifying//更新查询
    @Transactional//开启事务
    @Query("update CarEntity c set c.isDelete = 1 where c.id in ?1")
    int deleteByIds(String[] ids);

}
