package com.yan.easy.erp.service;

import com.yan.easy.erp.model.Roles;

public interface RolesService {
    Roles saveRoles(Roles roles);
    Roles findRolesById(Long id);
    Roles findRolesByName(String roleName);
}
