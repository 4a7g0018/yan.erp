package com.yan.easy.erp;

import com.yan.easy.erp.model.Users;
import com.yan.easy.erp.repository.UserRepository;
import com.yan.easy.erp.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.io.File;
import java.util.*;

@SpringBootTest
@Slf4j
public class UsersServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testUserSave() {

        List<String> userNameList = new ArrayList<>();
        List<String> userImageList = new ArrayList<>();
        List<String> userPhoneList = new ArrayList<>();
        List<String> userMailList = new ArrayList<>();

        userNameList.add("GENERAL_MANAGER");
        userImageList.add("D:\\spring-boot\\yan.security.practise\\src\\main\\java\\com\\yan\\easy\\erp\\headshot\\GENERAL-MANAGER.jpg");
        userPhoneList.add("0989123456");
        userMailList.add("GENERAL_MANAGER@gmail.com");

        userNameList.add("HR_MANAGER");
        userImageList.add("D:\\spring-boot\\yan.security.practise\\src\\main\\java\\com\\yan\\easy\\erp\\headshot\\HR-MANAGER.jpg");
        userPhoneList.add("0989147852");
        userMailList.add("HR_MANAGER@gmail.com");

        userNameList.add("HR_USER");
        userImageList.add("D:\\spring-boot\\yan.security.practise\\src\\main\\java\\com\\yan\\easy\\erp\\headshot\\HR-USER.jpg");
        userPhoneList.add("0989963258");
        userMailList.add("HR_USER@gmail.com");

        userNameList.add("RD_MANAGER");
        userImageList.add("D:\\spring-boot\\yan.security.practise\\src\\main\\java\\com\\yan\\easy\\erp\\headshot\\RD-MANAGER.jpg");
        userPhoneList.add("0989987456");
        userMailList.add("RD_MANAGER@gmail.com");

        userNameList.add("RD_USER");
        userImageList.add("D:\\spring-boot\\yan.security.practise\\src\\main\\java\\com\\yan\\easy\\erp\\headshot\\RD-USER.jpg");
        userPhoneList.add("0989852147");
        userMailList.add("RD_MANAGER@gmail.com");

        userNameList.add("FD_MANAGER");
        userImageList.add("D:\\spring-boot\\yan.security.practise\\src\\main\\java\\com\\yan\\easy\\erp\\headshot\\FD-MANAGER.jpg");
        userPhoneList.add("0989987456");
        userMailList.add("RD_MANAGER@gmail.com");

        userNameList.add("FD_USER");
        userImageList.add("D:\\spring-boot\\yan.security.practise\\src\\main\\java\\com\\yan\\easy\\erp\\headshot\\FD-USER.jpg");
        userPhoneList.add("0989325698");
        userMailList.add("RD_MANAGER@gmail.com");

//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {

            for (String userName:userNameList){
                int index = userNameList.indexOf(userName);

                byte[] imageDecodeBytes = FileUtils.readFileToByteArray(new File(userImageList.get(index)));
                String imageString = Base64.getEncoder().encodeToString(imageDecodeBytes);
//                String password = userName;
//                password = passwordEncoder.encode(password);

                Users user = new Users();
                user.setUserName(userName);
                user.setPassword(userName);
//                user.setPassword(passwordEncoder.encode(userName));
                user.setEnable(true);
                user.setImage(imageString);
                user.setEmail(userMailList.get(index));
                user.setPhone(userPhoneList.get(index));
                userService.saveUser(user);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
