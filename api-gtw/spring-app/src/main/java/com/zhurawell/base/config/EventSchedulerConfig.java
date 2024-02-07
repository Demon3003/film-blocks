package com.zhurawell.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
public class EventSchedulerConfig {

    @Value("${app.threadPoolSize:10}")
    private Integer threadPoolSize;

    @Value("${app.taskQueueSize:100}")
    private Integer taskQueueSize;

    @Bean
    public Scheduler publishEventScheduler() {
        return Schedulers.newBoundedElastic(threadPoolSize, taskQueueSize, "publish-pool");
    }

}
