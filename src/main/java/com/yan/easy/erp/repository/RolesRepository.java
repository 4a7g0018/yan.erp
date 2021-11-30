package com.yan.easy.erp.repository;

import com.yan.easy.erp.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles,Long> {
    Roles findRolesById(Long id);
    Roles findRolesByName(String name);
}
