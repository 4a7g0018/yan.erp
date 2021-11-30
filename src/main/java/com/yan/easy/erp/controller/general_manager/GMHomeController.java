package com.yan.easy.erp.controller.general_manager;

import com.yan.easy.erp.model.HomeData;
import com.yan.easy.erp.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/gm/home")
@Controller
@Slf4j
public class GMHomeController {

    @Autowired
    private AccountAssetsServiceImpl accountAssetsServiceImpl;

    @Autowired
    private AccountExpensesServiceImpl accountExpensesServiceImpl;

    @Autowired
    private AccountLiabilitiesServiceImpl accountLiabilitiesServiceImpl;

    @Autowired
    private AccountOwnersEquityServiceImpl accountOwnersEquityServiceImpl;

    @Autowired
    private AccountRevenueServiceImpl accountRevenueServiceImpl;

    @Autowired
    private WorkListServiceImpl workListService;

    @GetMapping("data")
    public ResponseEntity<?> getHomeData() {
        List<String> years = new ArrayList<>();
        years.add("2019");
        years.add("2020");
        years.add("2021");
        years.add("2022");

        List<Integer> sumAssetsList = new ArrayList<>();
        List<Integer> sumExpensesList = new ArrayList<>();
        List<Integer> sumLiabilitiesList = new ArrayList<>();
        List<Integer> sumOwnersEquityList = new ArrayList<>();
        List<Integer> sumRevenueList = new ArrayList<>();
        for (String year : years) {
            if(accountAssetsServiceImpl.findSumAssetsByYear(Integer.parseInt(year) )<=0){
                log.info(year+" - accountAssets -is null");
                sumAssetsList.add(0);
            }else {
                sumAssetsList.add(accountAssetsServiceImpl.findSumAssetsByYear(Integer.parseInt(year)));
            }


            sumExpensesList.add(accountExpensesServiceImpl.findSumExpensesByYear(Integer.parseInt(year)));
            sumLiabilitiesList.add(accountLiabilitiesServiceImpl.findSumLiabilitiesByYear(Integer.parseInt(year)));
            sumOwnersEquityList.add(accountOwnersEquityServiceImpl.findSumOwnersEquityByYear(Integer.parseInt(year)));
            sumRevenueList.add(accountRevenueServiceImpl.findSumRevenueByYear(Integer.parseInt(year)));
        }

        List<List<String>> doneWork = workListService.findAllDoneWorkTimes();
        List<List<String>> undoneWork = workListService.findAllUndoneWorkTimes();

        List<String> workPriority = new ArrayList<>();
        List<Integer> formatDoneWork = new ArrayList<>();
        List<Integer> formatUndoneWork = new ArrayList<>();
        for (int priority = 0; priority < 5; priority++) {

            try {
                workPriority.add(doneWork.get(priority).get(0));
            }catch (Exception e){
                workPriority.add("NULL");
            }


            try{
                formatDoneWork.add(Integer.valueOf(doneWork.get(priority).get(1)));
            }catch (Exception e){
                formatDoneWork.add(0);
            }

            try{
                formatUndoneWork.add(Integer.valueOf(undoneWork.get(priority).get(1)));
            }catch (Exception e){
                formatUndoneWork.add(0);
            }
        }

        HomeData homeData = new HomeData(years, sumAssetsList, sumExpensesList, sumLiabilitiesList, sumOwnersEquityList,
                sumRevenueList, workPriority, formatDoneWork, formatUndoneWork);

        return ResponseEntity.ok().body(homeData);
    }

}
