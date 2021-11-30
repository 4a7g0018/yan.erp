package com.yan.easy.erp;

import com.yan.easy.erp.model.Users;
import com.yan.easy.erp.model.WorkList;
import com.yan.easy.erp.model.WorkListParticipants;
import com.yan.easy.erp.service.WorkListParticipantsServiceImpl;
import com.yan.easy.erp.service.UserServiceImpl;
import com.yan.easy.erp.service.WorkListServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

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
    private WorkListParticipantsServiceImpl workListParticipantsService;

//    @Autowired
//    private ParticipantsListServiceImpl participantsListService;

    @Test
    public void testSaveWorkList() {
        WorkList workList = new WorkList();
        workList.setJobTitle("Job-hot-3");
        workList.setPriority(4);
        workList.setFinish(false);
        workList.setSponsor("admin");
        workList.setDate(new Date());
        workList.setIllustrate("this is a test..");
        workList.setAnnounce(true);
        System.out.println("----------------------------------------");
//        System.out.println(workList);
        workListServiceImpl.saveWorkList(workList);

//        Users user1 = userServiceImpl.findUserByUserId(1l);
//        Users user2 = userServiceImpl.findUserByUserId(2l);

//        ParticipantsList participantsList=new ParticipantsList(workList1,user1);
//        ParticipantsList participantsList1=new ParticipantsList(workList1,user2);
//        participantsListService.saveParticipantsList(participantsList);
//        participantsListService.saveParticipantsList(participantsList1);

    }


    @Test
    public void testFindByWorkListId() {
        Users users=userServiceImpl.findUserByUserId(1L);
        WorkList workLists =workListServiceImpl.findWorkListById(2L);
        System.out.println("------------------------------------");

        WorkListParticipants workListParticipants=new WorkListParticipants(workLists,users);
        workListParticipantsService.saveWorkListParticipants(workListParticipants);
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
