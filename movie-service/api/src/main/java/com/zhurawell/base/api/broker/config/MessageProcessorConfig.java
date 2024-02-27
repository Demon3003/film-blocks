package com.zhurawell.base.api.broker.config;

import com.zhurawell.base.api.rest.dto.user.UserDto;
import com.zhurawell.blocks.common.broker.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;
import java.util.function.Consumer;

@Configuration
public class MessageProcessorConfig {

    private static final Logger LOG = LoggerFactory.getLogger(MessageProcessorConfig.class);

    @Autowired
    public MessageProcessorConfig() {
    }

    @Bean
    public Consumer<Event<BigInteger, UserDto>> messageProcessor() {
        return event -> {
            LOG.info("Process message created at {}...", event.getEventCreatedAt());
        };
    }

}
