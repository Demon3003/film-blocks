package com.zhurawell.base.service.user;

import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.projection.user.UserLightView;

import java.math.BigInteger;
import java.util.List;

public interface UserService {

    public User findById(BigInteger id);

    public User saveUser(User user);

    public User findByLogin(String login);

}
