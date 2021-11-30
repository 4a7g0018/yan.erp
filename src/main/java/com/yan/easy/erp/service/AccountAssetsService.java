package com.yan.easy.erp.service;

import com.yan.easy.erp.model.AccountAssets;

import java.util.List;

public interface AccountAssetsService {
    List<AccountAssets> findAllAssets();
    AccountAssets saveAssets(AccountAssets accountAssets);
    void deleteAssets(Long id);
    AccountAssets findAssetById(Long id);
    int findSumAssets();
    int findSumAssetsByYear(int year);
}
