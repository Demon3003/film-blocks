package com.zhurawell.base.api.converters;

import com.zhurawell.base.data.model.user.User;
import org.springframework.core.convert.converter.Converter;

public class UserRestConverter implements Converter<String, User> {
    @Override
    public User convert(String  login) {
        User u = new User();
        u.setLogin(login);
        return u;
    }
}
