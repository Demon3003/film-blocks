package com.zhurawell.base.data.repo.post;

import com.zhurawell.base.data.model.post.Post;
import com.zhurawell.base.data.model.post.PostComment;
import com.zhurawell.base.data.projection.post.PostLight;
import com.zhurawell.base.data.projection.post.PostLightExtended;
import org.hibernate.jpa.QueryHints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, BigInteger>, PostExtendedRepository {

    PostLightExtended findPostByTitle(String title);

    @Query("select p from Post p where p.id in (select pc.id from PostComment pc where pc.id = ?1)")
    Post findPostByCommentId(BigInteger id);

    @Query("select p from Post p left join PostDetails pd on pd.id = p.id where pd.createdOn < function('current_date') - 2")
    List<Post> getPostIdAndCurrentDate();

    @Query("select p from Post p join fetch p.postDetails where p.postComments is empty and p.postDetails.createdOn > current_date() - 7")
    List<Post> getLatestPostsWithoutComments();

    @Query("select p from Post p where size(p.postComments) > ?1")
    List<Post> getPostsWithCommentsAmount(int commentsNumber);

    @Query("select p from Post p where ?1 member of p.postComments")
    List<Post> getPostsWithComment(PostComment comment);

    @org.springframework.data.jpa.repository.QueryHints({
            @QueryHint(name = QueryHints.HINT_NATIVE_SPACES, value = "com.zhurawell.base.data.model.post.Post")})
    @Query(nativeQuery = true, value = "select text from post where id = ?1")
    String getPostTextById(BigInteger id);

    @org.springframework.data.jpa.repository.QueryHints({
            @QueryHint(name = QueryHints.HINT_NATIVE_SPACES, value = "com.app.art.registry.model.post.Post")})
    @Query(nativeQuery = true, value = "select id as id, text as text, title as title from post where id = ?1")
    PostLight getPostLight(BigInteger id);

    /**
     * Here we use NamedNativeQuery inside
     * @see Post
     * with name = Post.getPostMainInfo
     * */
    @Query(nativeQuery = true)
    @org.springframework.data.jpa.repository.QueryHints({
            @QueryHint(name = QueryHints.HINT_NATIVE_SPACES, value = "com.app.art.registry.model.post.Post")})
    PostLightExtended getPostMainInfo(@Param("id") BigInteger id);

}
