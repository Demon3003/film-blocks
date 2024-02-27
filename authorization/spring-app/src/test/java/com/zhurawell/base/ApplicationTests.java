//package com.zhurawell.base;
//
//
//import com.zhurawell.base.data.model.user.User;
//import com.zhurawell.base.data.repo.user.UserRepository;
//import com.zhurawell.base.service.user.UserService;
//import com.zhurawell.base.service.user.impl.UserServicesImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.ActiveProfiles;
//import reactor.core.scheduler.Scheduler;
//import reactor.core.scheduler.Schedulers;
//
//import javax.persistence.EntityManagerFactory;
//import java.math.BigInteger;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;
//
//@Slf4j
//@ActiveProfiles("test")
//@SpringBootTest(classes = {UserServicesImpl.class})
//class ApplicationTests {
//
//	@Autowired
//	@InjectMocks
//	UserServicesImpl userService;
//
//	@MockBean
//	UserRepository userRepository;
//
//	@MockBean(name = "entityManagerFactory")
//	EntityManagerFactory entityManagerFactory;
//
//	@MockBean
//	private Scheduler jdbcScheduler;
//
//	@BeforeEach
//	public void setUpData() {
//		jdbcScheduler = Schedulers.newBoundedElastic(4, 4, "reactive-jdbc-pool");
//	}
//
//	@Test
//	void contextLoads() {
//		//Given
//		User u = new User(BigInteger.valueOf(1l));
//		when(userRepository.save(any(User.class))).thenReturn(u);
//
//		//When
//		u = userService.saveUserDetails(u).block();
//
//		//Then
//		assertThat(u.getId()).isEqualTo(1l);
//	}
//
//}
//
//
