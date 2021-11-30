package com.yan.easy.erp.service;

import com.yan.easy.erp.model.Users;

import java.util.List;

public interface UserService {
    Users saveUser(Users users);
    List<Users> findAllUser();
    Users findUserByUserId(Long id);
    Users findByUserName(String userName);
    void deleteUsersById(Long userId);
}
