package com.zhurawell.base.api.mappers;


import com.zhurawell.base.api.dto.user.UserDto;
import com.zhurawell.base.api.integration.requests.user.CreateBaseUserRequest;
import com.zhurawell.base.data.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;
    
    public abstract UserDto entityToDto(User entity);

    @Mapping(target = "password",
            expression = "java(user.getPassword() == null ? null : passwordEncoder.encode(user.getPassword()))")
    public abstract User dtoToEntity(UserDto user);

    public abstract List<UserDto> entityListToApiList(List<User> entity);

    public abstract List<User> apiListToEntityList(List<UserDto> api);

    public abstract CreateBaseUserRequest dtoToCreateRequest(UserDto user);
}
