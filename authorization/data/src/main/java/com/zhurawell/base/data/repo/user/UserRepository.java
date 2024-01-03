package com.zhurawell.base.data.repo.user;

import com.zhurawell.base.data.model.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger>, UserExtendedRepository {

    @EntityGraph(value = "g-user-role")
    Optional<User> findByEmailOrLogin(String email, String login);

    User findByLogin(String login);
}