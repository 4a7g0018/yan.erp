package com.yan.easy.erp.controller.rd;

import com.yan.easy.erp.dao.WorkListDao;
import com.yan.easy.erp.model.Users;
import com.yan.easy.erp.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rd")
@Controller
@Slf4j
public class RDPageController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RolesServiceImpl rolesServiceImpl;

    @Autowired
    private UserRoleServiceImpl userRoleServiceImpl;

    @Autowired
    private AccountAssetsServiceImpl accountAssetsServiceImpl;

    @Autowired
    private AccountExpensesServiceImpl accountExpensesServiceImpl;

    @Autowired
    private AccountLiabilitiesServiceImpl accountLiabilitiesServiceImpl;

    @Autowired
    private AccountOwnersEquityServiceImpl accountOwnersEquityServiceImpl;

    @Autowired
    private AccountRevenueServiceImpl accountRevenueServiceImpl;

    @Autowired
    private WorkListServiceImpl workListService;

    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userServiceImpl.findByUserName(authentication.getName());
    }

    @GetMapping("index")
    public String showAdminIndexPage(Model model) {
        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());
        return "rd/index";
    }

    @GetMapping("home")
    public String showAdminHomePage(Model model) {
        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());
        return "rd/home";
    }

    @GetMapping("work_list")
    public String showAdminNewPage1Page(Model model) {
        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());
//        model.addAttribute("allWorkList", workListService.findAllWorkList());
        model.addAttribute("work_list", new WorkListDao());
        return "rd/work_list";
    }



    @GetMapping("member")
    public String showAdminNewPage2Page(Model model) {

        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());

        return "rd/member";
    }

    @GetMapping("food")
    public String showFoodPage(Model model) {
        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());
        return "rd/food";
    }
}
