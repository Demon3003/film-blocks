package com.zhurawell.base;


import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.repo.user.UserRepository;
import com.zhurawell.base.service.user.UserService;
import com.zhurawell.base.service.user.impl.UserServicesImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManagerFactory;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(classes = {UserServicesImpl.class})
class ApplicationTests {

	@Autowired
	UserService userService;

	@MockBean
	UserRepository userRepository;

	@MockBean(name = "entityManagerFactory")
	EntityManagerFactory entityManagerFactory;

	@Test
	void contextLoads() {
		//Given
		User u = new User(BigInteger.valueOf(1l));
		when(userRepository.save(any(User.class))).thenReturn(u);

		//When
		u = userService.saveUser(u);

		//Then
		assertThat(u.getId()).isEqualTo(1l);
	}
}
