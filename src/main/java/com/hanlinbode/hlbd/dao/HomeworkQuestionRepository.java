package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkQuestionRepository extends JpaRepository<TeacherHomeworkQuestion, Long> {
    //    @Query("select t from TeacherHomeworkQuestion t where t.teacherHomeworkId=:homeworkId")
    List<TeacherHomeworkQuestion> findTeacherHomeworkQuestionsByTeacherHomeworkId(String homeworkId);

    TeacherHomeworkQuestion findTeacherHomeworkQuestionById(int id);

    @Modifying
    @Query("delete from TeacherHomeworkQuestion t where t.teacherHomeworkId=:homework_id")
    int deleteTeacherHomeworkQuestionsByTeacherHomeworkId(@Param("homework_id") String teacherHomewokrId);
}
