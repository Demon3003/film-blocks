package com.zhurawell.base.api.mapper;

import com.zhurawell.base.api.dto.user.UserDto;
import com.zhurawell.base.data.model.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserDto entityToDto(User user);

    public User dtoToEntity(UserDto dto);

    List<UserDto> entityListToDtoList(List<User> entity);

    List<User> dtoListToEntityList(List<UserDto> api);
}
