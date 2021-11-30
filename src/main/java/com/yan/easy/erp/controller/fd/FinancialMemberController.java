package com.yan.easy.erp.controller.fd;

import com.yan.easy.erp.model.Users;
import com.yan.easy.erp.dao.UserDao;
import com.yan.easy.erp.dao.UserRoleDao;
import com.yan.easy.erp.model.Roles;
import com.yan.easy.erp.model.UserRole;
import com.yan.easy.erp.service.RolesServiceImpl;
import com.yan.easy.erp.service.UserRoleServiceImpl;
import com.yan.easy.erp.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@RequestMapping("/fd/member")
@Controller
@Slf4j
public class FinancialMemberController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRoleServiceImpl userRoleServiceImpl;

    @Autowired
    private RolesServiceImpl rolesServiceImpl;

    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userServiceImpl.findByUserName(authentication.getName());
    }

    private Roles getUserRole(){
        UserRole userRole = userRoleServiceImpl.findUserRoleByUsersId(getUser().getId());
        log.info("userRole :"+userRole );
        return rolesServiceImpl.findRolesById(userRole.getId());
    }

    @GetMapping("is_manager")
    public ResponseEntity<Boolean> isManager(){
        Roles userRole = userRoleServiceImpl.findUserRoleByUsersId(getUser().getId()).getRoles();
        return ResponseEntity.ok(userRole.getName().contains("MANAGER"));
    }

    @GetMapping("get_account_users")
    public ResponseEntity<?> getRdUsers(){
        Long managerId = rolesServiceImpl.findRolesByName("FD_MANAGER").getId();
        Long userId = rolesServiceImpl.findRolesByName("FD_USER").getId();

        List<UserRole> accountUsers=userRoleServiceImpl.findUserRoleByRolesId(managerId);
        accountUsers.addAll(userRoleServiceImpl.findUserRoleByRolesId(userId));

        return ResponseEntity.ok(accountUsers);
    }

    @PostMapping("upload")
    @ResponseBody
    public ResponseEntity<?> updateFile(@RequestParam("files") MultipartFile uploadfiles, UserDao userDao) {
        String fileName = uploadfiles.getOriginalFilename();
        log.info("userDao : "+userDao.getId());
        if (userDao.getId() == null) {
            log.info("creat user");
            Users users = new Users();
            users.setUserName(userDao.getUserName());
            users.setPassword(userDao.getPassword());
            users.setEnable(userDao.isEnable());
            try {
                byte[] imageDecodeBytes = uploadfiles.getBytes();
                String imageString = Base64.getEncoder().encodeToString(imageDecodeBytes);
                users.setImage(imageString);
            } catch (IOException e) {
                log.info("error : "+e.getMessage());
            }

            userServiceImpl.saveUser(users);

            UserRole roleAdmin = new UserRole(users, rolesServiceImpl.findRolesByName(userDao.getRoleAdmin()));
            userRoleServiceImpl.saveUserRole(roleAdmin);

        } else {
            log.info("id :" + userDao.getId());
            log.info("user name :" + userDao.getUserName());
            log.info("password :" + userDao.getPassword());
            log.info("Role Admin :" + userDao.getRoleAdmin());
            log.info("Enable :" + userDao.isEnable());


            Users existedUsers = userServiceImpl.findUserByUserId(userDao.getId());
            existedUsers.setUserName(userDao.getUserName());
            existedUsers.setPassword(userDao.getPassword());
            existedUsers.setEnable(userDao.isEnable());
            try {
                byte[] imageDecodeBytes = uploadfiles.getBytes();
                String imageString = Base64.getEncoder().encodeToString(imageDecodeBytes);
                existedUsers.setImage(imageString);
            } catch (IOException e) {
                log.info("error : "+e.getMessage());
            }
            userServiceImpl.saveUser(existedUsers);

            UserRole oldRole =userRoleServiceImpl.findUserRoleByUsersId(existedUsers.getId());

            if (!Objects.equals(oldRole.getRoles().getName(), userDao.getRoleAdmin())) {
                oldRole.setRoles(rolesServiceImpl.findRolesByName(userDao.getRoleAdmin()));
                userRoleServiceImpl.saveUserRole(oldRole);
                return new ResponseEntity(fileName, HttpStatus.OK);
            }

        }

        return new ResponseEntity(fileName, HttpStatus.OK);

    }


    @GetMapping("edit/{id}")
    public ResponseEntity<?> editAdmin(@PathVariable("id") Long id) {
        Users users = userServiceImpl.findUserByUserId(id);
        UserRole userRoleList = userRoleServiceImpl.findUserRoleByUsersId(id);
        String role = userRoleList.getRoles().getName();

        UserRoleDao userRoleDao = new UserRoleDao(
                users.getId(),
                users.getUserName(),
                users.getPassword(),
                role,
                users.isEnable(),
                users.getEmail(),
                users.getPhone()
        );
        return ResponseEntity.ok().body(userRoleDao);
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable("id") Long id) {
        userRoleServiceImpl.deleteUserRoleByUserId(id);
        userServiceImpl.deleteUsersById(id);
        return ResponseEntity.ok("Id :" + id);
    }
}
