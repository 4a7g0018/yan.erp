package com.yan.easy.erp.controller.financial;

import com.yan.easy.erp.RolesList;
import com.yan.easy.erp.dao.BulletinBoardDao;
import com.yan.easy.erp.model.*;
import com.yan.easy.erp.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@RequestMapping("/account/home")
@Controller
@Slf4j
public class FinancialHomeController {

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

    @Autowired
    private BulletinBoardService bulletinBoardService;

    @Autowired
    private RolesServiceImpl rolesService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRoleServiceImpl userRoleServiceImpl;

    @Autowired
    private RolesServiceImpl rolesServiceImpl;

    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userServiceImpl.findByUserName(authentication.getName());
    }

    @GetMapping("is_manager")
    public ResponseEntity<Boolean> isManager() {
        Roles userRole = userRoleServiceImpl.findUserRoleByUsersId(getUser().getId()).getRoles();
        return ResponseEntity.ok(userRole.getName().contains("MANAGER"));
    }

    @GetMapping("get_name")
    public ResponseEntity<String> getName(){
        return ResponseEntity.ok(getUser().getUserName());
    }

    @GetMapping("get_work_data")
    public ResponseEntity<?> getWorkData() {
        Roles role = rolesServiceImpl.findRolesByName("ACCOUNT_MANAGER");
        List<WorkList> workLists = workListService.findWorkListsByRoleId(role.getId());

        List<List<String>> doneWork = workListService.findDoneWorkTimesByRoleId(role.getId());
        List<List<String>> undoneWork = workListService.findUndoneWorkTimesByRoleId(role.getId());
        for (int i=0 ; i<5 ; i++){
            try{
                Objects.equals(doneWork.get(i).get(0), String.valueOf(i+1));
            }catch (Exception e){
                List<String> doneLostList =new ArrayList<>();
                doneLostList.add(String.valueOf(i+1));
                doneLostList.add("0");
                doneWork.add(i,doneLostList);
            }
        }
        System.out.println("-------------------------------");
        System.out.println("doneWork:"+doneWork);
        System.out.println("undoneWork:"+undoneWork);
        for(int j=0 ; j<5 ; j++){
            try{
                if(!Objects.equals(undoneWork.get(j).get(0), String.valueOf(j+1))){
                    List<String> undoneLostList =new ArrayList<>();
                    undoneLostList.add(String.valueOf(j+1));
                    undoneLostList.add("0");
                    undoneWork.add(j,undoneLostList);
                }

            }catch (Exception e){
                System.out.println(j);
                List<String> undoneLostList =new ArrayList<>();
                undoneLostList.add(String.valueOf(j+1));
                undoneLostList.add("0");
                undoneWork.add(j,undoneLostList);
            }
        }
        System.out.println("-------------------------------");
        System.out.println("doneWork:"+doneWork);
        System.out.println("undoneWork:"+undoneWork);

        List<String> workPriority = new ArrayList<>();
//        List<Integer> formatDoneWork = new ArrayList<>();
//        List<Integer> formatUndoneWork = new ArrayList<>();

        Map<String,Integer>  formatDoneWork =new HashMap<>();
        Map<String,Integer> formatUndoneWork = new HashMap<>();
        for (int priority = 0; priority < 5; priority++) {
            try {
                workPriority.add(doneWork.get(priority).get(0));
            } catch (Exception e) {
                workPriority.add(String.valueOf(priority));
            }

            try {
                formatDoneWork.put(String.valueOf(priority),Integer.valueOf(doneWork.get(priority).get(1)));
            } catch (Exception e) {
                formatDoneWork.put(String.valueOf(priority),0);
            }

            try {
                formatUndoneWork.put(String.valueOf(priority),Integer.valueOf(undoneWork.get(priority).get(1)));
            } catch (Exception e) {
                formatUndoneWork.put(String.valueOf(priority),0);
            }
        }
        HomeData homeData =new HomeData(formatDoneWork,formatUndoneWork);
        System.out.println("-------------------------------");
        System.out.println("formatDoneWork:"+formatDoneWork);
        System.out.println("formatUndoneWork:"+formatUndoneWork);
        return ResponseEntity.ok(homeData);
    }

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
            if (accountAssetsServiceImpl.findSumAssetsByYear(Integer.parseInt(year)) <= 0) {
                log.info(year + " - accountAssets -is null");
                sumAssetsList.add(0);
            } else {
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
            } catch (Exception e) {
                workPriority.add("NULL");
            }


            try {
                formatDoneWork.add(Integer.valueOf(doneWork.get(priority).get(1)));
            } catch (Exception e) {
                formatDoneWork.add(0);
            }

            try {
                formatUndoneWork.add(Integer.valueOf(undoneWork.get(priority).get(1)));
            } catch (Exception e) {
                formatUndoneWork.add(0);
            }
        }

        HomeData homeData = new HomeData(years, sumAssetsList, sumExpensesList, sumLiabilitiesList, sumOwnersEquityList,
                sumRevenueList, workPriority, formatDoneWork, formatUndoneWork);

        return ResponseEntity.ok().body(homeData);
    }

    @GetMapping("get_bulletin_board")
    public ResponseEntity<List<BulletinBoard>> getBulletinBoardByDepartmentId() {
        Long roleId = rolesService.findRolesByName(RolesList.ACCOUNT_MANAGER.name()).getId();
        List<BulletinBoard> bulletinBoardList = bulletinBoardService.findDepartmentBulletinBoard(roleId);

        return ResponseEntity.ok(bulletinBoardList);
    }

    @PostMapping("save_bulletin_board")
    public ResponseEntity<?> saveBulletinBoard(BulletinBoardDao bulletinBoardDao){
        Long roleId = rolesService.findRolesByName(RolesList.ACCOUNT_MANAGER.name()).getId();
        System.out.println("--------------------------------");
        System.out.println("bulletinBoardDao:");
        System.out.println(bulletinBoardDao.getTitle());
        System.out.println(bulletinBoardDao.getDate());
        System.out.println(bulletinBoardDao.getSponsor());
        System.out.println(bulletinBoardDao.getContent());

        BulletinBoard bulletinBoard=new BulletinBoard(
                bulletinBoardDao.getTitle(),
                new Date(),
                bulletinBoardDao.getSponsor(),
                bulletinBoardDao.getContent(),
                roleId
        );
        bulletinBoardService.saveBulletinBoard(bulletinBoard);
        return ResponseEntity.ok(bulletinBoardDao);
    }
}
