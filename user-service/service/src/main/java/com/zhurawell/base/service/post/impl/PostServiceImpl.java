package com.zhurawell.base.service.post.impl;

import com.zhurawell.base.service.post.PostService;
import com.zhurawell.base.data.model.post.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

@Service
public class PostServiceImpl implements PostService {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Post createPost(Post p) {
        em.persist(p);
        return p;
    }

    @Override
    @Transactional
    public void deletePost(BigInteger id) {

    }
}
