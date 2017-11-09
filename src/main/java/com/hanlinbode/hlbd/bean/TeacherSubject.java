package com.hanlinbode.hlbd.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties({})
@Entity
public class TeacherSubject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int stageId;
    @Column(nullable = false)
    private String stage;
    @Column(nullable = false)
    private int subjectId;
    @Column(nullable = false)
    private String subject;
    @Column(nullable = false)
    private int versionId;
    @Column(nullable = false)
    private String version;
    @Column(nullable = false)
    private int textbookId;
    @Column(nullable = false)
    private String textbook;

    private String teacherId;


    public String getStage() {
        return stage;
    }

    public void setStage(String grade) {
        this.stage = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTextbook() {
        return textbook;
    }

    public void setTextbook(String textbook) {
        this.textbook = textbook;
    }


    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public int getTextbookId() {
        return textbookId;
    }

    public void setTextbookId(int textbookId) {
        this.textbookId = textbookId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TeacherSubject{" +
                "id=" + id +
                ", grade='" + stage + '\'' +
                ", subject='" + subject + '\'' +
                ", version='" + version + '\'' +
                ", textbook='" + textbook + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherSubject that = (TeacherSubject) o;

        if (stageId != that.stageId) return false;
        if (subjectId != that.subjectId) return false;
        if (versionId != that.versionId) return false;
        if (textbookId != that.textbookId) return false;
        if (stage != null ? !stage.equals(that.stage) : that.stage != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (textbook != null ? !textbook.equals(that.textbook) : that.textbook != null) return false;
        return teacherId != null ? teacherId.equals(that.teacherId) : that.teacherId == null;
    }

    @Override
    public int hashCode() {
        int result = stageId;
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        result = 31 * result + subjectId;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + versionId;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + textbookId;
        result = 31 * result + (textbook != null ? textbook.hashCode() : 0);
        result = 31 * result + (teacherId != null ? teacherId.hashCode() : 0);
        return result;
    }
}
