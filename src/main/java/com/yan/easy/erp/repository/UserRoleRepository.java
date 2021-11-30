package com.yan.easy.erp.repository;

import com.yan.easy.erp.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findUserRoleById(Long id);

    UserRole findUserRoleByUsersId(Long id);

    List<UserRole> findUserRoleByRolesId(Long roleId);

    @Modifying
    @Transactional
    void deleteUserRoleByUsersId(Long id);
}
