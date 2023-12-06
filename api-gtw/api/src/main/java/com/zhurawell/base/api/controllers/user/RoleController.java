package com.zhurawell.base.api.controllers.user;

import com.zhurawell.base.api.dto.grants.RoleDto;
import com.zhurawell.base.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity createRole(@RequestBody RoleDto role) {
        roleService.createRole(role.getPojo());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity updateRole(@RequestBody RoleDto role) {
        roleService.updateRole(role.getPojo());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRole(@PathVariable("id") BigInteger id) {
        roleService.deleteRoleById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable("id") BigInteger id) {
        return ResponseEntity.ok(new RoleDto(roleService.getRoleById(id)));
    }

    @PostMapping("/addPermission/{roleId}/{permissionId}")
    public ResponseEntity addPermission(@PathVariable("roleId") BigInteger roleId,
                                        @PathVariable("permissionId") BigInteger permissionId) {
        roleService.addPermission(roleId, permissionId);
        return ResponseEntity.ok().build();
    }
}
