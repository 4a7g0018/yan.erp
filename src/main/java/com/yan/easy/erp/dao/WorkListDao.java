package com.yan.easy.erp.dao;

import java.util.Date;
import java.util.List;

public class WorkListDao {
    private Long id;
    private String workName;
    private int priority;
    private Date date;
    private List<String> participants;
    private String illustrate;
    private boolean announce;
    private boolean finish;
    private Long roleId;

    public WorkListDao() {
    }

    public WorkListDao(String workName) {
        this.workName = workName;
    }

    public WorkListDao(String workName, int priority, Date date) {
        this.workName = workName;
        this.priority = priority;
        this.date = date;
    }

    public WorkListDao(String workName, int priority, Date date, List<String> participants, String illustrate, boolean announce, boolean finish) {
        this.workName = workName;
        this.priority = priority;
        this.date = date;
        this.participants = participants;
        this.illustrate = illustrate;
        this.announce = announce;
        this.finish = finish;

    }

    public WorkListDao(Long id, String workName, int priority, Date date, List<String> participants, String illustrate, boolean announce, boolean finish) {
        this.id = id;
        this.workName = workName;
        this.priority = priority;
        this.date = date;
        this.participants = participants;
        this.illustrate = illustrate;
        this.announce = announce;
        this.finish = finish;
    }

    public WorkListDao(Long id, String workName, int priority, Date date, List<String> participants, String illustrate, boolean announce, boolean finish, Long roleId) {
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }

    public boolean isAnnounce() {
        return announce;
    }

    public void setAnnounce(boolean announce) {
        this.announce = announce;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
