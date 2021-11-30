package com.yan.easy.erp.service;

import com.yan.easy.erp.model.AccountRevenue;
import com.yan.easy.erp.repository.AccountRevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRevenueServiceImpl implements AccountRevenueService{

    @Autowired
    private AccountRevenueRepository accountRevenueRepository;

    @Override
    public List<AccountRevenue> findAllRevenue() {
        return accountRevenueRepository.findAllAccountRevenueDESC();
    }

    @Override
    public AccountRevenue saveRevenue(AccountRevenue accountRevenue) {
        return accountRevenueRepository.save(accountRevenue);
    }

    @Override
    public void deleteRevenue(Long id) {
        accountRevenueRepository.deleteById(id);
    }

    @Override
    public AccountRevenue findRevenueById(Long id) {
        return accountRevenueRepository.findRevenueById(id);
    }

    @Override
    public int findSumRevenue() {
        return accountRevenueRepository.findSumRevenueRepository();
    }

    @Override
    public int findSumRevenueByYear(int year) {
        String yearFormat = String.valueOf(year)+"_%";
        return accountRevenueRepository.findSumRevenueRepositoryByYear(yearFormat);
    }

}
