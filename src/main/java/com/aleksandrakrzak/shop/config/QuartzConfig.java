package com.aleksandrakrzak.shop.config;

import com.aleksandrakrzak.shop.scheduler.SimpleQuartzScheduler;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob()
                .ofType(SimpleQuartzScheduler.class)
                .storeDurably()
                .withIdentity("casio")
                .withDescription("calculator")
                .build();
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetail jobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();

        cronTriggerFactoryBean.setJobDetail(jobDetail);
        cronTriggerFactoryBean.setCronExpression("*/10 * * ? * * *");

        cronTriggerFactoryBean.setStartDelay(5000);
        cronTriggerFactoryBean.setName("use_log");

        return cronTriggerFactoryBean;
    }

}
