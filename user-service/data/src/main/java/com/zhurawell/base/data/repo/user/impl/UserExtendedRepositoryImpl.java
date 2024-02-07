package com.zhurawell.base.data.repo.user.impl;

import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.repo.user.UserExtendedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserExtendedRepositoryImpl implements UserExtendedRepository {

    @Autowired
    R2dbcEntityTemplate et;

    public void updateUsersInBatch(List<User> users) {
        users.stream().forEach(u -> et.update(users));
    }
}
