package com.zhurawell.base.data.model.user;

import com.zhurawell.base.data.user.UserStatusConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users") //TODO Replace magic strings by constants
@SequenceGenerator(name = "user_generator", schema = "public", sequenceName = "user_seq", allocationSize = 50)
@NamedEntityGraph(name = "g-user-role", attributeNodes =
    @NamedAttributeNode(value = "role", subgraph = "sub-role-permission"),
    subgraphs = @NamedSubgraph(name = "sub-role-permission", attributeNodes = @NamedAttributeNode(value = "permissions")))
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "main.user")
public class User {

    @Id
    @GeneratedValue(generator = "user_generator")
    private BigInteger id;

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    private String email;

    private String image;

    private Date registrationDate;

    private String token;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User(BigInteger id) {
        this.id = id;
    }

    @Column(name = "status_id")
    @Convert(converter = UserStatusConverter.class)
    private Status status;

//    @Fetch(value = FetchMode.JOIN) // JOIN and SUBSELECT we use for EAGER  fetchType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    public UserDetails getUserDetails() {
        boolean isActive = Status.ACTIVE.equals(this.getStatus());
        return new org.springframework.security.core.userdetails.User(
                this.getLogin(),
                this.getPassword(),
                isActive,
                isActive,
                isActive,
                isActive,
                this.getRole().getPermissions()
        );
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;

        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return User.class.hashCode();
    }
}
