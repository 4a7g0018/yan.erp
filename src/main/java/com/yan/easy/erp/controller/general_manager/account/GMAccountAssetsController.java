package com.yan.easy.erp.controller.general_manager.account;

import com.yan.easy.erp.dao.AccountingAssetsDao;
import com.yan.easy.erp.dao.AccountingAssetsEditDao;
import com.yan.easy.erp.model.AccountAssets;
import com.yan.easy.erp.model.Users;
import com.yan.easy.erp.service.AccountAssetsServiceImpl;
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
import java.util.*;

@RequestMapping("/gm/account/assets")
@Controller
@Slf4j
public class GMAccountAssetsController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AccountAssetsServiceImpl accountAssetsServiceImpl;

    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userServiceImpl.findByUserName(authentication.getName());
    }


    @GetMapping("get_assets")
    public ResponseEntity<List<AccountAssets>> getAccountAssets() {

        return ResponseEntity.ok().body(accountAssetsServiceImpl.findAllAssets());
    }

    @PostMapping("save")
    public ResponseEntity<?> saveAccountingAssets(AccountingAssetsDao accountingAssetsDao) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(accountingAssetsDao.getTime());

        AccountAssets accountAssets = new AccountAssets();
        accountAssets.setAccountName(accountingAssetsDao.getAccountName());
        accountAssets.setDate(date);
        accountAssets.setAccountAmount(Integer.parseInt(accountingAssetsDao.getAmount()));
        accountAssets.setRemark(accountingAssetsDao.getRemark());

        accountAssetsServiceImpl.saveAssets(accountAssets);
        log.info("accountingAssetsDao :" + accountingAssetsDao);
        log.info("date :" + date);
        return ResponseEntity.ok(accountingAssetsDao);
    }

    @GetMapping("edit/{id}")
    public ResponseEntity<?> getAssetsById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(accountAssetsServiceImpl.findAssetById(id));
    }

    @PostMapping("edit/save/{id}")
    public ResponseEntity<?> editAccountingAssets(@PathVariable("id") Long id, AccountingAssetsEditDao accountingAssetsDao) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(accountingAssetsDao.getTime());

        AccountAssets accountAssets = accountAssetsServiceImpl.findAssetById(id);

        accountAssets.setAccountName(accountingAssetsDao.getAccountName());
        accountAssets.setDate(date);
        accountAssets.setAccountAmount(Integer.parseInt(accountingAssetsDao.getAmount()));
        accountAssets.setRemark(accountingAssetsDao.getRemark());

        accountAssetsServiceImpl.saveAssets(accountAssets);
        log.info("accountingAssetsDao :" + accountingAssetsDao);
        log.info("date :" + date);
        return ResponseEntity.ok(accountingAssetsDao);
    }

    @DeleteMapping("delete/{assetsId}")
    public ResponseEntity<String> deleteAccountingAssets(@PathVariable("assetsId") String assetsId) {
        accountAssetsServiceImpl.deleteAssets(Long.parseLong(assetsId));
        log.info("delete id : " + assetsId);
        return ResponseEntity.ok().body("success delete assets id: " + assetsId);
    }
}
