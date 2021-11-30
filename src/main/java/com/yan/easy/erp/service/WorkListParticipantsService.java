package com.yan.easy.erp.service;

import java.util.List;

import com.yan.easy.erp.model.WorkListParticipants;

public interface WorkListParticipantsService {

    WorkListParticipants saveWorkListParticipants(WorkListParticipants workListParticipants);

    WorkListParticipants findWorkListParticipantsById(Long id);

    List<WorkListParticipants> findWorkListParticipantsByUsersId(Long userId);

    List<WorkListParticipants> findWorkListParticipantsByWorkListId(Long workListId);

    void deleteWorkListParticipantsByWorkListId(Long workListId);
}
