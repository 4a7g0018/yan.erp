package com.yan.easy.erp.controller.fd;

import com.yan.easy.erp.model.*;
import com.yan.easy.erp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/fd/account")
@Controller
public class FinancialAccountController {

    @Autowired
    private UserServiceImpl userServiceImpl;

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

    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userServiceImpl.findByUserName(authentication.getName());
    }

    @GetMapping("get_assets")
    public ResponseEntity<?> getAssets(){
        List<AccountAssets> accountAssetsList = accountAssetsServiceImpl.findAllAssets();
        return  ResponseEntity.ok(accountAssetsList);
    }

    @GetMapping("get_liabilities")
    public ResponseEntity<?> getLiabilities(){
        List<AccountLiabilities> accountLiabilitiesList = accountLiabilitiesServiceImpl.findAllLiabilities();

        return  ResponseEntity.ok(accountLiabilitiesList);
    }

    @GetMapping("get_owner_equity")
    public ResponseEntity<?> getOwnersEquity(){
        List<AccountOwnersEquity> accountOwnersEquityList = accountOwnersEquityServiceImpl.findAllOwnersEquity();
        return  ResponseEntity.ok(accountOwnersEquityList);
    }

    @GetMapping("get_revenue")
    public ResponseEntity<?> getRevenuesList(){
        List<AccountRevenue> accountRevenuesList = accountRevenueServiceImpl.findAllRevenue();
        return  ResponseEntity.ok(accountRevenuesList);
    }

    @GetMapping("get_expenses")
    public ResponseEntity<?> getExpensesEquity(){
        List<AccountExpenses> accountExpensesList = accountExpensesServiceImpl.findAllExpenses();
        return  ResponseEntity.ok(accountExpensesList);
    }

    @GetMapping("assets")
    public String showAssetsPage(Model model){
        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());
        return "fd/account/assets";
    }

    @GetMapping("liabilities")
    public String showLiabilitiesPage(Model model){
        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());
        return "fd/account/liabilities";
    }

    @GetMapping("owners_equity")
    public String showOwnersEquityPage(Model model){
        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());
        return "fd/account/owner_equity";
    }

    @GetMapping("revenue")
    public String showRevenuePage(Model model){
        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());
        return "fd/account/revenue";
    }

    @GetMapping("expenses")
    public String showExpensesPage(Model model){
        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());
        return "fd/account/expenses";
    }

    @GetMapping("all")
    public String showAllPage(Model model){
        model.addAttribute("headShot", "data:image/jpg;base64," + getUser().getImage());
        return "fd/account/all";
    }
}
