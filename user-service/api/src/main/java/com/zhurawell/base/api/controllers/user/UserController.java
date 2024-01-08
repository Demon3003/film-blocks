package com.zhurawell.base.api.controllers.user;

import com.zhurawell.base.api.converters.UserRestConverter;
import com.zhurawell.base.api.dto.user.UserDto;
import com.zhurawell.base.api.mapper.UserMapper;
import com.zhurawell.base.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

/**
 * REST controller to manage data about users.
 * @author dimazhuravlyov
 * */
@Slf4j
@RestController
@RequestMapping("/user")
@PreAuthorize("hasAuthority('all:write') or hasAuthority('sysadm')")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/create")
    public Mono<UserDto> createUser(@RequestBody UserDto user) {
        user.setRegistrationDate(LocalDate.now());
        return userService.saveUser(userMapper.dtoToEntity(user)).map(userMapper::entityToDto);
    }

    @PutMapping("/update")
    public Mono<UserDto> updateUser(@RequestBody UserDto user) {
        return userService.saveUser(userMapper.dtoToEntity(user)).map(userMapper::entityToDto);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity> deleteUser(@PathVariable("id") BigInteger id) {
        return userService.deleteById(id).map(__ -> ResponseEntity.ok().build());
    }

    @GetMapping("/get/{id}")
    public Mono<UserDto> getUser(@PathVariable("id") BigInteger id) {
        return userService.findById(id).map(userMapper::entityToDto);
    }

    @GetMapping("/getByFirstName")
    public Flux<UserDto> findAllByFirstName(@RequestParam("firstName") String name) {
        return userService.findAllByFirstName(name).map(userMapper::entityToDto);
    }

    /**
     * @see  UserRestConverter
     * */
    @GetMapping("/get/new/")
    public Mono<UserDto> getUserNew(UserDto user) {
        return userService.findByLogin(user.getLogin()).map(userMapper::entityToDto);
    }

    @GetMapping("/getAllActiveFrom/{date}")
    public Flux<UserDto> getUser(@PathVariable("date") Long activeFrom) {
        return userService.findByRegistrationDateAfter(new Date(activeFrom)).map(userMapper::entityToDto);
    }
}
