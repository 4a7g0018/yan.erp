package com.yan.easy.erp.service;

import com.yan.easy.erp.model.AccountLiabilities;
import com.yan.easy.erp.repository.AccountLiabilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountLiabilitiesServiceImpl implements AccountLiabilitiesService {

    @Autowired
    private AccountLiabilitiesRepository accountLiabilitiesRepository;

    @Override
    public List<AccountLiabilities> findAllLiabilities() {
        return accountLiabilitiesRepository.findAllLiabilitiesDESC();
    }

    @Override
    public AccountLiabilities saveLiabilities(AccountLiabilities accountLiabilities) {
        return accountLiabilitiesRepository.save(accountLiabilities);
    }

    @Override
    public void deleteLiabilities(Long id) {
        accountLiabilitiesRepository.deleteById(id);
    }

    @Override
    public AccountLiabilities findLiabilitiesById(Long id) {
        return accountLiabilitiesRepository.findLiabilitiesById(id);
    }

    @Override
    public int findSumLiabilities() {
        return accountLiabilitiesRepository.findSumLiabilities();
    }

    @Override
    public int findSumLiabilitiesByYear(int year) {
        String yearFormat = String.valueOf(year)+"_%";
        return accountLiabilitiesRepository.findSumLiabilitiesByYear(yearFormat);
    }

}
