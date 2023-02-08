package com.shang.otp.config;

import com.shang.otp.common.CommonBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shangwei2009@hotmail.com on 2020/9/10 19:15
 */
@Configuration
public class CommonBeanConfig {
    @Bean
    @ConditionalOnMissingBean
    public CommonBeanFactory commonBeanFactory() {
        return new CommonBeanFactory();
    }
}
