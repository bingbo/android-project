package com.ibingbo.models;

/**
 * Created by zhangbingbing on 16/8/1.
 */
public class User {


    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String name;
    private String password;
    private String email;

    public User(String nm,String pwd,String eml){
        this.name=nm;
        this.password=pwd;
        this.email=eml;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
