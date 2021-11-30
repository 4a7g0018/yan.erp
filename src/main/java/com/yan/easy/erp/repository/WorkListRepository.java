package com.yan.easy.erp.repository;

import com.yan.easy.erp.model.WorkList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface WorkListRepository extends JpaRepository<WorkList, Long> {
    WorkList findWorkListById(Long id);

    List<WorkList> findWorkListByRoleId(Long roleId);

    @Query(value = "SELECT priority as ?1 ,COUNT(*) FROM work_list;", nativeQuery = true)
    int findAllWorkByPriority(String priority);

    @Query(value = "SELECT priority ,COUNT(priority) AS 'times' FROM work_list WHERE finish=0 GROUP by priority;", nativeQuery = true)
    List<List<String>> findAllUndoneWorkTimes();

    @Query(value = "SELECT priority ,COUNT(priority) AS 'times' FROM work_list WHERE role_id=?1 AND finish=0 GROUP by priority;", nativeQuery = true)
    List<List<String>> findUndoneWorkTimesByRoleId(Long roleId);

    @Query(value = "SELECT priority ,COUNT(priority) AS 'times' FROM work_list WHERE finish=1 GROUP by priority;", nativeQuery = true)
    List<List<String>> findAllDoneWorkTimes();

    @Query(value = "SELECT priority ,COUNT(priority) AS 'times' FROM work_list WHERE role_id=?1 AND finish=1 GROUP by priority;", nativeQuery = true)
    List<List<String>> findDoneWorkTimesByRoleId(Long roleId);

    @Modifying
    @Query(value = "UPDATE work_list_participants w SET w.user_id=?2 WHERE w.work_list_id=?1", nativeQuery = true)
    void updateWorkList(Long workListId, Long userId);

    @Modifying
    @Transactional
    void deleteWorkListById(Long id);
}
