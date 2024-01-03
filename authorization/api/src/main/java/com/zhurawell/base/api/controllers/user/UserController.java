package com.zhurawell.base.api.controllers.user;

import com.zhurawell.base.api.dto.user.UserDto;
import com.zhurawell.base.api.mappers.UserMapper;
import com.zhurawell.base.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.math.BigInteger;

/**
 * REST controller to manage data about users.
 * @author dimazhuravlyov
 * */
@Slf4j
@RestController
@RequestMapping("/userDetails")
@PreAuthorize("hasAuthority('all:write') or hasAuthority('sysadm')")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Scheduler jdbcScheduler;


    @PostMapping("/register")
    public Mono<UserDto> createUserDetail(@RequestBody UserDto user) {
        log.debug("User: {}", user);
        return  Mono.fromCallable(() ->
                userService.saveUser(userMapper.dtoToEntity(user)))
                .map(userMapper::entityToDto)
                .subscribeOn(jdbcScheduler);
    }

    @PutMapping("/update")
    public Mono<UserDto> updateUserDetails(@RequestBody UserDto user) {
        log.debug("User: {}", user);
        return  Mono.fromCallable(() ->
                userService.saveUser(userMapper.dtoToEntity(user)))
                .map(userMapper::entityToDto)
                .subscribeOn(jdbcScheduler);
    }

    @GetMapping("/get/{id}")
    public Mono<UserDto> getUserDetails(@PathVariable("id") BigInteger id) {
        return Mono.fromCallable(() ->
                userService.findById(id))
                .map(userMapper::entityToDto)
                .subscribeOn(jdbcScheduler);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteUserDetails(@PathVariable("id") BigInteger id) {
         return Mono.fromRunnable(() -> userService.deleteById(id)).subscribeOn(jdbcScheduler).then();
    }

}
