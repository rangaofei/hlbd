package com.hanlinbode.hlbd.bean;

import javax.persistence.*;

@Entity
@Table(name = "l_booknode")
public class Booknode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int pid;
    private int textbook_id;
    private int xkbooknode_id;
    private int xkpid;
    private int xktextbook_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getTextbook_id() {
        return textbook_id;
    }

    public void setTextbook_id(int textbook_id) {
        this.textbook_id = textbook_id;
    }

    public int getXkbooknode_id() {
        return xkbooknode_id;
    }

    public void setXkbooknode_id(int xkbooknode_id) {
        this.xkbooknode_id = xkbooknode_id;
    }

    public int getXkpid() {
        return xkpid;
    }

    public void setXkpid(int xkpid) {
        this.xkpid = xkpid;
    }

    public int getXktextbook_id() {
        return xktextbook_id;
    }

    public void setXktextbook_id(int xktextbook_id) {
        this.xktextbook_id = xktextbook_id;
    }
}
