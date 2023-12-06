package com.zhurawell.base.service.post;

import com.zhurawell.base.data.model.post.Post;

import java.math.BigInteger;

public interface PostService {

    Post createPost(Post p);

    void deletePost(BigInteger id);

}
