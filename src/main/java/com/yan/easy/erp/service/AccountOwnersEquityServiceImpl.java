package com.yan.easy.erp.service;

import com.yan.easy.erp.model.AccountOwnersEquity;
import com.yan.easy.erp.repository.AccountOwnersEquityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountOwnersEquityServiceImpl implements AccountOwnersEquityService {

    @Autowired
    private AccountOwnersEquityRepository accountOwnersEquityRepository;

    @Override
    public List<AccountOwnersEquity> findAllOwnersEquity() {
        return accountOwnersEquityRepository.findAllOwnersEquityDESC();
    }

    @Override
    public AccountOwnersEquity saveOwnersEquity(AccountOwnersEquity accountOwnersEquity) {
        return accountOwnersEquityRepository.save(accountOwnersEquity);
    }

    @Override
    public void deleteOwnersEquity(Long id) {
        accountOwnersEquityRepository.deleteById(id);
    }

    @Override
    public AccountOwnersEquity findOwnersEquityById(Long id) {
        return accountOwnersEquityRepository.findOwnersEquityById(id);
    }

    @Override
    public int findSumOwnersEquity() {
        return accountOwnersEquityRepository.findSumOwnersEquity();
    }

    @Override
    public int findSumOwnersEquityByYear(int year) {
        String yearFormat = String.valueOf(year)+"_%";
        return accountOwnersEquityRepository.findSumOwnersEquityByYear(yearFormat);

    }

}
