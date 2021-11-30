package com.yan.easy.erp;

import com.yan.easy.erp.model.*;
import com.yan.easy.erp.service.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
@SpringBootTest
@Slf4j
public class AccountRepositoryTest {

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


    @Test
    public void testSaveAssets() throws ParseException {
        List<String> timeList = new ArrayList<>();
        List<String> assetsNameList = new ArrayList<>();
        List<Integer> assetsAmount = new ArrayList<>();

        List<String> expensesNameList = new ArrayList<>();
        List<Integer> expensesAmount = new ArrayList<>();

        List<String> liabilitiesNameList = new ArrayList<>();
        List<Integer> liabilitiesAmount = new ArrayList<>();

        List<String> ownersEquityNameList = new ArrayList<>();
        List<Integer> ownersEquityAmount = new ArrayList<>();

        List<String> revenueNameList = new ArrayList<>();
        List<Integer> revenueAmount = new ArrayList<>();

        timeList.add("2022-05-02 08:05:53");
        assetsNameList.add("現金");
        assetsAmount.add(452345);
        expensesNameList.add("薪資費用");
        expensesAmount.add(162011);
        liabilitiesNameList.add("應付帳款");
        liabilitiesAmount.add(60121);
        ownersEquityNameList.add("業主往來");
        ownersEquityAmount.add(100015);
        revenueNameList.add("銷貨收入");
        revenueAmount.add(90041);

        timeList.add("2022-03-02 10:50:28");
        assetsNameList.add("應收票據");
        assetsAmount.add(352345);
        expensesNameList.add("薪資費用");
        expensesAmount.add(162011);
        liabilitiesNameList.add("應付票據");
        liabilitiesAmount.add(90121);
        ownersEquityNameList.add("業主往來");
        ownersEquityAmount.add(150015);
        revenueNameList.add("銷貨收入");
        revenueAmount.add(130041);

        timeList.add("2021-04-02 13:10:51");
        assetsNameList.add("應收帳款");
        assetsAmount.add(302345);
        expensesNameList.add("薪資費用");
        expensesAmount.add(152011);
        liabilitiesNameList.add("應付帳款");
        liabilitiesAmount.add(100121);
        ownersEquityNameList.add("業主往來");
        ownersEquityAmount.add(100015);
        revenueNameList.add("銷貨收入");
        revenueAmount.add(100041);

        timeList.add("2020-03-02 10:24:58");
        assetsNameList.add("現金");
        assetsAmount.add(182345);
        expensesNameList.add("薪資費用");
        expensesAmount.add(152011);
        liabilitiesNameList.add("應付票據");
        liabilitiesAmount.add(80121);
        ownersEquityNameList.add("業主往來");
        ownersEquityAmount.add(80015);
        revenueNameList.add("銷貨折扣");
        revenueAmount.add(70041);

        timeList.add("2019-02-02 09:13:50");
        assetsNameList.add("短期投資");
        assetsAmount.add(132345);
        expensesNameList.add("薪資費用");
        expensesAmount.add(152011);
        liabilitiesNameList.add("應付利息");
        liabilitiesAmount.add(50121);
        ownersEquityNameList.add("業主往來");
        ownersEquityAmount.add(50015);
        revenueNameList.add("銷貨收入");
        revenueAmount.add(10041);

        timeList.add("2018-01-02 10:46:58");
        assetsNameList.add("現金");
        assetsAmount.add(12345);
        expensesNameList.add("薪資費用");
        expensesAmount.add(152011);
        liabilitiesNameList.add("預收收入");
        liabilitiesAmount.add(10121);
        ownersEquityNameList.add("業主往來");
        ownersEquityAmount.add(10015);
        revenueNameList.add("銷貨折扣");
        revenueAmount.add(120041);


        String dateStr = "2022-03-02 21:25:58";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (String t : timeList) {
            int index = timeList.indexOf(t);
            Date date = simpleDateFormat.parse(t);

            AccountAssets accountAssets = new AccountAssets(date, assetsNameList.get(index)
                    , assetsAmount.get(index), "測試");
            accountAssetsServiceImpl.saveAssets(accountAssets);

            AccountExpenses accountExpenses = new AccountExpenses(date, expensesNameList.get(index)
                    , expensesAmount.get(index), "測試");
            accountExpensesServiceImpl.saveExpenses(accountExpenses);

            AccountLiabilities accountLiabilities = new AccountLiabilities(date, liabilitiesNameList.get(index)
                    , liabilitiesAmount.get(index), "測試");
            accountLiabilitiesServiceImpl.saveLiabilities(accountLiabilities);

            AccountOwnersEquity accountOwnersEquity = new AccountOwnersEquity(date, ownersEquityNameList.get(index)
                    , ownersEquityAmount.get(index), "測試");
            accountOwnersEquityServiceImpl.saveOwnersEquity(accountOwnersEquity);

            AccountRevenue accountRevenue = new AccountRevenue(date, revenueNameList.get(index)
                    , revenueAmount.get(index), "測試");
            accountRevenueServiceImpl.saveRevenue(accountRevenue);
        }


//        AccountAssets accountAssets = new AccountAssets(date, "測試1", 750200, "測試11");
//        AccountAssets saveAccount = accountAssetsServiceImpl.saveAssets(accountAssets);
//        Assertions.assertThat(saveAccount).isNotNull();
//        log.info("Account : " + saveAccount);
//
//        AccountExpenses accountExpenses = new AccountExpenses(date, "測試2", 450325, "測試22");
//        AccountExpenses saveExpenses = accountExpensesServiceImpl.saveExpenses(accountExpenses);
//        Assertions.assertThat(saveExpenses).isNotNull();
//        log.info("Expenses : " + saveExpenses);
//
//        AccountLiabilities accountLiabilities = new AccountLiabilities(date, "測試3", 200451, "測試33");
//        AccountLiabilities saveLiabilities = accountLiabilitiesServiceImpl.saveLiabilities(accountLiabilities);
//        Assertions.assertThat(saveLiabilities).isNotNull();
//        log.info("Liabilities :" + saveLiabilities);
//
//        AccountOwnersEquity accountOwnersEquity = new AccountOwnersEquity(date, "測試4", 10037, "測試44");
//        AccountOwnersEquity saveOwnersEquity = accountOwnersEquityServiceImpl.saveOwnersEquity(accountOwnersEquity);
//        Assertions.assertThat(saveOwnersEquity).isNotNull();
//        log.info("OwnersEquity : " + saveOwnersEquity);
//
//        AccountRevenue accountRevenue = new AccountRevenue(date, "測試5", 9500, "測試55");
//        AccountRevenue saveRevenue = accountRevenueServiceImpl.saveRevenue(accountRevenue);
//        Assertions.assertThat(saveRevenue).isNotNull();
//        log.info("Revenue : " + saveRevenue);
    }

    @Test
    public void testDate() {
        Date date = new Date();
        System.out.println("----------------------");
        System.out.println(date);
        log.info(String.valueOf(date.getTime()));
    }

    @Test
    public void testSum() {
        int sumAssets = accountAssetsServiceImpl.findSumAssets();
        int sumExpenses = accountExpensesServiceImpl.findSumExpenses();
        int sumLiabilities = accountLiabilitiesServiceImpl.findSumLiabilities();
        int sumOwnersEquity = accountOwnersEquityServiceImpl.findSumOwnersEquity();
        int sumRevenue = accountRevenueServiceImpl.findSumRevenue();
        log.info("Sum Assets : " + sumAssets);
        log.info("Sum Expenses : " + sumExpenses);
        log.info("Sum Liabilities : " + sumLiabilities);
        log.info("Sum OwnersEquity : " + sumOwnersEquity);
        log.info("Sum Revenue : " + sumRevenue);
    }

    @Test
    public void testSumByYear() throws ParseException {
        log.info("2021 sum : " + accountAssetsServiceImpl.findSumAssetsByYear(2021));

    }
}
