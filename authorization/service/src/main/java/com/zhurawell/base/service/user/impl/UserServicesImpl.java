package com.zhurawell.base.service.user.impl;

import com.zhurawell.base.data.model.user.Status;
import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.repo.user.UserRepository;
import com.zhurawell.base.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.math.BigInteger;

@Service
@Slf4j
public class UserServicesImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public User registerNewUser(User user) {
        user.setStatus(Status.NEW);
        return saveUserDetails(user);
    }

    @Override
    @Transactional
    public User saveUserDetails(User user) {
        return userRepository.save(user);
    }


    public User findById(BigInteger id) {
        return userRepository.findById(id).get();
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    public void deleteById(BigInteger id) {
        userRepository.deleteById(id);
    }


}
