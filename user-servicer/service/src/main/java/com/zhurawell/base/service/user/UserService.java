package com.zhurawell.base.service.user;

import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.projection.user.UserLightView;

import java.math.BigInteger;
import java.util.List;

public interface UserService {

    public User findById(BigInteger id);

    public User saveUser(User user);

    public List<User> saveAllUsers(List<User> users);

    public List<User> saveAllUsersBatched(List<User> users, int batchSize);

    public void deleteAllUsersBatched(List<User> users);

    public List<User> findAllByFirstName(String firstName);

    public List<UserLightView> findByFirstNameLight(String firstName);

    public User findByIdWithRole(BigInteger id);
}
