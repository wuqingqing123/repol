package com.wu.pojo;

import java.io.Serializable;

/**
 * 会员的实体类
 */
public class Member implements Serializable
{
    /*******************************私有属性****************************************/
    private String id; //会员id
    private String name; //会员姓名
    private String nickname; //昵称
    private String phoneNum; //电话号码
    private String email; //邮箱

    /**************************************set/get**********************************/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
