package com.zhurawell.base.data.repo.user;

import com.zhurawell.base.data.model.user.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, BigInteger> {
}
