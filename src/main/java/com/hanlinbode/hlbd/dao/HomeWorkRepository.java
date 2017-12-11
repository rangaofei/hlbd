package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.TeacherHomework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HomeWorkRepository extends JpaRepository<TeacherHomework, Long> {

//    @Query("select h from TeacherHomework h where h.teacherId=:teacher_id")
//    List<TeacherHomework> findHomeWorkByTeacherId(@Param("teacher_id") String teacherId);

    List<TeacherHomework> findTeacherHomeworksByTeacherId(String teacherId);

    TeacherHomework findTeacherHomeWorkByHomeworkId(String homeworkId);
    @Modifying
    @Query("delete from TeacherHomework t where t.homeworkId=:homeworkId ")
    int deleteByHomeworkId(@Param("homeworkId") String homeworkId);


}
