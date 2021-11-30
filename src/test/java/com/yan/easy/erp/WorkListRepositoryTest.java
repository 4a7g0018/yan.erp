package com.yan.easy.erp;

import com.yan.easy.erp.model.Users;
import com.yan.easy.erp.model.WorkList;
import com.yan.easy.erp.model.WorkListParticipants;
import com.yan.easy.erp.service.UserRoleServiceImpl;
import com.yan.easy.erp.service.WorkListParticipantsServiceImpl;
import com.yan.easy.erp.service.UserServiceImpl;
import com.yan.easy.erp.service.WorkListServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
@SpringBootTest
@Slf4j
public class WorkListRepositoryTest {


    @Autowired
    private WorkListServiceImpl workListServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRoleServiceImpl userRoleService;

    @Autowired
    private WorkListParticipantsServiceImpl workListParticipantsService;

//    @Autowired
//    private ParticipantsListServiceImpl participantsListService;

    @Test
    public void testSaveWorkList() {
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
            Long roleID = userRoleService.findUserRoleByUsersId(userID).getId();
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
    public void testFindByWorkListId() {
        Users users=userServiceImpl.findUserByUserId(2L);
        WorkList workLists =workListServiceImpl.findWorkListById(1L);
        System.out.println("------------------------------------");

        WorkListParticipants workListParticipants=new WorkListParticipants(workLists,users);
        workListParticipantsService.saveWorkListParticipants(workListParticipants);
    }

    @Test
    public void testPut(){
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
            Long roleId = userRoleService.findUserRoleByUsersId(userId).getId();
            List<WorkList> workList= workListServiceImpl.findWorkListsByRoleId(roleId);
            for(WorkList w : workList){
                WorkListParticipants workListParticipants=new WorkListParticipants(w,user);
                workListParticipantsService.saveWorkListParticipants(workListParticipants);
            }
        }
    }


    @Test
    public void testFindWorkListParticipantsById(){

        System.out.println("--------------------------------------");
        System.out.println(workListParticipantsService.findWorkListParticipantsById(1L));
        System.out.println("--------------------------------------");
        System.out.println(workListParticipantsService.findWorkListParticipantsByWorkListId(1L));
        for(WorkListParticipants workListParticipants : workListParticipantsService.findWorkListParticipantsByWorkListId(1L)){
            System.out.println("--------------------------------------");
            System.out.println("--------------------------------------");
            System.out.println(workListParticipants.getUsers().getUserName());

        }
    }

    @Test
    public void testGetWorkTimesByPriority(){
        log.info("Undone :"+workListServiceImpl.findAllUndoneWorkTimes());
        log.info("Done :"+workListServiceImpl.findAllDoneWorkTimes());
    }

    @Test
    public void testDeleteWorkListParticipants(){
        workListParticipantsService.deleteWorkListParticipantsByWorkListId(13L);
    }

}
