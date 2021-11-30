package com.yan.easy.erp.service;

import com.yan.easy.erp.model.AccountRevenue;

import java.util.List;

public interface AccountRevenueService {
    List<AccountRevenue> findAllRevenue();
    AccountRevenue saveRevenue(AccountRevenue accountRevenue);
    void deleteRevenue(Long id);
    AccountRevenue findRevenueById(Long id);
    int findSumRevenue();
    int findSumRevenueByYear(int year);
}
