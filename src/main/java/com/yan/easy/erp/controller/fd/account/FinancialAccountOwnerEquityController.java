package com.yan.easy.erp.controller.fd.account;

import com.yan.easy.erp.dao.AccountingAssetsDao;
import com.yan.easy.erp.dao.AccountingAssetsEditDao;
import com.yan.easy.erp.model.AccountOwnersEquity;
import com.yan.easy.erp.model.Users;
import com.yan.easy.erp.service.AccountOwnersEquityServiceImpl;
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

@RequestMapping("/fd/account/owner_equity")
@Controller
@Slf4j
public class FinancialAccountOwnerEquityController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AccountOwnersEquityServiceImpl accountOwnersEquityService;

    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userServiceImpl.findByUserName(authentication.getName());
    }


    @GetMapping("get_owner_equity")
    public ResponseEntity<List<AccountOwnersEquity>> getAccountOwnersEquity() {

        return ResponseEntity.ok().body(accountOwnersEquityService.findAllOwnersEquity());
    }

    @PostMapping("save")
    public ResponseEntity<?> saveAccountingOwnersEquity(AccountingAssetsDao accountingOwnersEquityDao) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(accountingOwnersEquityDao.getTime());

        AccountOwnersEquity accountOwnersEquity = new AccountOwnersEquity();
        accountOwnersEquity.setAccountName(accountingOwnersEquityDao.getAccountName());
        accountOwnersEquity.setDate(date);
        accountOwnersEquity.setAccountAmount(Integer.parseInt(accountingOwnersEquityDao.getAmount()));
        accountOwnersEquity.setRemark(accountingOwnersEquityDao.getRemark());

        accountOwnersEquityService.saveOwnersEquity(accountOwnersEquity);
        log.info("accountingAssetsDao :" + accountingOwnersEquityDao);
        log.info("date :" + date);
        return ResponseEntity.ok(accountingOwnersEquityDao);
    }

    @GetMapping("edit/{id}")
    public ResponseEntity<?> getOwnersEquityById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(accountOwnersEquityService.findOwnersEquityById(id));
    }

    @PostMapping("edit/save/{id}")
    public ResponseEntity<?> editAccountingOwnersEquity(@PathVariable("id") Long id, AccountingAssetsEditDao accountingOwnersEquityDao) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(accountingOwnersEquityDao.getTime());

        AccountOwnersEquity accountOwnersEquity = accountOwnersEquityService.findOwnersEquityById(id);

        accountOwnersEquity.setAccountName(accountingOwnersEquityDao.getAccountName());
        accountOwnersEquity.setDate(date);
        accountOwnersEquity.setAccountAmount(Integer.parseInt(accountingOwnersEquityDao.getAmount()));
        accountOwnersEquity.setRemark(accountingOwnersEquityDao.getRemark());

        accountOwnersEquityService.saveOwnersEquity(accountOwnersEquity);
        log.info("accountingAssetsDao :" + accountingOwnersEquityDao);
        log.info("date :" + date);
        return ResponseEntity.ok(accountingOwnersEquityDao);
    }

    @DeleteMapping("delete/{ownersEquityId}")
    public ResponseEntity<String> deleteAccountingOwnersEquity(@PathVariable("ownersEquityId") String ownersEquityId) {
        accountOwnersEquityService.deleteOwnersEquity(Long.parseLong(ownersEquityId));
        log.info("delete id : " + ownersEquityId);
        return ResponseEntity.ok().body("success delete assets id: " + ownersEquityId);
    }
}
