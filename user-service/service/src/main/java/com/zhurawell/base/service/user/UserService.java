package com.zhurawell.base.service.user;

import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.projection.user.UserLightView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface UserService {

    public Mono<User> findById(BigInteger id);

    public Mono<User> saveUser(User user);

    public Flux<User> findAllByFirstName(String firstName);

    Mono<Void> deleteById(BigInteger id);

    Mono<User> findByLogin(String login);

    Flux<User> findByRegistrationDateAfter(Date date);
}
