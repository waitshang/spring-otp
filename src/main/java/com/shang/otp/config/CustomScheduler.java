package com.shang.otp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by shangwei2009@hotmail.com on 2023/2/2 13:49
 */
@Configuration
@EnableScheduling
@Slf4j
public class CustomScheduler {

//    @Scheduled(cron = "* * * * * ?")
    public void clock() {
        log.info("clock");
    }

//    @Scheduled(cron = "0,30 * * * * ?")
    public void timedOTP() {
        log.error("otp");
    }
}
