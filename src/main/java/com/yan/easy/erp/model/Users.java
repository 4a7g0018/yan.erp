package com.yan.easy.erp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "enable")
    private boolean enable;

    @Lob
    @Column(name = "image")
    private String image;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    public Users(String userName, String password, boolean enable) {
        this.userName = userName;
        this.password = password;
        this.enable = enable;
    }

    public Users(String userName, String password, boolean enable, String image) {
        this.userName = userName;
        this.password = password;
        this.enable = enable;
        this.image = image;
    }

    public Users(String userName, String password, boolean enable, String image, String email, String phone) {
        this.userName = userName;
        this.password = password;
        this.enable = enable;
        this.image = image;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
