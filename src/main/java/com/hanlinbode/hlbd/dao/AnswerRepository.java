package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {

}
