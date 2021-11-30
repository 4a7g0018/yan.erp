package com.yan.easy.erp.repository;


import com.yan.easy.erp.model.AccountLiabilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountLiabilitiesRepository extends JpaRepository<AccountLiabilities,Long> {

    @Query(value = "SELECT * FROM account_liabilities ORDER BY date DESC",nativeQuery = true)
    List<AccountLiabilities> findAllLiabilitiesDESC();

    AccountLiabilities findLiabilitiesById(Long id);

    @Query(value = "SELECT SUM(amount) FROM account_liabilities",nativeQuery = true)
    int findSumLiabilities();

    @Query(value = "SELECT SUM(amount) FROM account_liabilities  WHERE date LIKE ?1",nativeQuery = true)
    int findSumLiabilitiesByYear(String year);
}
