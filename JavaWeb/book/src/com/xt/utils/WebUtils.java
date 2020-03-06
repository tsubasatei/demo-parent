package com.xt.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {
    public static <T> T copyParam2Bean(T bean, Map map) {
        System.out.println("注入之前：" + bean);
        try {
            BeanUtils.populate(bean, map);
            /**
             * 把所有请求的参数都注入到user 对象中
             */
            System.out.println("注入之后：" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
