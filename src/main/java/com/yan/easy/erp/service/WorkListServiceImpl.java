package com.yan.easy.erp.service;

import com.yan.easy.erp.model.WorkList;
import com.yan.easy.erp.repository.WorkListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkListServiceImpl implements WorkListService {

    @Autowired
    private WorkListRepository workListRepository;

    @Override
    public WorkList saveWorkList(WorkList workList) {
        return workListRepository.save(workList);
    }

    @Override
    public WorkList findWorkListById(Long id) {
        return workListRepository.findWorkListById(id);
    }

    @Override
    public List<WorkList> findAllWorkList() {
        return workListRepository.findAll();
    }

    @Override
    public List<WorkList> findWorkListsByRoleId(Long roleId) {
        return workListRepository.findWorkListByRoleId(roleId);
    }

    @Override
    public List<List<String>> findAllUndoneWorkTimes() {
        return workListRepository.findAllUndoneWorkTimes();
    }

    @Override
    public List<List<String>> findUndoneWorkTimesByRoleId(Long roleID) {
        return workListRepository.findUndoneWorkTimesByRoleId(roleID);
    }

    @Override
    public List<List<String>> findAllDoneWorkTimes() {
        return workListRepository.findAllDoneWorkTimes();
    }

    @Override
    public List<List<String>> findDoneWorkTimesByRoleId(Long roleID) {
        return workListRepository.findDoneWorkTimesByRoleId(roleID);
    }

    @Override
    public void updateWorkList(Long workListId, Long userId) {
        workListRepository.updateWorkList(workListId, userId);
    }

    @Override
    public void deleteWorkListById(Long workListId) {
        workListRepository.deleteWorkListById(workListId);
    }
}
