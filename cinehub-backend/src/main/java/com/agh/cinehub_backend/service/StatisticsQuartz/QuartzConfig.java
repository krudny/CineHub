package com.agh.cinehub_backend.service.StatisticsQuartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail ratingsCalculatorJobDetail() {
        return JobBuilder.newJob(MovieRatingsJob.class)
                .withIdentity("MovieRatingsJob")
                .storeDurably()
                .build();
    }
    @Bean
    public Trigger ratingsCalculatorJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInHours(24)
                .withIntervalInMinutes(1)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(ratingsCalculatorJobDetail())
                .withIdentity("ratingsCalculatorTrigger")
                .withSchedule(scheduleBuilder)
                .startNow()
                .build();
    }

    @Bean
    public JobDetail statisticsJobDetail() {
        return JobBuilder.newJob(StatisticsJob.class)
                .withIdentity("statisticsJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger statisticsJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInHours(1)
                .withIntervalInMinutes(1)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(statisticsJobDetail())
                .withIdentity("statisticsTrigger")
                .withSchedule(scheduleBuilder)
                .startNow()
                .build();
    }
}
