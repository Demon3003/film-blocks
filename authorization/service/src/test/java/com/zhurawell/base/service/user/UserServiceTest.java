package com.zhurawell.base.service.user;

import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.repo.user.UserRepository;
import com.zhurawell.base.service.user.impl.UserServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.scheduler.Scheduler;

import javax.persistence.EntityManagerFactory;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {UserServicesImpl.class, Scheduler.class})
public class UserServiceTest {

    @Autowired
    private UserService userServices;

    @MockBean
    private UserRepository userRepository;

    @MockBean(name = "entityManagerFactory")
    EntityManagerFactory entityManagerFactory;

    @MockBean
    private Scheduler scheduler;

    @BeforeEach
    public void setUpData() {
        when(userRepository.save(any(User.class))).thenReturn(new User(BigInteger.ONE));
    }

    @Test
    public void test_save() {
//        assertThat(userServices.saveUserDetails(new User(BigInteger.ONE)).block()).isEqualTo(new User(BigInteger.ONE));
    }

}
