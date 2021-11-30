package com.yan.easy.erp.service;

import com.yan.easy.erp.model.WorkList;

import java.util.List;

public interface WorkListService {
    WorkList saveWorkList(WorkList workList);

    WorkList findWorkListById(Long id);

    List<WorkList> findAllWorkList();

    List<WorkList> findWorkListsByRoleId(Long roleId);

    List<List<String>> findAllUndoneWorkTimes();

    List<List<String>> findUndoneWorkTimesByRoleId(Long roleID);

    List<List<String>> findAllDoneWorkTimes();

    List<List<String>> findDoneWorkTimesByRoleId(Long roleID);

    void updateWorkList(Long workListId,Long userId);

    void deleteWorkListById(Long workListId);
}
