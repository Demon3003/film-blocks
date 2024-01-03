package com.zhurawell.base.data.model.post;

import com.zhurawell.base.data.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(name = "post_comment")
@NoArgsConstructor
@SequenceGenerator(name = "post_comment_seq", sequenceName = "post_comment_sequence", allocationSize = 50)
@NamedEntityGraphs({
        @NamedEntityGraph(name = "PostComment.post",attributeNodes = @NamedAttributeNode("post")),
        @NamedEntityGraph(name = "PostComment.post.postDetails", attributeNodes = @NamedAttributeNode(value = "post", subgraph = "post.postDetails"),
        subgraphs = {@NamedSubgraph(name = "post.postDetails", attributeNodes = @NamedAttributeNode("postDetails"))})
})
public class PostComment {

    @Id
    @GeneratedValue(generator = "post_comment_seq")
    private BigInteger id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "PostComment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
