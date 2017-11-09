package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.bean.TeacherSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Long> {
    List<TeacherSubject> findTeacherSubjectsByTeacherId(String teacherId);

    @Modifying
    @Transactional
    List<TeacherSubject> deleteTeacherSubjectsById(List<Integer> ids);
}
