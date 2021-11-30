package com.yan.easy.erp.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class TaskForm {
    private Long id;
    private String workName;
    private String priority;
    private String date;
    private List<String> participants;
    private String illustrate;
    private String announce;
    private String  finish;
    private Long roleId;

    public TaskForm(){}

    public TaskForm(String workName) {
        this.workName = workName;
    }

    public TaskForm(String workName, String priority, String date) {
        this.workName = workName;
        this.priority = priority;
        this.date = date;
    }

    public TaskForm(String workName, String priority, String date, String illustrate) {
        this.workName = workName;
        this.priority = priority;
        this.date = date;
        this.illustrate = illustrate;
    }

    public TaskForm(String workName, String priority, String date, String illustrate, String announce, String finish) {
        this.workName = workName;
        this.priority = priority;
        this.date = date;
        this.illustrate = illustrate;
        this.announce = announce;
        this.finish = finish;
    }

    public TaskForm(String workName, String priority, String date, List<String> participants, String illustrate, String announce, String finish) {
        this.workName = workName;
        this.priority = priority;
        this.date = date;
        this.participants = participants;
        this.illustrate = illustrate;
        this.announce = announce;
        this.finish = finish;
    }

    public TaskForm(Long id, String workName, String priority, String date, String illustrate, String announce, String finish) {
        this.id = id;
        this.workName = workName;
        this.priority = priority;
        this.date = date;
        this.participants = participants;
        this.illustrate = illustrate;
        this.announce = announce;
        this.finish = finish;
    }

    public TaskForm(Long id, String workName, String priority, String date, List<String> participants, String illustrate, String announce, String finish, Long roleId) {
        this.id = id;
        this.workName = workName;
        this.priority = priority;
        this.date = date;
        this.participants = participants;
        this.illustrate = illustrate;
        this.announce = announce;
        this.finish = finish;
        this.roleId = roleId;
    }

    public TaskForm(Long id, String workName, String priority, String date, List<String> participants, String illustrate, String announce, String finish) {
        this.id = id;
        this.workName = workName;
        this.priority = priority;
        this.date = date;
        this.participants = participants;
        this.illustrate = illustrate;
        this.announce = announce;
        this.finish = finish;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }

    public String getAnnounce() {
        return announce;
    }

    public void setAnnounce(String announce) {
        this.announce = announce;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
