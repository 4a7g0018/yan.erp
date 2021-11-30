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
import java.util.Date;

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

        String dateStr ="2022-03-02 21:25:58";
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(dateStr);



        AccountAssets accountAssets = new AccountAssets(date, "測試1", 750200, "測試11");
        AccountAssets saveAccount = accountAssetsServiceImpl.saveAssets(accountAssets);
        Assertions.assertThat(saveAccount).isNotNull();
        log.info("Account : " + saveAccount);

        AccountExpenses accountExpenses = new AccountExpenses(date, "測試2", 450325, "測試22");
        AccountExpenses saveExpenses = accountExpensesServiceImpl.saveExpenses(accountExpenses);
        Assertions.assertThat(saveExpenses).isNotNull();
        log.info("Expenses : " + saveExpenses);

        AccountLiabilities accountLiabilities = new AccountLiabilities(date, "測試3", 200451, "測試33");
        AccountLiabilities saveLiabilities = accountLiabilitiesServiceImpl.saveLiabilities(accountLiabilities);
        Assertions.assertThat(saveLiabilities).isNotNull();
        log.info("Liabilities :" + saveLiabilities);

        AccountOwnersEquity accountOwnersEquity = new AccountOwnersEquity(date, "測試4", 10037, "測試44");
        AccountOwnersEquity saveOwnersEquity = accountOwnersEquityServiceImpl.saveOwnersEquity(accountOwnersEquity);
        Assertions.assertThat(saveOwnersEquity).isNotNull();
        log.info("OwnersEquity : " + saveOwnersEquity);

        AccountRevenue accountRevenue = new AccountRevenue(date, "測試5", 9500, "測試55");
        AccountRevenue saveRevenue = accountRevenueServiceImpl.saveRevenue(accountRevenue);
        Assertions.assertThat(saveRevenue).isNotNull();
        log.info("Revenue : " + saveRevenue);
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
        int sumExpenses=accountExpensesServiceImpl.findSumExpenses();
        int sumLiabilities=accountLiabilitiesServiceImpl.findSumLiabilities();
        int sumOwnersEquity=accountOwnersEquityServiceImpl.findSumOwnersEquity();
        int sumRevenue=accountRevenueServiceImpl.findSumRevenue();
        log.info("Sum Assets : " + sumAssets);
        log.info("Sum Expenses : " + sumExpenses);
        log.info("Sum Liabilities : " + sumLiabilities);
        log.info("Sum OwnersEquity : " + sumOwnersEquity);
        log.info("Sum Revenue : " + sumRevenue);
    }

    @Test
    public void testSumByYear() throws ParseException {
        log.info("2021 sum : "+accountAssetsServiceImpl.findSumAssetsByYear(2021));

    }
}
