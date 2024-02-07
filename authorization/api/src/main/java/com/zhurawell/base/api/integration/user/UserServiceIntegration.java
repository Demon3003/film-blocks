package com.zhurawell.base.api.integration.user;

import com.zhurawell.base.api.dto.user.UserDto;
import reactor.core.publisher.Mono;

public interface UserServiceIntegration {
    
    public Mono<UserDto> createBaseUser(UserDto user);
    
}
