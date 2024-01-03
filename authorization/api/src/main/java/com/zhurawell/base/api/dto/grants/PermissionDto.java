package com.zhurawell.base.api.dto.grants;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
public class PermissionDto  {

    private BigInteger id;

    @JsonProperty("authority")
    private String permissionName;

}
