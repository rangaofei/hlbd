package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.StudentAnswerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerQuestionRepository extends JpaRepository<StudentAnswerQuestion, Long> {

    List<StudentAnswerQuestion> findStudentAnswerQuestionsByAnswerId(String answerId);
}
