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

package com.yc.carmall.entity;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: BaseEntity
 * @description: 基础实体类
 * @date 2018年07月10日 15:05
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Access(AccessType.FIELD)
public class BaseEntity implements Serializable {

    @ApiModelProperty(hidden = true)
    @Id
    @Column(name="id",length=40)
    private String id;

    @ApiModelProperty(hidden = true)
    @CreatedBy
    @Column(name="createBy",length=40)
    private String createBy;

    @ApiModelProperty(hidden = true)
    @LastModifiedBy
    @Column(name="updateBy",length=40)
    private String updateBy;

    @ApiModelProperty(hidden = true)
    @LastModifiedDate
    private long updateTime;

    @ApiModelProperty(hidden = true)
    @CreatedDate
    private long createTime;

    // 是否为已经删除的数据，0：正常数据，1：已删除数据
    @ApiModelProperty(hidden = true)
    @Column(name="isDelete")
    private short isDelete;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public short getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(short isDelete) {
        this.isDelete = isDelete;
    }

    //如果number没有,自动生成number
    @PrePersist
    public void prePersist(){
        //可以通过this  获取到当前的class 是什么实体     获取到之后可以作为redis的键值
        Class<? extends BaseEntity> clazz = this.getClass();
        clazz.toString();
        //todo 结合redis 让number的位数变为7 8位 2018年3月29日10:30:57
        if(!StringUtils.hasText(id)){
            this.updateTime= System.currentTimeMillis();
            //this.number=String.valueOf(System.currentTimeMillis()).substring(1);
            this.id=String.valueOf(System.currentTimeMillis()).substring(1);
        }
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
