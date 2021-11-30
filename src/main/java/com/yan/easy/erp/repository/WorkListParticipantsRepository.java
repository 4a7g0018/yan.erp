package com.yan.easy.erp.repository;


import com.yan.easy.erp.model.WorkListParticipants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface WorkListParticipantsRepository extends JpaRepository<WorkListParticipants, Long> {

    WorkListParticipants findWorkListParticipantsById(Long id);

    List<WorkListParticipants> findWorkListParticipantsByUsersId(Long userId);

    List<WorkListParticipants> findWorkListParticipantsByWorkListId(Long workListId);

    @Modifying
    @Transactional
    void deleteWorkListParticipantsByWorkListId(Long workListId);

}
