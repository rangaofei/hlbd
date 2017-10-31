package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findQuestionById(int questionId);

}
