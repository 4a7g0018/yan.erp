package com.yan.easy.erp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "priority", nullable = false)
    private int priority;

    @Column(name = "finish", nullable = false)
    private boolean finish;

    @Column(name = "sponsor", nullable = false)
    private String sponsor;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "illustrate", nullable = false)
    private String illustrate;

    @Column(name = "announce", nullable = false)
    private boolean announce;

    @Column(name = "roleId", nullable = false)
    private Long roleId;

    public WorkList(String jobTitle, int priority, boolean finish, String sponsor, Date date, String illustrate, boolean announce) {
        this.jobTitle = jobTitle;
        this.priority = priority;
        this.finish = finish;
        this.sponsor = sponsor;
        this.date = date;
        this.illustrate = illustrate;
        this.announce = announce;
    }

    public WorkList(String jobTitle, int priority, boolean finish, String sponsor, Date date, String illustrate, boolean announce, Long roleId) {
        this.jobTitle = jobTitle;
        this.priority = priority;
        this.finish = finish;
        this.sponsor = sponsor;
        this.date = date;
        this.illustrate = illustrate;
        this.announce = announce;
        this.roleId = roleId;
    }

    public WorkList(Long roleId) {
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
