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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        for (Enum role : RolesList.class.getEnumConstants()){
            Roles roles = new Roles(role.toString());
            rolesServiceImpl.saveRoles(roles);
        }
    }

    @Test
    public void testAddRoleToUser(){
        List<String> user_list = new ArrayList<>();
        user_list.add("GENERAL_MANAGER");
        user_list.add("HR_MANAGER");
        user_list.add("HR_USER");
        user_list.add("RD_MANAGER");
        user_list.add("RD_USER");
        user_list.add("FD_MANAGER");
        user_list.add("FD_USER");

        for (String user :user_list){
            Users users=userServiceImpl.findByUserName(user);
            Roles roles=rolesServiceImpl.findRolesByName(user);
            UserRole userRole=new UserRole(users,roles);
            userRoleServiceImpl.saveUserRole(userRole);
        }
    }

    @Test
    public void testdsa() {

        Users users = userServiceImpl.findByUserName("test");
        Roles roles = rolesServiceImpl.findRolesByName("GENERAL_MANAGER");
        UserRole userRole = new UserRole(users, roles);
        userRoleServiceImpl.saveUserRole(userRole);

    }

    @Test
    public void testDeleteUserAndUserRole(){
        userRoleServiceImpl.deleteUserRoleByUserId(2L);
        userServiceImpl.deleteUsersById(2L);
    }
}
