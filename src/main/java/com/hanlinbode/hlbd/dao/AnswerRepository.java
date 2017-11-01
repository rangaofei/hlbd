package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.TeacherHomework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<StudentAnswer, Long> {
    @Query("select s from StudentAnswer s where s.homeworkId=:homeworkid and s.studentId=:studentid")
    List<StudentAnswer> findAnswerByhomeworkAndStudent(@Param("homeworkid") String homeworkId, @Param("studentid") String studentId);


    StudentAnswer findStudentAnswerByAnswerId(String answerId);

    List<StudentAnswer> findStudentAnswersByTeamIdAndStudentId(String teamId, String studentId);

}
