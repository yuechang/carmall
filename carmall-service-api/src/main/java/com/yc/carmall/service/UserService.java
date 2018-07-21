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

import com.yc.carmall.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: UserService
 * @description: 用户信息服务接口
 * @date 2018年07月17日 16:53
 */
public interface UserService {

    UserEntity login(String username, String password);

    UserEntity addUserEntity(UserEntity userEntity);

    UserEntity updateUserEntity(String id, UserEntity userEntity);

    long delUserEntity(String[] ids);

    Page<UserEntity> userEntityPage(Pageable pageable);

    List<UserEntity> findAllUserEntity();

    List<UserEntity> findUserByUsername(String username);
}
