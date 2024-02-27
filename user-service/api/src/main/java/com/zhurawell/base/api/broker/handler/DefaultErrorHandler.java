package com.zhurawell.base.api.broker.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.ErrorMessage;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class DefaultErrorHandler {

    @Bean
    public Consumer<ErrorMessage> defaultMessageErrorHandler() {
        return m -> {
            log.error("Unexpected error during message processing", m.getPayload());
        };
    }
}
