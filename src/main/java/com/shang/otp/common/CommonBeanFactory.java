package com.shang.otp.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

public class CommonBeanFactory implements ApplicationContextAware {
    private static ApplicationContext context;

    public CommonBeanFactory() {
    }

    public void setApplicationContext(@NonNull ApplicationContext ctx) throws BeansException {
        context = ctx;
    }

    public static Object getBean(String beanName) {
        if (context == null) {
            throw new RuntimeException("不存在ApplicationContext");
        }
        if (!StringUtils.hasText(beanName)) {
            throw new RuntimeException("beanName不能为空");
        }
        return context.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> requiredType) {
        if (context == null) {
            throw new RuntimeException("不存在ApplicationContext");
        }
        if (!StringUtils.hasText(beanName)) {
            throw new RuntimeException("beanName不能为空");
        }
        return context.getBean(beanName, requiredType);
    }

    public static <T> T getBean(Class<T> className) {
        if (context == null) {
            throw new RuntimeException("不存在ApplicationContext");
        }
        if (className == null) {
            throw new RuntimeException("className不能为空");
        }
        return context.getBean(className);
    }
}

