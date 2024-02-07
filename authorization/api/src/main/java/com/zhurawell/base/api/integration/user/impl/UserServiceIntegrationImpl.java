package com.zhurawell.base.api.integration.user.impl;

import com.zhurawell.base.api.constants.HttpHeaders;
import com.zhurawell.base.api.constants.Permissions;
import com.zhurawell.base.api.constants.ServiceUrl;
import com.zhurawell.base.api.dto.user.UserDto;
import com.zhurawell.base.api.integration.user.UserServiceIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserServiceIntegrationImpl implements UserServiceIntegration {

    private WebClient webClient;

    @Autowired
    public UserServiceIntegrationImpl(WebClient.Builder webClientBuilder) {
        this.webClient=webClientBuilder.build();
    }
    
    public Mono<UserDto> createBaseUser(UserDto user) {
        return this.webClient.post()
                .uri(ServiceUrl.CREATE_BASE_USER)
                .header(HttpHeaders.LOGIN_HEADER, user.getLogin())
                .header(HttpHeaders.AUTHORITY_HEADER, Permissions.SYSADM)
                .body(user, UserDto.class)
                .retrieve().bodyToMono(UserDto.class);
    }
}
