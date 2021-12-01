package com.yan.easy.erp;

import com.yan.easy.erp.model.*;
import com.yan.easy.erp.service.*;
import org.apache.commons.io.FileUtils;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataBaseInitTest {

    @Autowired
    private RolesServiceImpl rolesServiceImpl;

    @Autowired
    private UserRoleServiceImpl userRoleServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private WorkListServiceImpl workListServiceImpl;

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
    private WorkListParticipantsServiceImpl workListParticipantsService;

    @Test
    //CreatUser
    public void T1() {

        List<String> userNameList = new ArrayList<>();
        List<String> userImageList = new ArrayList<>();
        List<String> userPhoneList = new ArrayList<>();
        List<String> userMailList = new ArrayList<>();

        userNameList.add("GENERAL_MANAGER");
        userImageList.add("D:\\spring-boot\\yan.security.practise\\src\\main\\java\\com\\yan\\easy\\erp\\headshot\\GENERAL-MANAGER.jpg");
        userPhoneList.add("0989123456");
        userMailList.add("GENERAL_MANAGER@gmail.com");

        userNameList.add("HR_MANAGER");
        userImageList.add("D:\\spring-boot\\yan.security.practise\\src\\main\\java\\com\\yan\\easy\\erp\\headshot\\HR-MANAGER.jpg");
        userPhoneList.add("0989147852");
        userMailList.add("HR_MANAGER@gmail.com");

        userNameList.add("HR_USER");
        userImageList.add("D:\\spring-boot\\yan.security.practise\\src\\main\\java\\com\\yan\\easy\\erp\\headshot\\HR-USER.jpg");
        userPhoneList.add("0989963258");
        userMailList.add("HR_USER@gmail.com");

        userNameList.add("RD_MANAGER");
        userImageList.add("D:\\spring-boot\\yan.security.practise\\src\\main\\java\\com\\yan\\easy\\erp\\headshot\\RD-MANAGER.jpg");
        userPhoneList.add("0989987456");
        userMailList.add("RD_MANAGER@gmail.com");

        userNameList.add("RD_USER");
        userImageList.add("D:\\spring-boot\\yan.security.practise\\src\\main\\java\\com\\yan\\easy\\erp\\headshot\\RD-USER.jpg");
        userPhoneList.add("0989852147");
        userMailList.add("RD_MANAGER@gmail.com");

        userNameList.add("FD_MANAGER");
        userImageList.add("D:\\spring-boot\\yan.security.practise\\src\\main\\java\\com\\yan\\easy\\erp\\headshot\\FD-MANAGER.jpg");
        userPhoneList.add("0989987456");
        userMailList.add("RD_MANAGER@gmail.com");

        userNameList.add("FD_USER");
        userImageList.add("D:\\spring-boot\\yan.security.practise\\src\\main\\java\\com\\yan\\easy\\erp\\headshot\\FD-USER.jpg");
        userPhoneList.add("0989325698");
        userMailList.add("RD_MANAGER@gmail.com");

//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {

            for (String userName:userNameList){
                int index = userNameList.indexOf(userName);

                byte[] imageDecodeBytes = FileUtils.readFileToByteArray(new File(userImageList.get(index)));
                String imageString = Base64.getEncoder().encodeToString(imageDecodeBytes);
//                String password = userName;
//                password = passwordEncoder.encode(password);

                Users user = new Users();
                user.setUserName(userName);
                user.setPassword(userName);
//                user.setPassword(passwordEncoder.encode(userName));
                user.setEnable(true);
                user.setImage(imageString);
                user.setEmail(userMailList.get(index));
                user.setPhone(userPhoneList.get(index));
                userServiceImpl.saveUser(user);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    //CreateRole
    public void T2(){
        for (Enum role : RolesList.class.getEnumConstants()){
            Roles roles = new Roles(role.toString());
            rolesServiceImpl.saveRoles(roles);
        }
    }

    @Test
    //AddRoleToUser
    public void T3(){
        List<String> user_list = new ArrayList<>();
        user_list.add("GENERAL_MANAGER");
        user_list.add("HR_MANAGER");
        user_list.add("HR_USER");
        user_list.add("RD_MANAGER");
        user_list.add("RD_USER");
        user_list.add("FD_MANAGER");
        user_list.add("FD_USER");

        for (String user :user_list){
            Users users=userServiceImpl.findByUserName(user);
            Roles roles=rolesServiceImpl.findRolesByName(user);
            UserRole userRole=new UserRole(users,roles);
            userRoleServiceImpl.saveUserRole(userRole);
        }
    }

    @Test
    //CreateAccount
    public void T4() throws ParseException {
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
    //CreateWorkList
    public void T5() {
        List<String> user_list = new ArrayList<>();
        List<String> testJobTitle = new ArrayList<>();
        user_list.add("GENERAL_MANAGER");
        testJobTitle.add("總經理-測試");
        user_list.add("HR_MANAGER");
        testJobTitle.add("人資部-測試");
        user_list.add("RD_MANAGER");
        testJobTitle.add("研發部-測試");
        user_list.add("FD_MANAGER");
        testJobTitle.add("財務部-測試");

        for (String user : user_list){
            int index = user_list.indexOf(user);

            Long userID = userServiceImpl.findByUserName(user).getId();
            Long roleID = userRoleServiceImpl.findUserRoleByUsersId(userID).getId();
            WorkList workList = new WorkList();
            workList.setJobTitle(testJobTitle.get(index));
            workList.setPriority(1);
            workList.setSponsor(user);
            workList.setDate(new Date());
            workList.setIllustrate("this is a test..");
            workList.setAnnounce(true);
            workList.setFinish(false);
            workList.setRoleId(roleID);

            workListServiceImpl.saveWorkList(workList);
        }
    }

    @Test
    //AddUserToWorkList
    public void T6(){
        List<String> manager_list = new ArrayList<>();
        List<String> user_list = new ArrayList<>();
        manager_list.add("GENERAL_MANAGER");
        user_list.add("HR_MANAGER");
        manager_list.add("HR_MANAGER");
        user_list.add("HR_USER");
        manager_list.add("RD_MANAGER");
        user_list.add("RD_USER");
        manager_list.add("FD_MANAGER");
        user_list.add("FD_USER");

        for(String manager :manager_list){
            int index = manager_list.indexOf(manager);

            Users user = userServiceImpl.findByUserName(user_list.get(index));

            Long userId= userServiceImpl.findByUserName(manager).getId();
            Long roleId = userRoleServiceImpl.findUserRoleByUsersId(userId).getId();
            List<WorkList> workList= workListServiceImpl.findWorkListsByRoleId(roleId);
            for(WorkList w : workList){
                WorkListParticipants workListParticipants=new WorkListParticipants(w,user);
                workListParticipantsService.saveWorkListParticipants(workListParticipants);
            }
        }
    }
}
