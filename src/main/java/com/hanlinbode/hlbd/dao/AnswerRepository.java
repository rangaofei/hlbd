package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.enums.AnswerState;
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

    List<StudentAnswer> findStudentAnswersByTeamIdAndHomeworkIdAndStateNotLike(String teamId, String homeworkId,AnswerState state);

    @Query(value = "SELECT sum(cost_time) FROM student_answer WHERE student_id=?1", nativeQuery = true)
    Integer getTotalTime(String studentId);

    @Query(value = "SELECT DISTINCT subject_name FROM student_answer WHERE student_id=?1",
            nativeQuery = true)
    List<String> getAllSubject(String studentId);

    @Query(value = "SELECT sum(cost_time) FROM student_answer WHERE student_id=?1 AND subject_name=?2",
            nativeQuery = true)
    Integer getCostTimeBySubjectName(String studentId, String subjectName);

    @Query(value = "select s from StudentAnswer s where student_id=:studentId and subject_name=:subject" +
            " and type=2 and (state='CORRECT' or state='COMMIT')")
    List<StudentAnswer> getAnswerBySubjectAndStudentId(@Param("studentId") String studentId, @Param("subject") String subject);
}
