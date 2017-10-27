package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.TeacherHomeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeWorkRepository extends JpaRepository<TeacherHomeWork,Long> {

    @Query("select h from TeacherHomeWork h where h.fk_teacher_id=:teacher_id")
    List<TeacherHomeWork> findHomeWorkByTeacherId(@Param("teacher_id") String teacherId);
}
