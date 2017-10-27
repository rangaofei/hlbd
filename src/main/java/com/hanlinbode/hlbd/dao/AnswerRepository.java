package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.TeacherHomeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<StudentAnswer, Long> {
    @Query("select s from StudentAnswer s where s.fk_homework_id=:homeworkid and s.fk_student_id=:studentid")
    List<StudentAnswer> findAnswerByhomeworkAndStudent(@Param("homeworkid") String homeworkId, @Param("studentid") String studentId);

    List<StudentAnswer> findStudentAnswersByAnswerTeacherHomeWorkAndStudent(TeacherHomeWork teacherHomeWork, Student student);

    StudentAnswer findStudentAnswerByAnswerId(String answerId);
}
