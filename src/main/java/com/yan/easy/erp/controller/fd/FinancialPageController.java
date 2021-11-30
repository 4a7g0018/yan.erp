package com.yan.easy.erp.controller.fd;

import com.yan.easy.erp.dao.WorkListDao;
import com.yan.easy.erp.model.Users;
import com.yan.easy.erp.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("fd")
@Controller
@Slf4j
class FinancialPageController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userServiceImpl.findByUserName(authentication.getName());
    }

    @GetMapping("index")
    public String showAdminIndexPage(Model model) {
        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());
        return "fd/index";
    }

    @GetMapping("home")
    public String showAdminHomePage(Model model) {
        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());
        return "fd/home";
    }

    @GetMapping("account")
    public String showAdminAccountIndexPage(Model model) {
        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());
        return "fd/account_home";
    }

    @GetMapping("work_list")
    public String showAdminNewPage1Page(Model model) {
        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());
        model.addAttribute("work_list", new WorkListDao());
        return "fd/work_list";
    }



    @GetMapping("member")
    public String showAdminNewPage2Page(Model model) {

        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());

        return "fd/member";
    }

    @GetMapping("food")
    public String showFoodPage(Model model) {
        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());
        return "fd/food";
    }
}
