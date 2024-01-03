package com.zhurawell.base.api.controllers.user;

import com.zhurawell.base.api.dto.grants.RoleDto;
import com.zhurawell.base.api.mappers.RoleMapper;
import com.zhurawell.base.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.math.BigInteger;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private Scheduler jdbcScheduler;

    @PostMapping("/create")
    public Mono<Void> createRole(@RequestBody RoleDto role) {
       return Mono.fromRunnable(() ->
                roleService.createRole(roleMapper.dtoToEntity(role)))
                .subscribeOn(jdbcScheduler).then();
    }

    @PutMapping("/update")
    public Mono<Void> updateRole(@RequestBody RoleDto role) {
        return Mono.fromRunnable(() ->
                roleService.updateRole(roleMapper.dtoToEntity(role)))
                .subscribeOn(jdbcScheduler).then();
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteRole(@PathVariable("id") BigInteger id) {
        return Mono.fromRunnable(() ->
                roleService.deleteRoleById(id))
                .subscribeOn(jdbcScheduler).then();
    }

    @GetMapping("/get/{id}")
    public Mono<RoleDto> getRoleById(@PathVariable("id") BigInteger id) {
        return Mono.fromCallable(() ->
                roleService.getRoleById(id)).map(roleMapper::entityToDto)
                .subscribeOn(jdbcScheduler);

    }

    @PostMapping("/addPermission/{roleId}/{permissionId}")
    public Mono<Void> addPermission(@PathVariable("roleId") BigInteger roleId,
                                    @PathVariable("permissionId") BigInteger permissionId) {
        return Mono.fromRunnable(() ->
                roleService.addPermission(roleId, permissionId))
                .subscribeOn(jdbcScheduler).then();

    }
}
