package com.yc.carmall.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class UpdateUtil {

    /**
     * 将不为 null 的属性从目标实体类中复制到源实体类中
     *
     * @param src    : 更新的准备数据
     * @param target :更新的目标数据,一般从数据库中查询得到
     */
    public static <T> void copyNonNullProperties(T src, T target) {
        BeanUtils.copyProperties(src, target, getNullProperties(src));
    }


    public static <T> void copyProperties(T src, T target, String... ignorePropertyName) {
        String[] nullProperties = getNullProperties(src);
        int total = nullProperties.length + ignorePropertyName.length;
        String[] ignoreResult = new String[total];
        System.arraycopy(nullProperties, 0, ignoreResult, 0, nullProperties.length-1);
        System.arraycopy(ignorePropertyName, 0, ignoreResult, nullProperties.length, ignorePropertyName.length);

        BeanUtils.copyProperties(src, target, ignoreResult);
    }


    /**
     * 寻找 bean 对象中值为 null 的属性，并返回
     */
    private static String[] getNullProperties(Object src) {
        BeanWrapper srcBean = new BeanWrapperImpl(src);

        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        List<String> properties = new ArrayList<>(pds.length);

        for (PropertyDescriptor p : pds) {
            Object srcValue = srcBean.getPropertyValue(p.getName());
            if (srcValue == null)
                properties.add(p.getName());
        }
        String[] result = new String[properties.size()];
        return properties.toArray(result);
    }
}
