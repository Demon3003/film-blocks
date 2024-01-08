package com.zhurawell.base.data.repo.user;

import com.zhurawell.base.data.model.user.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.util.Date;

@Repository
public interface UserRepository extends R2dbcRepository<User, BigInteger>, UserExtendedRepository {

    Flux<User> findByRegistrationDateAfter(Date afterDate);

    Mono<User> findByLogin(String login);
    Flux<User> findByFirstnameContaining(String firstName);

}