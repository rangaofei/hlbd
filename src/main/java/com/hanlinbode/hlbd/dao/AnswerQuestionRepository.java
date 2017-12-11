package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.StudentAnswerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerQuestionRepository extends JpaRepository<StudentAnswerQuestion, Long> {

    List<StudentAnswerQuestion> findStudentAnswerQuestionsByAnswerId(String answerId);

    List<StudentAnswerQuestion> findStudentAnswerQuestionsByTeacherHomeworkQuestionId(int id);

    StudentAnswerQuestion findStudentAnswerQuestionById(int id);

    @Query("delete from StudentAnswerQuestion s where s.teacherHomeworkQuestionId=:homework_id")
    int deleteAnswerQuestionByHomeworkId(@Param("homework_id") int homeworkId);

}
