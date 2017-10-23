package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

//    List<Teacher> findTeacherByPhone(@Param("phone") String phone);


    @Query("select t from Teacher t where t.name=:name")
    List<Teacher> findTeacherByName(@Param("name") String name);

    @Query("select t from Teacher t where t.teacherId=:teacher_id")
    Teacher findTeacherByTeacherId(@Param("teacher_id") String teacherId);

    @Query("select t from Teacher t where t.phone=:phone")
    Teacher findTeacherByPhone(@Param("phone") String phone);
}
