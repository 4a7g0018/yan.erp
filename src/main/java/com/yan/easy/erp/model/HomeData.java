package com.yan.easy.erp.model;

import java.util.List;
import java.util.Map;

public class HomeData {
    private List<String> years;
    private List<Integer> sumAssets;
    private List<Integer> sumExpenses;
    private List<Integer> sumLiabilities;
    private List<Integer> sumOwnersEquity;
    private List<Integer> sumRevenue;
    private List<String> workPriority;
    private List<Integer> doneWork;
    private List<Integer> undoneWork;
    private Map<String,Integer> doneWorkMap;
    private Map<String,Integer> undoneWorkMap;

    public HomeData(List<String> years, List<Integer> sumAssets, List<Integer> sumExpenses, List<Integer> sumLiabilities, List<Integer> sumOwnersEquity, List<Integer> sumRevenue, List<String> workPriority, List<Integer> doneWork, List<Integer> undoneWork) {
        this.years = years;
        this.sumAssets = sumAssets;
        this.sumExpenses = sumExpenses;
        this.sumLiabilities = sumLiabilities;
        this.sumOwnersEquity = sumOwnersEquity;
        this.sumRevenue = sumRevenue;
        this.workPriority = workPriority;
        this.doneWork = doneWork;
        this.undoneWork = undoneWork;
    }

    public HomeData(List<String> workPriority, List<Integer> doneWork, List<Integer> undoneWork) {
        this.workPriority = workPriority;
        this.doneWork = doneWork;
        this.undoneWork = undoneWork;
    }

    public HomeData(Map<String, Integer> doneWorkMap, Map<String, Integer> undoneWorkMap) {
        this.doneWorkMap = doneWorkMap;
        this.undoneWorkMap = undoneWorkMap;
    }

    public List<String> getYears() {
        return years;
    }

    public void setYears(List<String> years) {
        this.years = years;
    }

    public List<Integer> getSumAssets() {
        return sumAssets;
    }

    public void setSumAssets(List<Integer> sumAssets) {
        this.sumAssets = sumAssets;
    }

    public List<Integer> getSumExpenses() {
        return sumExpenses;
    }

    public void setSumExpenses(List<Integer> sumExpenses) {
        this.sumExpenses = sumExpenses;
    }

    public List<Integer> getSumLiabilities() {
        return sumLiabilities;
    }

    public void setSumLiabilities(List<Integer> sumLiabilities) {
        this.sumLiabilities = sumLiabilities;
    }

    public List<Integer> getSumOwnersEquity() {
        return sumOwnersEquity;
    }

    public void setSumOwnersEquity(List<Integer> sumOwnersEquity) {
        this.sumOwnersEquity = sumOwnersEquity;
    }

    public List<Integer> getSumRevenue() {
        return sumRevenue;
    }

    public void setSumRevenue(List<Integer> sumRevenue) {
        this.sumRevenue = sumRevenue;
    }

    public List<String> getWorkPriority() {
        return workPriority;
    }

    public void setWorkPriority(List<String> workPriority) {
        this.workPriority = workPriority;
    }

    public List<Integer> getDoneWork() {
        return doneWork;
    }

    public void setDoneWork(List<Integer> doneWork) {
        this.doneWork = doneWork;
    }

    public List<Integer> getUndoneWork() {
        return undoneWork;
    }

    public void setUndoneWork(List<Integer> undoneWork) {
        this.undoneWork = undoneWork;
    }

    public Map<String, Integer> getDoneWorkMap() {
        return doneWorkMap;
    }

    public void setDoneWorkMap(Map<String, Integer> doneWorkMap) {
        this.doneWorkMap = doneWorkMap;
    }

    public Map<String, Integer> getUndoneWorkMap() {
        return undoneWorkMap;
    }

    public void setUndoneWorkMap(Map<String, Integer> undoneWorkMap) {
        this.undoneWorkMap = undoneWorkMap;
    }
}
