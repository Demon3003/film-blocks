package com.zhurawell.base.api.rest.converters;

import com.zhurawell.base.api.rest.dto.user.UserDto;
import org.springframework.core.convert.converter.Converter;

public class UserRestConverter implements Converter<String, UserDto> {
    @Override
    public UserDto convert(String  login) {
        UserDto u = new UserDto();
        u.setLogin(login);
        return u;
    }
}
