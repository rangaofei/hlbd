package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.bean.TeacherSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Long> {
//    @Query("select t from TeacherSubject t where t.teacher_id=:teacherId")
//    List<TeacherSubject> findTeacherSubjectsByTeacherId(@Param("teacherId") int teacherId);
}
