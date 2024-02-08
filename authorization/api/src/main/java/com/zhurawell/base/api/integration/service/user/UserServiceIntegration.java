package com.zhurawell.base.api.integration.service.user;

import com.zhurawell.base.api.dto.user.UserDto;
import com.zhurawell.base.api.integration.requests.user.CreateBaseUserRequest;
import reactor.core.publisher.Mono;

public interface UserServiceIntegration {
    
    public Mono<UserDto> createBaseUser(CreateBaseUserRequest user);
    
}
