package com.yan.easy.erp.service;

import com.yan.easy.erp.model.AccountOwnersEquity;
import java.util.List;

public interface AccountOwnersEquityService {
    List<AccountOwnersEquity> findAllOwnersEquity();
    AccountOwnersEquity saveOwnersEquity(AccountOwnersEquity accountOwnersEquity);
    void deleteOwnersEquity(Long id);
    AccountOwnersEquity findOwnersEquityById(Long id);
    int findSumOwnersEquity();
    int findSumOwnersEquityByYear(int year);
}
