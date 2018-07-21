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

import com.yc.carmall.entity.UserEntity;
import com.yc.carmall.repository.UserRepository;
import com.yc.carmall.service.UserService;
import com.yc.carmall.util.PasswordUtil;
import com.yc.carmall.util.UpdateUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: UserServiceImpl
 * @description: 用户信息服务实现类
 * @date 2018年07月17日 17:22
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity login(String username, String password) {

        List<UserEntity> userEntityList = userRepository.login(username, password);
        if (CollectionUtils.isEmpty(userEntityList))
            return null;

        // 返回第一个用户对象
        UserEntity userEntity = userEntityList.get(0);

        String salt = userEntity.getSalt();
        if (StringUtils.isBlank(salt)) {
            salt = PasswordUtil.generate(password);
            userRepository.updateSaltById(salt,userEntity.getId());
            return userEntity;
        }
        // 验证用户密码加盐信息
        boolean flag = PasswordUtil.verify(password, salt);
        if (flag) {
            return userEntity;
        }
        return null;
    }

    @Override
    public UserEntity addUserEntity(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUserEntity(String id, UserEntity userEntity) {

        UserEntity targetEntity = userRepository.findById(id).get();
        UpdateUtil.copyProperties(userEntity, targetEntity);
        return userRepository.save(targetEntity);
    }

    @Override
    public long delUserEntity(String[] ids) {
        return userRepository.deleteByIds(ids);
    }

    @Override
    public Page<UserEntity> userEntityPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<UserEntity> findAllUserEntity() {
        return userRepository.findAll();
    }

    @Override
    public List<UserEntity> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
