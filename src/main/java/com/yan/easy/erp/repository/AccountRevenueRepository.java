package com.yan.easy.erp.repository;

import com.yan.easy.erp.model.AccountRevenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRevenueRepository extends JpaRepository<AccountRevenue,Long> {

    @Query(value = "SELECT * FROM account_revenue ORDER BY date DESC",nativeQuery = true)
    List<AccountRevenue> findAllAccountRevenueDESC();

    AccountRevenue findRevenueById(Long id);

    @Query(value = "SELECT SUM(amount) FROM account_revenue",nativeQuery = true)
    int findSumRevenueRepository();

    @Query(value = "SELECT SUM(amount) FROM account_revenue WHERE date LIKE ?1",nativeQuery = true)
    int findSumRevenueRepositoryByYear(String year);
}
