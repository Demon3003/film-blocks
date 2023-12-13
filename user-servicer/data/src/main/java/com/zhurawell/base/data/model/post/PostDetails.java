package com.zhurawell.base.data.model.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "post_details")
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(name = "post_det_seq", sequenceName = "post_det_sequence", allocationSize = 20, schema = "public")
public class PostDetails {

    @Id
    @GeneratedValue(generator = "post_det_seq")
    private BigInteger id;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "tags")
    private String tags;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @MapsId
    private Post post;

    public PostDetails(String createdBy) {
        createdOn = new Date();
        this.createdBy = createdBy;
    }

}
