package com.zhurawell.base.data.model.post;

import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.projection.post.PostLightExtended;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Set;

@Table(name = "post")
@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "post_seq", sequenceName = "post_sequence", schema = "public", allocationSize = 20)
@NamedNativeQuery(name = "Post.getPostMainInfo",
        query = "select id, title, text from post where id = :id",
        resultSetMapping = "mapping.getPostMainInfo")
@SqlResultSetMapping(name = "mapping.getPostMainInfo",
        classes =
    @ConstructorResult(targetClass = PostLightExtended.class,
            columns = {
                @ColumnResult(name = "id"),
                @ColumnResult(name = "text"),
                @ColumnResult(name = "title")
            }))
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    private BigInteger id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Fetch(value = FetchMode.JOIN)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "post")
    @LazyToOne(value = LazyToOneOption.NO_PROXY)
    private PostDetails postDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private Set<PostComment> postComments;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
