package com.zhurawell.base.api.dto.grants;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto {

    private BigInteger id;

    private String name;

    @JsonProperty("authorities")
    private List<PermissionDto> permissions;
}
