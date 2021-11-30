package com.yan.easy.erp.controller.fd.account;

import com.yan.easy.erp.dao.AccountingAssetsDao;
import com.yan.easy.erp.dao.AccountingAssetsEditDao;
import com.yan.easy.erp.model.AccountRevenue;
import com.yan.easy.erp.model.Users;
import com.yan.easy.erp.service.AccountRevenueServiceImpl;
import com.yan.easy.erp.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/fd/account/revenue")
@Controller
@Slf4j
public class FinancialAccountRevenueController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AccountRevenueServiceImpl accountRevenueService;

    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userServiceImpl.findByUserName(authentication.getName());
    }


    @GetMapping("get_revenue")
    public ResponseEntity<List<AccountRevenue>> getAccountRevenue() {

        return ResponseEntity.ok().body(accountRevenueService.findAllRevenue());
    }

    @PostMapping("save")
    public ResponseEntity<?> saveAccountingRevenue(AccountingAssetsDao accountingRevenueDao) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(accountingRevenueDao.getTime());

        AccountRevenue accountRevenue = new AccountRevenue();
        accountRevenue.setAccountName(accountingRevenueDao.getAccountName());
        accountRevenue.setDate(date);
        accountRevenue.setAccountAmount(Integer.parseInt(accountingRevenueDao.getAmount()));
        accountRevenue.setRemark(accountingRevenueDao.getRemark());

        accountRevenueService.saveRevenue(accountRevenue);
        log.info("accountingRevenueDao :" + accountingRevenueDao);
        log.info("date :" + date);
        return ResponseEntity.ok(accountingRevenueDao);
    }

    @GetMapping("edit/{id}")
    public ResponseEntity<?> getRevenueById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(accountRevenueService.findRevenueById(id));
    }

    @PostMapping("edit/save/{id}")
    public ResponseEntity<?> editAccountingAssets(@PathVariable("id") Long id, AccountingAssetsEditDao accountingRevenueDao) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(accountingRevenueDao.getTime());

        AccountRevenue accountRevenue = accountRevenueService.findRevenueById(id);

        accountRevenue.setAccountName(accountingRevenueDao.getAccountName());
        accountRevenue.setDate(date);
        accountRevenue.setAccountAmount(Integer.parseInt(accountingRevenueDao.getAmount()));
        accountRevenue.setRemark(accountingRevenueDao.getRemark());

        accountRevenueService.saveRevenue(accountRevenue);
        log.info("accountingAssetsDao :" + accountingRevenueDao);
        log.info("date :" + date);
        return ResponseEntity.ok(accountingRevenueDao);
    }

    @DeleteMapping("delete/{revenueId}")
    public ResponseEntity<String> deleteAccountingRevenue(@PathVariable("revenueId") String revenueId) {
        accountRevenueService.deleteRevenue(Long.parseLong(revenueId));
        log.info("delete id : " + revenueId);
        return ResponseEntity.ok().body("success delete revenue id: " + revenueId);
    }
}
