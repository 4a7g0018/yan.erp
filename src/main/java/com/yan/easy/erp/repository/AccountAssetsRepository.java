package com.yan.easy.erp.repository;

import com.yan.easy.erp.model.AccountAssets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountAssetsRepository extends JpaRepository<AccountAssets,Long> {

    @Query(value = "SELECT * FROM account_assets ORDER BY date DESC",nativeQuery = true)
    List<AccountAssets> findAllAssetsDESC();

    AccountAssets findAssetsById(Long id);

    @Query(value = "SELECT SUM(amount) FROM account_assets",nativeQuery = true)
    int findSumAssets();

    @Query(value = "SELECT SUM(amount) FROM account_assets WHERE date LIKE ?1",nativeQuery = true)
    int findSumAssetsByYear(String year);
}
