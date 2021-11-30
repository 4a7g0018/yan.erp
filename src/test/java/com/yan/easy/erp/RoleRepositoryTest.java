package com.yan.easy.erp;

import com.yan.easy.erp.model.Users;
import com.yan.easy.erp.service.RolesServiceImpl;
import com.yan.easy.erp.model.Roles;
import com.yan.easy.erp.model.UserRole;
import com.yan.easy.erp.service.UserRoleServiceImpl;
import com.yan.easy.erp.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    private RolesServiceImpl rolesServiceImpl;

    @Autowired
    private UserRoleServiceImpl userRoleServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    public void testCreateRole(){
//        Roles admin=new Roles("ADMIN");
//        rolesServiceImpl.saveRoles(admin);
//
//        Roles userRole =new Roles("USER_ROLE");
//        rolesServiceImpl.saveRoles(userRole);

        for (Enum role : RolesList.class.getEnumConstants()){
            Roles roles = new Roles(role.toString());
            rolesServiceImpl.saveRoles(roles);
        }
    }

    @Test
    public void testAddRoleToUser(){

        Users users=userServiceImpl.findUserByUserId(9L);

//        Roles roles=rolesServiceImpl.findRolesByName(RolesList.GENNERAL_MANAGER.name());
//        Roles roles=rolesServiceImpl.findRolesByName(RolesList.ACCOUNT_MANAGER.name());
//        Roles roles=rolesServiceImpl.findRolesByName(RolesList.ACCOUNT_USER.name());
//        Roles roles=rolesServiceImpl.findRolesByName(RolesList.RD_MANAGER.name());
//        Roles roles=rolesServiceImpl.findRolesByName(RolesList.RD_USER.name());
//        Roles roles=rolesServiceImpl.findRolesByName(RolesList.HR_MANAGER.name());
//        Roles roles=rolesServiceImpl.findRolesByName(RolesList.HR_USER.name());
        Roles roles=rolesServiceImpl.findRolesByName("USER_ROLE");
        UserRole userRole=new UserRole(users,roles);
        userRoleServiceImpl.saveUserRole(userRole);
    }

    @Test
    public void testDeleteUserAndUserRole(){
        userRoleServiceImpl.deleteUserRoleByUserId(2L);
        userServiceImpl.deleteUsersById(2L);
    }
}
