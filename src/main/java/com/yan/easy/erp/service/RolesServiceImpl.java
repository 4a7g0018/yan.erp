package com.yan.easy.erp.service;

import com.yan.easy.erp.model.Roles;
import com.yan.easy.erp.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements RolesService{

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public Roles saveRoles(Roles roles) {
        return rolesRepository.save(roles);
    }

    @Override
    public Roles findRolesById(Long id) {
        return rolesRepository.findRolesById(id);
    }

    @Override
    public Roles findRolesByName(String roleName) {
        return rolesRepository.findRolesByName(roleName);
    }
}
