package com.zhurawell.base.api.mappers;


import com.zhurawell.base.api.dto.user.UserDto;
import com.zhurawell.base.data.model.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDto entityToDto(User entity);

    User dtoToEntity(UserDto api);

    List<UserDto> entityListToApiList(List<User> entity);

    List<User> apiListToEntityList(List<UserDto> api);
}
