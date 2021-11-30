package com.yan.easy.erp.service;

import com.yan.easy.erp.model.UserRole;

import java.util.List;

public interface UserRoleService {
    UserRole findUserRoleById(Long id);

    UserRole findUserRoleByUsersId(Long userId);

    List<UserRole> findUserRoleByRolesId(Long roleId);

    UserRole saveUserRole(UserRole userRole);

    void deleteUserRoleByUserId(Long userId);
}
