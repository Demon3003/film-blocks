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
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(classes = {UserServicesImpl.class})
class ApplicationTests {

	@Autowired
	UserService userService;

	@MockBean
	UserRepository userRepository;

	@MockBean
	R2dbcEntityTemplate et;

	@Test
	void contextLoads() {
		//Given
		User u = new User(BigInteger.valueOf(1l));
		when(userRepository.save(any(User.class))).thenReturn(Mono.just(u));

		//When
		u = userService.saveUser(u).block();

		//Then
		assertThat(u.getId()).isEqualTo(1l);
	}
}
