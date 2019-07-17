package com.kitcut.helloworld.springboot.config;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {
//    @Scheduled(cron = "* * * * * ?")
    public void cronJobSch() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Java cron job expression:: " + strDate);
        Thread.sleep(5000);
    }

//    @Scheduled(fixedRate = 1000)
    public void fixedRateSch() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Java cron job expression:: " + strDate);
        Thread.sleep(5000);
    }

//    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void fixedDelaySch() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Java cron job expression:: " + strDate);
        Thread.sleep(5000);
    }
}
