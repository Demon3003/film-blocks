package com.zhurawell.base.api.mappers;

import com.zhurawell.base.api.dto.grants.RoleDto;
import com.zhurawell.base.data.model.user.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto entityToDto(Role entity);

    Role dtoToEntity(RoleDto api);

    List<RoleDto> entityListToApiList(List<Role> entity);

    List<Role> apiListToEntityList(List<RoleDto> api);
}
