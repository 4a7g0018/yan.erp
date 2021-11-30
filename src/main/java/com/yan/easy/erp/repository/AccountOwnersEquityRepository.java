package com.yan.easy.erp.repository;

import com.yan.easy.erp.model.AccountOwnersEquity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountOwnersEquityRepository extends JpaRepository<AccountOwnersEquity,Long> {

    @Query(value = "SELECT * FROM account_owners_equity ORDER BY date DESC",nativeQuery = true)
    List<AccountOwnersEquity> findAllOwnersEquityDESC();

    AccountOwnersEquity findOwnersEquityById(Long id);

    @Query(value = "SELECT SUM(amount) FROM account_owners_equity",nativeQuery = true)
    int findSumOwnersEquity();

    @Query(value = "SELECT SUM(amount) FROM account_owners_equity WHERE date LIKE ?1",nativeQuery = true)
    int findSumOwnersEquityByYear(String year);
}
