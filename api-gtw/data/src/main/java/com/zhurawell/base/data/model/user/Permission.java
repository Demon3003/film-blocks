package com.zhurawell.base.data.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Setter
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "permission_generator", sequenceName = "permission_seq", schema = "public", allocationSize = 10)
public class Permission implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "permission_generator")
    private BigInteger id;

    @Column(name = "permission_name")
    private String permissionName;

    @Transient // to ignore field for hibernate
    private String comment;

    Permission(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public String getAuthority() {
        return permissionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Permission))
            return false;

        Permission other = (Permission) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permission='" + permissionName + '\'' +
                '}';
    }
}