package com.yan.easy.erp.dao;

public class BulletinBoardDao {
    private String title;
    private String date;
    private String sponsor;
    private String content;

    public BulletinBoardDao(String title, String date, String sponsor, String content) {
        this.title = title;
        this.date = date;
        this.sponsor = sponsor;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
