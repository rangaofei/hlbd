package com.hanlinbode.hlbd.bean;


import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UID;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.INSERT)
    private Date crteatedTime;


    public Teacher() {
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCrteatedTime() {
        return crteatedTime;
    }

    public void setCrteatedTime(Date crteatedTime) {
        this.crteatedTime = crteatedTime;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "UID=" + UID +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
