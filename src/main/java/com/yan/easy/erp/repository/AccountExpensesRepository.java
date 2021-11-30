package com.yan.easy.erp.repository;

import com.yan.easy.erp.model.AccountExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountExpensesRepository extends JpaRepository<AccountExpenses,Long> {

    @Query(value = "SELECT * FROM account_expenses ORDER BY date DESC",nativeQuery = true)
    List<AccountExpenses> findAllExpensesDESC();

    AccountExpenses findExpensesById(Long id);

    @Query(value = "SELECT SUM(amount) FROM account_expenses",nativeQuery = true)
    int findSumExpenses();

    @Query(value = "SELECT SUM(amount) FROM account_expenses WHERE date LIKE ?1",nativeQuery = true)
    int findSumExpensesByYear(String year);
}
