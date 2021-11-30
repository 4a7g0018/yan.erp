package com.yan.easy.erp.service;

import com.yan.easy.erp.model.BulletinBoard;

import java.util.List;

public interface BulletinBoardService {
    BulletinBoard saveBulletinBoard(BulletinBoard bulletinBoard);
    List<BulletinBoard> findAllBulletinBoard();
    List<BulletinBoard> findGeneralManagerBulletinBoard();
    List<BulletinBoard> findDepartmentBulletinBoard(Long departmentId);
}
