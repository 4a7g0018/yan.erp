package com.yan.easy.erp.controller.fd;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/fd/work_list")
@Controller
@Slf4j
public class FinancialWorkListController {

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
        log.info("userRole:"+userRole);
        log.info("userRole.getId():"+userRole.getRoles());
        Roles roles=rolesServiceImpl.findRolesById(userRole.getId());
        log.info("roles:"+roles);
        return userRole.getRoles();
    }

    @GetMapping("get_user")
    public ResponseEntity<List<String>> getUserNameList() {
        Long managerId = rolesServiceImpl.findRolesByName("FD_MANAGER").getId();
        Long userId = rolesServiceImpl.findRolesByName("FD_USER").getId();

        List<UserRole> userRoleList = userRoleServiceImpl.findUserRoleByRolesId(managerId);
        userRoleList.addAll(userRoleServiceImpl.findUserRoleByRolesId(userId));

        List<String> userNames = new ArrayList<>();
        for (UserRole userRole : userRoleList){
            userNames.add(userRole.getUsers().getUserName());
        }

//        for (Users users : userServiceImpl.findAllUser()) {
//            userNames.add(users.getUserName());
//        }
        return ResponseEntity.ok().body(userNames);
    }

    @GetMapping("is_manager")
    public ResponseEntity<Boolean> isManager(){
        Roles userRole = userRoleServiceImpl.findUserRoleByUsersId(getUser().getId()).getRoles();
        return ResponseEntity.ok(userRole.getName().contains("MANAGER"));
    }

    @GetMapping("get_all_work")
    public ResponseEntity<List<WorkListDao>> getAllWorkList() {
        Roles userRole = userRoleServiceImpl.findUserRoleByUsersId(getUser().getId()).getRoles();
        Roles role = rolesServiceImpl.findRolesByName("FD_MANAGER");
        List<WorkList> workLists=workListService.findWorkListsByRoleId(role.getId());

        log.info("workLists:"+workLists.size());
//        List<WorkList> workLists = workListService.findAllWorkList();

        List<WorkListDao> workListDaoList =new ArrayList<>();
        if (workLists.size() >0 ){
            for (WorkList workList : workLists){
                WorkListDao workListDao=new WorkListDao();
                workListDao.setId(workList.getId());
                workListDao.setWorkName(workList.getJobTitle());
                workListDao.setPriority(workList.getPriority());
                workListDao.setDate(workList.getDate());
                workListDao.setIllustrate(workList.getIllustrate());
                workListDao.setAnnounce(workList.isAnnounce());
                workListDao.setFinish(workList.isFinish());
                workListDao.setRoleId(getUserRole().getId());
                log.info("Role id = "+getUserRole().getId());

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
        }else{
            WorkListDao workListDao =new WorkListDao();
            workListDao.setRoleId(role.getId());
            workListDaoList.add(workListDao);
        }


        return ResponseEntity.ok().body(workListDaoList);
    }

    @GetMapping("get_announce_work")
    public ResponseEntity<List<WorkListDao>> getAnnounceWorkList() {
        Roles userRole = userRoleServiceImpl.findUserRoleByUsersId(getUser().getId()).getRoles();
        Roles role = rolesServiceImpl.findRolesByName("FD_MANAGER");
        List<WorkList> workLists=workListService.findWorkListsByRoleId(role.getId());

        log.info("workLists:"+workLists.size());

        List<WorkListDao> workListDaoList =new ArrayList<>();
        if (workLists.size() >0 ){
            for (WorkList workList : workLists){
                if (workList.isAnnounce() && !workList.isFinish()){
                    WorkListDao workListDao=new WorkListDao();
                    workListDao.setId(workList.getId());
                    workListDao.setWorkName(workList.getJobTitle());
                    workListDao.setPriority(workList.getPriority());
                    workListDao.setDate(workList.getDate());
                    workListDao.setIllustrate(workList.getIllustrate());
                    workListDao.setAnnounce(workList.isAnnounce());
                    workListDao.setFinish(workList.isFinish());
                    workListDao.setRoleId(getUserRole().getId());
                    log.info("Role id = "+getUserRole().getId());

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
            }
        }else{
            WorkListDao workListDao =new WorkListDao();
            workListDao.setRoleId(role.getId());
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
