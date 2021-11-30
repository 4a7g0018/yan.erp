package com.yan.easy.erp.service;

import com.yan.easy.erp.model.AccountExpenses;
import com.yan.easy.erp.repository.AccountExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountExpensesServiceImpl implements AccountExpensesService {

    @Autowired
    private AccountExpensesRepository accountExpensesRepository;

    @Override
    public List<AccountExpenses> findAllExpenses() {
        return accountExpensesRepository.findAllExpensesDESC();
    }

    @Override
    public AccountExpenses saveExpenses(AccountExpenses accountExpenses) {
        return accountExpensesRepository.save(accountExpenses);
    }

    @Override
    public void deleteExpenses(Long id) {
        accountExpensesRepository.deleteById(id);
    }

    @Override
    public AccountExpenses findExpensesById(Long id) {
        return accountExpensesRepository.findExpensesById(id);
    }

    @Override
    public int findSumExpenses() {
        return accountExpensesRepository.findSumExpenses();
    }

    @Override
    public int findSumExpensesByYear(int year) {
        String yearFormat = String.valueOf(year)+"_%";
        return accountExpensesRepository.findSumExpensesByYear(yearFormat);
    }

}
