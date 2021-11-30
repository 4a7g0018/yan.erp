package com.yan.easy.erp.service;

import com.yan.easy.erp.model.AccountLiabilities;

import java.util.List;

public interface AccountLiabilitiesService {
    List<AccountLiabilities> findAllLiabilities();
    AccountLiabilities saveLiabilities(AccountLiabilities accountLiabilities);
    void deleteLiabilities(Long id);
    AccountLiabilities findLiabilitiesById(Long id);
    int findSumLiabilities();
    int findSumLiabilitiesByYear(int year);
}
