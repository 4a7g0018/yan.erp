package com.yan.easy.erp.controller;

import com.yan.easy.erp.model.Users;
import com.yan.easy.erp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    private Users getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userServiceImpl.findByUserName(authentication.getName());
    }

    @GetMapping("/index")
    public String showUserIndexPage(Model model){
        model.addAttribute("headShot","data:image/jpg;base64,"+getUser().getImage());
        return "user/index";
    }

    @GetMapping("/home")
    public String showUserHomePage(Model model){
        model.addAttribute("headShot","data:image/jpg;base64,"+getUser().getImage());
        return "user/home";
    }

    @GetMapping("/accounting")
    public String showUserAccountingPage(Model model){
        model.addAttribute("headShot","data:image/jpg;base64,"+getUser().getImage());
        return "user/accounting";
    }

    @GetMapping("/new-page-1")
    public String showUserNewPage1Page(Model model){
        model.addAttribute("headShot","data:image/jpg;base64,"+getUser().getImage());
        return "user/new-page-1";
    }

    @GetMapping("/new-page-2")
    public String showUserNewPage2Page(Model model){
        model.addAttribute("headShot","data:image/jpg;base64,"+getUser().getImage());
        return "user/new-page-2";
    }

}
