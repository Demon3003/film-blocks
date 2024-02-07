package com.zhurawell.base.data.repo.user;

import com.zhurawell.base.data.model.user.User;

import java.util.List;

public interface UserExtendedRepository {

    void updateUsersInBatch(List<User> users);
}
