package com.zhurawell.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
public class JdbcSchedulerConfig {


    private final Integer threadPoolSize;
    private final Integer taskQueueSize;

    @Autowired
    public JdbcSchedulerConfig(
            @Value("${jdbc.scheduler.threadPoolSize}") Integer threadPoolSize,
            @Value("${jdbc.scheduler.taskQueueSize}") Integer taskQueueSize
    ) {
        this.threadPoolSize = threadPoolSize;
        this.taskQueueSize = taskQueueSize;
    }

    @Bean
    public Scheduler jdbcScheduler() {
        return Schedulers.newBoundedElastic(threadPoolSize, taskQueueSize, "reactive-jdbc-pool");
    }
}
