package com.zhurawell.base.api.broker.processor;

import com.zhurawell.base.api.rest.dto.user.UserDto;
import com.zhurawell.base.api.rest.mapper.UserMapper;
import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.service.user.UserService;
import com.zhurawell.blocks.common.broker.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;
import java.util.function.Consumer;

@Configuration
public class UserMessageProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(UserMessageProcessor.class);

    private final UserService userService;

    private final UserMapper userMapper;

    @Autowired
    public UserMessageProcessor(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.userMapper = mapper;
    }

    @Bean
    public Consumer<Event<String, UserDto>> userCrudMessageProcessor() {
        return event -> {
            LOG.info("Process message created at {}...", event.getEventCreatedAt());

            switch (event.getEventType()) {
                case CREATE:
                    User user = userMapper.dtoToEntity(event.getData());
                    LOG.debug("Create user with Login: {}", user.getLogin());
                    userService.saveUser(user).block();
                    break;
                case DELETE:
                    break;
                default:
                    String errorMessage = "Incorrect event type: " + event.getEventType() + ", expected a CREATE or DELETE event";
                    LOG.warn(errorMessage);
                    throw new RuntimeException(errorMessage);
            }
        };
    }

}
