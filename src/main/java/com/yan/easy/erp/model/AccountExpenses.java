package com.yan.easy.erp.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
//費損類
@Entity
@Table(name = "account_expenses")
@AllArgsConstructor
@NoArgsConstructor
public class AccountExpenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "assetsName")
    private String accountName;

    @Column(name = "Amount")
    private int accountAmount;

    @Lob
    @Column(name = "remark")
    private String remark;

    public AccountExpenses(Date date, String accountName, int accountAmount, String remark) {
        this.date = date;
        this.accountName = accountName;
        this.accountAmount = accountAmount;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(int accountAmount) {
        this.accountAmount = accountAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
