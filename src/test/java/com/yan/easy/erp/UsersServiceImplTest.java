package com.yan.easy.erp;

import com.yan.easy.erp.model.Users;
import com.yan.easy.erp.repository.UserRepository;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.io.File;
import java.util.Base64;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UsersServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserSave() {
        try {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            Users users = new Users();
            Users users = userRepository.findUserById(2L);
//            users.setUserName("admin1");
            String password = "admin1";
//            byte[] imageDecodeBytes = FileUtils.readFileToByteArray(new File("D:\\spring-boot\\yan.security.practise\\src\\main\\java\\com\\yan\\security\\practise\\yan\\security\\practise\\test.jpg"));
            byte[] imageDecodeBytes = FileUtils.readFileToByteArray(new File("D:\\spring-boot\\yan.security.practise\\src\\main\\java\\com\\yan\\security\\practise\\yan\\security\\practise\\test.jpg"));
            String imageString = Base64.getEncoder().encodeToString(imageDecodeBytes);

            password = passwordEncoder.encode(password);
            users.setPassword(password);
//            users.setUsersRoles(List.of(new Roles("ADMIN"),new Roles("ROLE_USER")));
            users.setEnable(true);
            users.setImage(imageString);

            Users saveUsers = userRepository.save(users);
            Assertions.assertThat(saveUsers).isNotNull();
            Assertions.assertThat(saveUsers.getId()).isGreaterThan(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testFindByUserName() {
        System.out.println("------------------------------------");
        System.out.println(userRepository.findUserById(1l));
    }

//    @Test
//    public void testEditUserById(){
//        Users users =userRepository.findUserBySql(1l);
//        System.out.println("-------------------------------");
//        System.out.println(users);
//        users.setUsersRoles(List.of(new usersRoles("ADMIN"),new usersRoles("USER_ROLE")));
//        userRepository.save(users);
//    }
}
