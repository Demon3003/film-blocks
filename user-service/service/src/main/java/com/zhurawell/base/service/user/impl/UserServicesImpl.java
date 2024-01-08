package com.zhurawell.base.service.user.impl;

import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.repo.user.UserRepository;
import com.zhurawell.base.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.util.Date;

@Service
@Slf4j
public class UserServicesImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    R2dbcEntityTemplate entityTemplate;

    public Mono<User> saveUser(User user) {
        return userRepository.save(user);
    }

    public Flux<User> findAllByFirstName(String firstName) {
        return userRepository.findByFirstnameContaining(firstName);
    }

    @Override
    public Mono<User> findById(BigInteger id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(BigInteger id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Mono<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Flux<User> findByRegistrationDateAfter(Date date) {
        return userRepository.findByRegistrationDateAfter(date);
    }

}
