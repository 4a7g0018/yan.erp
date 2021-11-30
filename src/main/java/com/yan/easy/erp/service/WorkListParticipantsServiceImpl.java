package com.yan.easy.erp.service;

import java.util.List;

import com.yan.easy.erp.model.WorkListParticipants;
import com.yan.easy.erp.repository.WorkListParticipantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkListParticipantsServiceImpl implements WorkListParticipantsService{

    @Autowired
    private WorkListParticipantsRepository workListParticipantsRepository;

    @Override
    public WorkListParticipants saveWorkListParticipants(WorkListParticipants workListParticipants) {
        return workListParticipantsRepository.save(workListParticipants);
    }

    @Override
    public WorkListParticipants findWorkListParticipantsById(Long id) {
        return workListParticipantsRepository.findWorkListParticipantsById(id);
    }

    @Override
    public List<WorkListParticipants> findWorkListParticipantsByUsersId(Long userId) {
        return workListParticipantsRepository.findWorkListParticipantsByUsersId(userId);
    }

    @Override
    public List<WorkListParticipants> findWorkListParticipantsByWorkListId(Long workListId) {
        return workListParticipantsRepository.findWorkListParticipantsByWorkListId(workListId);
    }

    @Override
    public void deleteWorkListParticipantsByWorkListId(Long workListId) {
        workListParticipantsRepository.deleteWorkListParticipantsByWorkListId(workListId);
    }

}
