package com.zhurawell.base.service.user;

import com.zhurawell.base.data.model.user.User;

import java.math.BigInteger;

public interface UserService {

    public User findById(BigInteger id);

    public User saveUser(User user);

    public User findByLogin(String login);

    public void deleteById(BigInteger id);

}
