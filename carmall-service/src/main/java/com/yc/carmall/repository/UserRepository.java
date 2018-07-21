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

import com.yc.carmall.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: UserRepository
 * @description: 用户信息数据库查询接口
 * @date 2018年07月17日 16:56
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {

    @Override
    @EntityGraph("UserEntity.find")
    Page<UserEntity> findAll(Pageable pageable);

    @Query("select user from UserEntity user where user.username = ?1 and user.password = ?2 and user.isDelete = 0 ")
    List<UserEntity> login(String username, String password);

    @Query("select user from UserEntity user where user.username = ?1 and user.isDelete = 0 ")
    List<UserEntity> findUserByUsername(String username);

    @Modifying//更新查询
    @Transactional//开启事务
    @Query("update UserEntity c set c.salt = ?1 where c.id = ?2")
    int updateSaltById(String status, String id);


    @Modifying//更新查询
    @Transactional//开启事务
    @Query("update UserEntity c set c.isDelete = 1 where c.id in ?1")
    int deleteByIds(String[] ids);
}
