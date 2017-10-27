package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.HomeworkQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoQuestionRepository extends JpaRepository<HomeworkQuestion, Long> {
}
