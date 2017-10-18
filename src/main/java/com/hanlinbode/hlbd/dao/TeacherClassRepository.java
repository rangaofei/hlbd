package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.bean.TeacherClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherClassRepository extends JpaRepository<TeacherClass, Long> {
    @Query("select t from TeacherClass t where t.teacherId=:teacherId")
    List<TeacherClass> findTeacherClassesById(@Param("teacherId") int teacherId);
}
