package com.dcits.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author xuzzhh
 * @Date 2020/2/21 20:45
 * @Version 1.0
 * @Since study
 */
@EnableScheduling
@Component
@Slf4j
public class AutoTask {
    @Scheduled(cron="0 0 23 * * ?")
    private void process(){
        log.info("autoTask ");
    }
}
