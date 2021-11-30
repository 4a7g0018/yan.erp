package com.yan.easy.erp;

import com.yan.easy.erp.model.BulletinBoard;
import com.yan.easy.erp.service.BulletinBoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class BulletinBoardServiceTest {

    @Autowired
    private BulletinBoardService bulletinBoardService;

    @Test
    public void saveBulletinBoard(){
        BulletinBoard bulletinBoard=new BulletinBoard("test3",new Date(),"rd123","tsaaasaadsasaadsasast",13L);
        bulletinBoardService.saveBulletinBoard(bulletinBoard);
    }

    @Test
    public void findBulletinBoardByDepartmentId(){
        List<BulletinBoard> bulletinBoard = bulletinBoardService.findDepartmentBulletinBoard(13L);
        System.out.println(bulletinBoard);
    }
}
