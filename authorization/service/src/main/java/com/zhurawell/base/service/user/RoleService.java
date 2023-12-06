package com.zhurawell.base.service.user;

import com.zhurawell.base.data.projection.user.RoleLightView;
import com.zhurawell.base.data.model.user.Role;

import java.math.BigInteger;

public interface RoleService {

    Role createRole(Role r);

    Role updateRole(Role r);

    void deleteRoleById(BigInteger id);

    Role getRoleById(BigInteger id);

    RoleLightView getRoleLightById(BigInteger id);

    Role getRoleByName(String name);

    void addPermission(BigInteger roleId, BigInteger permissionId);

}
