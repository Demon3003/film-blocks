package com.zhurawell.base.data.repo.post;

import com.zhurawell.base.data.model.post.PostComment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.Optional;

public interface PostCommentRepo extends JpaRepository<PostComment, BigInteger> {

    @Override
    @EntityGraph(value = "PostComment.post.postDetails", type = EntityGraph.EntityGraphType.LOAD)
    Optional<PostComment> findById(BigInteger bigInteger);

    @Query(nativeQuery = true, value = "select id, text from post_comment where id = ?1")
    Tuple getTextById(BigInteger bigInteger);


}
