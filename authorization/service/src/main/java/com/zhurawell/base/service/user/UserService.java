package com.zhurawell.base.service.user;

import com.zhurawell.base.data.model.user.User;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

public interface UserService {

    public User findById(BigInteger id);

    public User registerNewUser(User user);

    public User saveUserDetails(User user);

    public User findByLogin(String login);

    public void deleteById(BigInteger id);

}
