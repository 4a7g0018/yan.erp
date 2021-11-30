package com.yan.easy.erp.repository;

import com.yan.easy.erp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;


public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUserName(String userName);
    Users findUserById(Long id);
//    @Query(value = "select * from users where id = ?1",nativeQuery = true)
//    Users findUserBySql(Long id);

    @Modifying
    @Transactional
    void deleteUsersById(Long userId);

}
