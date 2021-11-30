package com.yan.easy.erp.service;

import com.yan.easy.erp.model.AccountExpenses;

import java.util.List;

public interface AccountExpensesService {
    List<AccountExpenses> findAllExpenses();
    AccountExpenses saveExpenses(AccountExpenses accountExpenses);
    void deleteExpenses(Long id);
    AccountExpenses findExpensesById(Long id);
    int findSumExpenses();
    int findSumExpensesByYear(int year);
}
