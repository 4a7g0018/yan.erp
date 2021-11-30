package com.yan.easy.erp.controller.general_manager;

import com.yan.easy.erp.RolesList;
import com.yan.easy.erp.dao.BulletinBoardDao;
import com.yan.easy.erp.model.BulletinBoard;
import com.yan.easy.erp.model.HomeData;
import com.yan.easy.erp.model.Users;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/gm/index")
@Controller
@Slf4j
public class GMIndexController {

    @Autowired
    private BulletinBoardService bulletinBoardService;

    @Autowired
    private RolesServiceImpl rolesService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userServiceImpl.findByUserName(authentication.getName());
    }

    @GetMapping("get_name")
    public ResponseEntity<String> getName(){
        return ResponseEntity.ok(getUser().getUserName());
    }

    @GetMapping("get_bulletin_board")
    public ResponseEntity<List<BulletinBoard>> getBulletinBoardByDepartmentId() {
        List<BulletinBoard> bulletinBoardList = bulletinBoardService.findAllBulletinBoard();

        return ResponseEntity.ok(bulletinBoardList);
    }

    @PostMapping("save_bulletin_board")
    public ResponseEntity<?> saveBulletinBoard(BulletinBoardDao bulletinBoardDao){
        Long roleId = rolesService.findRolesByName(RolesList.GENERAL_MANAGER.name()).getId();
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
