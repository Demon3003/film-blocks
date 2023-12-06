//package com.app.art.registry.repo.user;
//
//import com.app.art.registry.model.user.User;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//
//import java.math.BigInteger;
//
//
//@Slf4j
//@DataJpaTest
//public class UserRepoTest {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    private TestEntityManager testEntityManager;
//
//    @Test
//    public void testGetById() {
//        log.error("DMZH: {}", userRepository);
//        User u = new User(BigInteger.ONE);
//    }
//}