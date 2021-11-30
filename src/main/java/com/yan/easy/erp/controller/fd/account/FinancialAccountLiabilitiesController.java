package com.yan.easy.erp.controller.fd.account;

import com.yan.easy.erp.dao.AccountingAssetsDao;
import com.yan.easy.erp.dao.AccountingAssetsEditDao;
import com.yan.easy.erp.model.AccountLiabilities;
import com.yan.easy.erp.model.Users;
import com.yan.easy.erp.service.AccountLiabilitiesServiceImpl;
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

@RequestMapping("/fd/account/liabilities")
@Controller
@Slf4j
public class FinancialAccountLiabilitiesController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AccountLiabilitiesServiceImpl accountLiabilitiesService;

    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userServiceImpl.findByUserName(authentication.getName());
    }


    @GetMapping("get_liabilities")
    public ResponseEntity<List<AccountLiabilities>> getAccountLiabilities() {

        return ResponseEntity.ok().body(accountLiabilitiesService.findAllLiabilities());
    }

    @PostMapping("save")
    public ResponseEntity<?> saveAccountingLiabilities(AccountingAssetsDao accountingLiabilitiesDao) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(accountingLiabilitiesDao.getTime());

        AccountLiabilities accountLiabilities = new AccountLiabilities();
        accountLiabilities.setAccountName(accountingLiabilitiesDao.getAccountName());
        accountLiabilities.setDate(date);
        accountLiabilities.setAccountAmount(Integer.parseInt(accountingLiabilitiesDao.getAmount()));
        accountLiabilities.setRemark(accountingLiabilitiesDao.getRemark());

        accountLiabilitiesService.saveLiabilities(accountLiabilities);
        log.info("accountingLiabilitiesDao :" + accountingLiabilitiesDao);
        log.info("date :" + date);
        return ResponseEntity.ok(accountingLiabilitiesDao);
    }

    @GetMapping("edit/{id}")
    public ResponseEntity<?> getLiabilitiesById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(accountLiabilitiesService.findLiabilitiesById(id));
    }

    @PostMapping("edit/save/{id}")
    public ResponseEntity<?> editAccountingLiabilities(@PathVariable("id") Long id, AccountingAssetsEditDao accountingLiabilitiesDao) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(accountingLiabilitiesDao.getTime());

        AccountLiabilities accountLiabilities = accountLiabilitiesService.findLiabilitiesById(id);

        accountLiabilities.setAccountName(accountingLiabilitiesDao.getAccountName());
        accountLiabilities.setDate(date);
        accountLiabilities.setAccountAmount(Integer.parseInt(accountingLiabilitiesDao.getAmount()));
        accountLiabilities.setRemark(accountingLiabilitiesDao.getRemark());

        accountLiabilitiesService.saveLiabilities(accountLiabilities);
        log.info("accountingAssetsDao :" + accountingLiabilitiesDao);
        log.info("date :" + date);
        return ResponseEntity.ok(accountingLiabilitiesDao);
    }

    @DeleteMapping("delete/{liabilitiesId}")
    public ResponseEntity<String> deleteAccountingLiabilities(@PathVariable("liabilitiesId") String liabilitiesId) {
        accountLiabilitiesService.deleteLiabilities(Long.parseLong(liabilitiesId));
        log.info("delete id : " + liabilitiesId);
        return ResponseEntity.ok().body("success delete liabilities id " + liabilitiesId);
    }
}
