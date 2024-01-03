package com.zhurawell.base.api.controllers.user;

import com.zhurawell.base.api.dto.BaseDto;
import com.zhurawell.base.api.dto.user.UserDto;
import com.zhurawell.base.api.mappers.UserMapper;
import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.repo.user.UserRepository;
import com.zhurawell.base.api.converters.UserRestConverter;
import com.zhurawell.base.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

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

    private UserMapper userMapper;


    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {

        userService.saveUser(userMapper.dtoToEntity(user));
        log.debug("DMZH TEST: {}", user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user) {
        userService.saveUser(userMapper.dtoToEntity(user));
        log.debug("DMZH TEST: {}", user);
        return ResponseEntity.ok(user);
    }

    /**
     * RSET endpoint to test functionality of the
     * @see  UserRestConverter
     * example of a request: http://localhost:8086/api/user/get/new/?user=Dmytro
     * */
    @GetMapping("/get/new/")
    public ResponseEntity<User> getUserNew(User user) {
        return ResponseEntity.ok(userService.findByLogin(user.getLogin()));
    }

}
