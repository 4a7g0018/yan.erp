package com.yan.easy.erp.controller;

import com.yan.easy.erp.model.Users;
import com.yan.easy.erp.service.UserRoleServiceImpl;
import com.yan.easy.erp.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRoleServiceImpl userRoleServiceImpl;

    @GetMapping()
    public String indexPage(Model model){

        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName=authentication.getName();
            Users users =userServiceImpl.findByUserName(currentPrincipalName);
            String headshot = users.getImage();

            model.addAttribute("pass", true);
            model.addAttribute("no", false);
            model.addAttribute("headShot","data:image/jpg;base64,"+headshot);


                switch (userRoleServiceImpl.findUserRoleByUsersId(users.getId()).getRoles().getName()) {
                    case "GENERAL_MANAGER" :
                        return "gm/index";

                    case "FD_MANAGER":
                        return "fd/home";

                    case "FD_USER":
                        return "fd/home";

                    case "RD_MANAGER":
                        return "rd/home";

                    case "RD_USER":
                        return "rd/home";

//                    case "HR_MANAGER":
//                        return "hr/hr_index";
//
//                    case "HR_USER":
//                        return "hr/hr_index";

                    default:
                        return null;
                }
//                if(Objects.equals(role.getRoles().getName(),"ADMIN")){
//                    return "admin/index";
//                }


        }catch (Exception e){
            log.info("error : "+e.getMessage());
            return null;
        }
    }
}
