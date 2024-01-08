package com.zhurawell.base.service.user;

import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.repo.user.UserRepository;
import com.zhurawell.base.service.user.impl.UserServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {UserServicesImpl.class})
public class UserServiceTest {

    @Autowired
    private UserService userServices;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    R2dbcEntityTemplate et;

    @BeforeEach
    public void setUpData() {
        when(userRepository.save(any(User.class))).thenReturn(Mono.just(new User(BigInteger.ONE)));
    }

    @Test
    public void test_save() {
        assertThat(userServices.saveUser(new User(BigInteger.ONE)).block()).isEqualTo(new User(BigInteger.ONE));
    }

}
