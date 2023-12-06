package com.zhurawell.base.data.repo.post;

import com.zhurawell.base.data.model.post.PostDetails;

import java.math.BigInteger;

public interface PostExtendedRepository {

    PostDetails getPostsDetailsById(BigInteger id);
}
