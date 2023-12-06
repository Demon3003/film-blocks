package com.zhurawell.base.service.user.impl;

import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.projection.user.UserLightView;
import com.zhurawell.base.data.repo.user.UserRepository;
import com.zhurawell.base.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserServicesImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    EntityManager em;

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public List<User> saveAllUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    @Transactional
    public List<User> saveAllUsersBatched(List<User> users, int batchSize) {
        em.unwrap(Session.class).setJdbcBatchSize(batchSize);
        List<User> savedUsers = userRepository.saveAll(users);
        return savedUsers;
    }

    @Transactional
    public void deleteAllUsersBatched(List<User> users) {
        userRepository.deleteAllInBatch(users);
    }

    public List<User> findAllByFirstName(String firstName) {
        return userRepository.findAllByFirstName(firstName);
    }

    public List<UserLightView> findByFirstNameLight(String firstName) {
        return userRepository.findByFirstNameLight(firstName);
    }

    public User findById(BigInteger id) {
        return userRepository.findById(id).get();
    }

    public User findByIdWithRole(BigInteger id) {
        EntityGraph eg = em.createEntityGraph(User.class);
        eg.addAttributeNodes("role");
        return em.find(User.class, id, Collections.singletonMap(
                "javax.persistence.loadgraph",
                eg
        ));
    }
}
