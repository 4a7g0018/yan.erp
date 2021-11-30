package com.yan.easy.erp.service;

import com.yan.easy.erp.model.BulletinBoard;
import com.yan.easy.erp.repository.BulletinBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BulletinBoardServiceImpl implements BulletinBoardService{

    @Autowired
    private BulletinBoardRepository bulletinBoardRepository;

    @Override
    public BulletinBoard saveBulletinBoard(BulletinBoard bulletinBoard) {
        return bulletinBoardRepository.save(bulletinBoard);
    }

    @Override
    public List<BulletinBoard> findAllBulletinBoard() {
        return bulletinBoardRepository.findAll();
    }

    @Override
    public List<BulletinBoard> findGeneralManagerBulletinBoard() {
        return bulletinBoardRepository.findBulletinBoardByDepartmentId(1L);
    }

    @Override
    public List<BulletinBoard> findDepartmentBulletinBoard(Long departmentId) {
        return bulletinBoardRepository.findBulletinBoardByDepartmentId(departmentId);
    }
}
