package com.zhurawell.base.api.dto.grants;

import com.zhurawell.base.api.dto.BaseDto;
import com.zhurawell.base.data.model.user.Permission;

import java.math.BigInteger;

public class PermissionDto extends BaseDto<Permission> {

    public PermissionDto(Permission pojo) {
        super(pojo);
    }

    public PermissionDto() {
        super(new Permission());
    }

    public BigInteger getId() {
        return getPojo().getId();
    }

    public void setId(BigInteger id) {
        this.getPojo().setId(id);
    }

    public String getPermissionName() {
        return getPojo().getPermissionName();
    }

    public void setPermissionName(String permissionName) {
        this.getPojo().setPermissionName(permissionName);
    }
}
