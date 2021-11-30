package com.yan.easy.erp.service;

import com.yan.easy.erp.model.Users;
import com.yan.easy.erp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleServiceImpl userRoleServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try {
            Users users = userRepository.findByUserName(userName);

            List<SimpleGrantedAuthority> authorities =new ArrayList<>();
//            for (UserRole userRole : userRoleServiceImpl.findUserRoleByUsersId(users.getId())){
//                authorities.add(new SimpleGrantedAuthority(userRole.getRoles().getName()));
//            }
            SimpleGrantedAuthority role = new SimpleGrantedAuthority(userRoleServiceImpl.findUserRoleByUsersId(users.getId()).getRoles().getName());
            switch (role.toString()){
                case "RD_MANAGER":
                    authorities.add(new SimpleGrantedAuthority("RD_USER"));
                case "ACCOUNT_MANAGER":
                    authorities.add(new SimpleGrantedAuthority("ACCOUNT_USER"));
            }
//            if (role.toString().contains("RD_MANAGER")){
//                authorities.add(new SimpleGrantedAuthority("RD_USER"));
//            }
            authorities.add(role);

            return new org.springframework.security.core.userdetails.User(users.getUserName(), users.getPassword(), authorities);
        } catch (Exception e) {
            log.error("Error message {}", e.getMessage());
            throw new UsernameNotFoundException("Users is not found");
        }
    }

    @Override
    public Users saveUser(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));


        return userRepository.save(users);
    }

    @Override
    public List<Users> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Users findUserByUserId(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public Users findByUserName(String userName) {

        return userRepository.findByUserName(userName);
    }

    @Override
    public void deleteUsersById(Long userId) {
        userRepository.deleteUsersById(userId);
    }


}
