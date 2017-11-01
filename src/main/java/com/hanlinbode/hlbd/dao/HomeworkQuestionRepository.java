package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkQuestionRepository extends JpaRepository<TeacherHomeworkQuestion, Long> {
    List<TeacherHomeworkQuestion> findTeacherHomeworkQuestionsByTeacherHomeworkId(String homeworkId);

    TeacherHomeworkQuestion findTeacherHomeworkQuestionById(int id);


}
