package com.zhurawell.base.api.mappers;

import com.zhurawell.base.api.dto.grants.PermissionDto;
import com.zhurawell.base.data.model.user.Permission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionDto entityToDto(Permission entity);

    Permission dtoToEntity(PermissionDto api);

    List<PermissionDto> entityListToApiList(List<Permission> entity);

    List<Permission> apiListToEntityList(List<PermissionDto> api);
}
