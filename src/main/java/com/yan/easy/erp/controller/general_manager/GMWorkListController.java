package com.yan.easy.erp.controller.general_manager;

import com.yan.easy.erp.dao.TaskForm;
import com.yan.easy.erp.dao.WorkListDao;
import com.yan.easy.erp.model.*;
import com.yan.easy.erp.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/gm/work_list")
@Controller
@Slf4j
public class GMWorkListController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRoleServiceImpl userRoleServiceImpl;

    @Autowired
    private RolesServiceImpl rolesServiceImpl;

    @Autowired
    private WorkListServiceImpl workListService;

    @Autowired
    private WorkListParticipantsServiceImpl workListParticipantsService;

    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userServiceImpl.findByUserName(authentication.getName());
    }

    private Roles getUserRole(){
        UserRole userRole = userRoleServiceImpl.findUserRoleByUsersId(getUser().getId());
        return userRole.getRoles();
    }

    @GetMapping("get_user")
    public ResponseEntity<List<String>> getUserNameList() {
        List<String> userNames = new ArrayList<>();
        for (Users users : userServiceImpl.findAllUser()) {
            userNames.add(users.getUserName());
        }
        return ResponseEntity.ok().body(userNames);
    }

    @GetMapping("get_all_work")
    public ResponseEntity<List<WorkListDao>> getAllWorkList() {
        List<WorkList> workLists = workListService.findAllWorkList();

        List<WorkListDao> workListDaoList =new ArrayList<>();
        for (WorkList workList : workLists){
            WorkListDao workListDao=new WorkListDao();
            workListDao.setId(workList.getId());
            workListDao.setWorkName(workList.getJobTitle());
            workListDao.setPriority(workList.getPriority());
            workListDao.setDate(workList.getDate());
            workListDao.setIllustrate(workList.getIllustrate());
            workListDao.setAnnounce(workList.isAnnounce());
            workListDao.setFinish(workList.isFinish());
            workListDao.setRoleId(workList.getRoleId());

            List<String> participants=new ArrayList<>();
            for (WorkListParticipants workListParticipants : workListParticipantsService.findWorkListParticipantsByWorkListId(workList.getId())){
                try {
                    participants.add(workListParticipants.getUsers().getUserName());
                }catch(Exception e) {
                    log.info("error : "+e.getMessage());

                }
            }
            workListDao.setParticipants(participants);


            workListDaoList.add(workListDao);
        }

        return ResponseEntity.ok().body(workListDaoList);
    }

    @GetMapping("edit/{id}")
    public ResponseEntity<WorkListDao> getWorkListById(@PathVariable("id") Long id) {
        WorkList workList=workListService.findWorkListById(id);

        List<WorkListParticipants> workListParticipants = workListParticipantsService.findWorkListParticipantsByWorkListId(workList.getId());
        List<String> participantsList=new ArrayList<>();
        for (WorkListParticipants workListParticipants1 : workListParticipants){
            participantsList.add(workListParticipants1.getUsers().getUserName());
        }

        log.info("participantsList :"+participantsList);

        WorkListDao workListDao=new WorkListDao(
                workList.getId(),
                workList.getJobTitle(),
                workList.getPriority(),
                workList.getDate(),
                participantsList,
                workList.getIllustrate(),
                workList.isAnnounce(),
                workList.isFinish(),
                workList.getRoleId()
        );
        return ResponseEntity.ok().body(workListDao);
    }

    @PostMapping("save")
    public ResponseEntity<?> getFromAjax(TaskForm taskForm) throws ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("HelloWorld: task.getJob_title()");
        log.info("Id :" + taskForm.getId());
        log.info("WorkName :" + taskForm.getWorkName());
        log.info("Date :" + taskForm.getDate());
        log.info("Priority :" + taskForm.getPriority());
        log.info("Participants :" + taskForm.getParticipants());
        log.info("Illustrate :" + taskForm.getIllustrate());
        log.info("announce :" + taskForm.getAnnounce());
        log.info("finish :" + taskForm.getFinish());
        log.info("finish :" + taskForm.getRoleId());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date date = sdf.parse(taskForm.getDate());
        if (taskForm.getId() == null) {

            WorkList workList = new WorkList();
            workList.setSponsor(authentication.getName());
            workList.setJobTitle(taskForm.getWorkName());
            workList.setDate(date);
            workList.setPriority(Integer.parseInt(taskForm.getPriority()));
            workList.setIllustrate(taskForm.getIllustrate());
            workList.setAnnounce(Boolean.parseBoolean(taskForm.getAnnounce()));
            workList.setFinish(Boolean.parseBoolean(taskForm.getFinish()));
            workList.setRoleId(taskForm.getRoleId());
            workListService.saveWorkList(workList);

            for (String userName : taskForm.getParticipants()) {
                Users users = userServiceImpl.findByUserName(userName);
                WorkListParticipants workListParticipants = new WorkListParticipants(workList, users);
                workListParticipantsService.saveWorkListParticipants(workListParticipants);
            }
        }else{

            WorkList workList =workListService.findWorkListById(taskForm.getId());
            workList.setSponsor(authentication.getName());
            workList.setJobTitle(taskForm.getWorkName());
            workList.setDate(date);
            workList.setPriority(Integer.parseInt(taskForm.getPriority()));
            workList.setIllustrate(taskForm.getIllustrate());
            workList.setAnnounce(Boolean.parseBoolean(taskForm.getAnnounce()));
            workList.setFinish(Boolean.parseBoolean(taskForm.getFinish()));
            workList.setRoleId(taskForm.getRoleId());
            workListService.saveWorkList(workList);
            if(workListParticipantsService.findWorkListParticipantsByWorkListId(taskForm.getId()) != null){
                workListParticipantsService.deleteWorkListParticipantsByWorkListId(taskForm.getId());
            }

            for (String userName : taskForm.getParticipants()) {
                Users users = userServiceImpl.findByUserName(userName);
                WorkListParticipants workListParticipants = new WorkListParticipants(workList, users);
                workListParticipantsService.saveWorkListParticipants(workListParticipants);
            }
        }

        return ResponseEntity.ok(taskForm);
    }

    @PostMapping("announce/{id}")
    public ResponseEntity<String> editWorkListAnnounce(@PathVariable("id")Long id){
        WorkList workList=workListService.findWorkListById(id);
        if (workList.isAnnounce()){
            workList.setAnnounce(false);
        }else if (!workList.isAnnounce()){
            workList.setAnnounce(true);
        }
        workListService.saveWorkList(workList);
        return ResponseEntity.ok("done");
    }

    @PostMapping("finish/{id}")
    public ResponseEntity<String> editWorkListFinish(@PathVariable("id")Long id){
        WorkList workList=workListService.findWorkListById(id);
        if (workList.isFinish()){
            workList.setFinish(false);
        }else if (!workList.isFinish()){
            workList.setFinish(true);
        }
        workListService.saveWorkList(workList);
        return ResponseEntity.ok("done");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteWorkList(@PathVariable("id")Long id){
        workListParticipantsService.deleteWorkListParticipantsByWorkListId(id);
        workListService.deleteWorkListById(id);
        return ResponseEntity.ok("done");
    }

}
