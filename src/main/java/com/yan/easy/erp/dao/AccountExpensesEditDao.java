package com.yan.easy.erp.dao;

public class AccountExpensesEditDao {
    private String id;
    private String accountName;
    private String time;
    private String amount;
    private String remark;

    public AccountExpensesEditDao(String id, String accountName, String time, String amount, String remark) {
        this.id = id;
        this.accountName = accountName;
        this.time = time;
        this.amount = amount;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
