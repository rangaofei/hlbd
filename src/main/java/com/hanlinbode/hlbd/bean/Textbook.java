package com.hanlinbode.hlbd.bean;

import javax.persistence.*;

@Entity
@Table(name = "l_textbook")
public class Textbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int versionId;
    @Column(nullable = false)
    private int xktextbookId;
    @Column(nullable = false)
    private int xkversionId;

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

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public int getXktextbookId() {
        return xktextbookId;
    }

    public void setXktextbookId(int xktextbookId) {
        this.xktextbookId = xktextbookId;
    }

    public int getXkversionId() {
        return xkversionId;
    }

    public void setXkversionId(int xkversionId) {
        this.xkversionId = xkversionId;
    }
}
