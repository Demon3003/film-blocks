package com.zhurawell.base;

import com.zhurawell.base.data.repo.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class Application {


	@Autowired
	UserRepository userRepository;

//	@Autowired
//	private PostRepository postRepository;
//
//	@Autowired
//	private UserRepository userRepository;
////
//	@PersistenceContext
//	EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void run() {

	}

}
