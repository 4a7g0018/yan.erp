package com.yan.easy.erp.service;

import com.yan.easy.erp.repository.AccountAssetsRepository;
import com.yan.easy.erp.model.AccountAssets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountAssetsServiceImpl implements AccountAssetsService{

    @Autowired
    private AccountAssetsRepository accountAssetsRepository;

    @Override
    public List<AccountAssets> findAllAssets() {
        return accountAssetsRepository.findAllAssetsDESC();
    }

    @Override
    public AccountAssets saveAssets(AccountAssets accountAssets) {
        return accountAssetsRepository.save(accountAssets);
    }

    @Override
    public void deleteAssets(Long id) {
        accountAssetsRepository.deleteById(id);
    }

    @Override
    public AccountAssets findAssetById(Long id) {
        return accountAssetsRepository.findAssetsById(id);
    }

    @Override
    public int findSumAssets(){
        return accountAssetsRepository.findSumAssets();
    }

    @Override
    public int findSumAssetsByYear(int year){
        String yearFormat = String.valueOf(year)+"_%";
        return accountAssetsRepository.findSumAssetsByYear(yearFormat);
    }
}
