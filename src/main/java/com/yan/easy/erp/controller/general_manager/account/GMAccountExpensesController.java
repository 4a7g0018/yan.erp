package com.yan.easy.erp.controller.general_manager.account;

import com.yan.easy.erp.model.Users;
import com.yan.easy.erp.dao.AccountingAssetsDao;
import com.yan.easy.erp.dao.AccountingAssetsEditDao;
import com.yan.easy.erp.model.AccountExpenses;
import com.yan.easy.erp.service.AccountExpensesServiceImpl;
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

@RequestMapping("/gm/account/expenses")
@Controller
@Slf4j
public class GMAccountExpensesController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AccountExpensesServiceImpl accountExpensesService;

    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userServiceImpl.findByUserName(authentication.getName());
    }


    @GetMapping("get_expenses")
    public ResponseEntity<List<AccountExpenses>> getAccountExpenses() {

        return ResponseEntity.ok().body(accountExpensesService.findAllExpenses());
    }

    @PostMapping("save")
    public ResponseEntity<?> saveAccountingExpenses(AccountingAssetsDao accountingExpensesDao) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(accountingExpensesDao.getTime());

        AccountExpenses accountExpenses = new AccountExpenses();
        accountExpenses.setAccountName(accountingExpensesDao.getAccountName());
        accountExpenses.setDate(date);
        accountExpenses.setAccountAmount(Integer.parseInt(accountingExpensesDao.getAmount()));
        accountExpenses.setRemark(accountingExpensesDao.getRemark());

        accountExpensesService.saveExpenses(accountExpenses);

        return ResponseEntity.ok(accountingExpensesDao);
    }

    @GetMapping("edit/{id}")
    public ResponseEntity<?> getExpensesById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(accountExpensesService.findExpensesById(id));
    }

    @PostMapping("edit/save/{id}")
    public ResponseEntity<?> editAccountingExpenses(@PathVariable("id") Long id, AccountingAssetsEditDao accountingExpensesDao) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(accountingExpensesDao.getTime());

        AccountExpenses accountExpenses = accountExpensesService.findExpensesById(id);

        accountExpenses.setAccountName(accountingExpensesDao.getAccountName());
        accountExpenses.setDate(date);
        accountExpenses.setAccountAmount(Integer.parseInt(accountingExpensesDao.getAmount()));
        accountExpenses.setRemark(accountingExpensesDao.getRemark());

        accountExpensesService.saveExpenses(accountExpenses);

        return ResponseEntity.ok(accountingExpensesDao);
    }

    @DeleteMapping("delete/{expensesId}")
    public ResponseEntity<String> deleteAccountingExpenses(@PathVariable("expensesId") String expensesId) {
        accountExpensesService.deleteExpenses(Long.parseLong(expensesId));
        log.info("delete id : " + expensesId);
        return ResponseEntity.ok().body("success delete expenses id: " + expensesId);
    }
}
