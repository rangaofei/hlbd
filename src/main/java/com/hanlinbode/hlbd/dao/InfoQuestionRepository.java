package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.TeacherHomeworkList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoQuestionRepository extends JpaRepository<TeacherHomeworkList, Long> {
}
