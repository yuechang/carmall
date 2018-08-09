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
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: CarEntity
 * @description: 中文车辆信息实体对象
 * @date 2018年07月10日 15:25
 */
@Entity
@Table(name = "T_CAR")
public class CarEntity extends BaseEntity {

    @ApiModelProperty(value = "车辆名称", example = "Toyota Yaris VVTI T SPIRIT - 2006 (06 plate)")
    private String carName;

    @ApiModelProperty(value = "车辆联系人", example = "13966666666")
    private String phone;

    @ApiModelProperty(value = "车辆品牌", example = "BMW")
    private String make;

    @ApiModelProperty(value = "车辆价格", example = "2295")
    private double price;

    @ApiModelProperty(value = "价格单位", example = "$")
    private String priceUnit;

    @ApiModelProperty(value = "车辆图片", example = "...,...")
    private String pictureUrl;

    @ApiModelProperty(value = "里程", example = "76712")
    private double mileage;

    @ApiModelProperty(value = "里程单位", example = "miles")
    private String mileageUnit;

    @ApiModelProperty(value = "车辆颜色", example = "GREY")
    private String colour;

    @ApiModelProperty(value = "变速器", example = "Manual")
    private String transmission;

    @ApiModelProperty(value = "燃料类型", example = "Petrol")
    private String fuelType;

    @ApiModelProperty(value = "车型", example = "HATCHBACK")
    private String bodyStyle;

    @ApiModelProperty(value = "初始注册时间", example = "2017-06-10")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date firstRegDate;

    @ApiModelProperty(value = "最后服务时间", example = "2018-07-12")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastService;

    @ApiModelProperty(value = "引擎大小", example = "1198")
    private int engineSize;

    @ApiModelProperty(value = "车位置", example = "Head office")
    private String vehicleLocation;


    @ApiModelProperty(value = "车辆描述", example = "...")
    private String vehicleDescription;


    @ApiModelProperty(value = "车辆选项", example = "...")
    private String vehicleOptions;

    @ApiModelProperty(value = "车源状态", example = "1")
    private Integer status;

    @ApiModelProperty(value = "车源信息语言", example = "cn/en")
    private String lang;


    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getMileageUnit() {
        return mileageUnit;
    }

    public void setMileageUnit(String mileageUnit) {
        this.mileageUnit = mileageUnit;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public Date getFirstRegDate() {
        return firstRegDate;
    }

    public void setFirstRegDate(Date firstRegDate) {
        this.firstRegDate = firstRegDate;
    }

    public Date getLastService() {
        return lastService;
    }

    public void setLastService(Date lastService) {
        this.lastService = lastService;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    public String getVehicleLocation() {
        return vehicleLocation;
    }

    public void setVehicleLocation(String vehicleLocation) {
        this.vehicleLocation = vehicleLocation;
    }

    public String getVehicleDescription() {
        return vehicleDescription;
    }

    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
    }

    public String getVehicleOptions() {
        return vehicleOptions;
    }

    public void setVehicleOptions(String vehicleOptions) {
        this.vehicleOptions = vehicleOptions;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }


    @Override
    public String toString() {
        return "CarEntity{" +
                "carName='" + carName + '\'' +
                ", phone='" + phone + '\'' +
                ", make='" + make + '\'' +
                ", price=" + price +
                ", priceUnit='" + priceUnit + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", mileage=" + mileage +
                ", mileageUnit='" + mileageUnit + '\'' +
                ", colour='" + colour + '\'' +
                ", transmission='" + transmission + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", bodyStyle='" + bodyStyle + '\'' +
                ", firstRegDate=" + firstRegDate +
                ", lastService=" + lastService +
                ", engineSize=" + engineSize +
                ", vehicleLocation='" + vehicleLocation + '\'' +
                ", vehicleDescription='" + vehicleDescription + '\'' +
                ", vehicleOptions='" + vehicleOptions + '\'' +
                ", status=" + status +
                ", lang='" + lang + '\'' +
                '}';
    }
}
