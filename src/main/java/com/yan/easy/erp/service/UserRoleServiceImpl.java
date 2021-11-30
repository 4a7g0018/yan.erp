package com.yan.easy.erp.service;

import com.yan.easy.erp.model.UserRole;
import com.yan.easy.erp.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService{

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserRole findUserRoleById(Long id) {
        return userRoleRepository.findUserRoleById(id);
    }

    @Override
    public UserRole findUserRoleByUsersId(Long userId) {
        return userRoleRepository.findUserRoleByUsersId(userId);
    }

    @Override
    public List<UserRole> findUserRoleByRolesId(Long roleId) {
        return userRoleRepository.findUserRoleByRolesId(roleId);
    }

    @Override
    public UserRole saveUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public void deleteUserRoleByUserId(Long userId) {
        userRoleRepository.deleteUserRoleByUsersId(userId);
    }
}
