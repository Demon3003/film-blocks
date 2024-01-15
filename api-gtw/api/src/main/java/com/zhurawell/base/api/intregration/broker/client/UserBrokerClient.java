package com.zhurawell.base.api.intregration.broker.client;

import com.zhurawell.base.api.dto.user.UserDto;
import com.zhurawell.base.api.intregration.broker.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Component
public class UserBrokerClient extends GenericBrokerClient {

    private final Scheduler publishEventScheduler;

    @Autowired
    UserBrokerClient(StreamBridge streamBridge, Scheduler publishEventScheduler) {
        super(streamBridge);
        this.publishEventScheduler = publishEventScheduler;
    }

    public Mono<UserDto> createUser(UserDto user) {
        return Mono.fromCallable(() -> {
            sendMessage("products-out-0", new Event(Event.Type.CREATE, user.getLogin(), user));
            return user;
        }).subscribeOn(publishEventScheduler);
    }
}
