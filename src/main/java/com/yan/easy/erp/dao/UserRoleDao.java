package com.yan.easy.erp.dao;

public class UserRoleDao {
    private Long id;
    private String userName;
    private String password;
    private String roles;
    private boolean enable;
    private String email;
    private String phone;

    public UserRoleDao(Long id, String userName, String password, String roles, boolean enable) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.enable = enable;
    }

    public UserRoleDao(Long id, String userName, String password, String roles, boolean enable, String email, String phone) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.enable = enable;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
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
