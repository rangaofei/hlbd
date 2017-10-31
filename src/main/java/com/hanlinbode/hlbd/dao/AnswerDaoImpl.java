package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerDaoImpl implements AnswerDao {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerQuestionRepository answerQuestionRepository;

    @Override
    public StudentAnswer saveAnswer(TeacherHomework teacherHomework, Student student) {
        StudentAnswer studentAnswer = new StudentAnswer();
//        studentAnswer.setAnswerTeacherHomework(teacherHomework);
//        studentAnswer.setStudent(student);
        answerRepository.save(studentAnswer);
        return null;
    }

    @Override
    public StudentAnswer saveAnserByTeam(TeacherHomework teacherHomework, Team team) {
        for (Student s : team.getStudents()) {
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.initWithHomeWork(teacherHomework);
            studentAnswer.setStudentId(s.getStudentId());
            studentAnswer.setStudentName(s.getName());
            studentAnswer.setTeamId(team.getTeamId());
            List<StudentAnswerQuestion> re = new ArrayList<>();
            for (TeacherHomeworkQuestion ts : teacherHomework.getTeacherHomeworkQuestions()) {
                StudentAnswerQuestion studentAnswerQuestion = new StudentAnswerQuestion(ts);
                studentAnswerQuestion.setStudentId(s.getStudentId());
                studentAnswerQuestion.setAnswerId(studentAnswer.getAnswerId());
                re.add(studentAnswerQuestion);
            }
            answerQuestionRepository.save(re);
            studentAnswer.setStudentAnswerLists(re);
            answerRepository.save(studentAnswer);
        }
        return null;
    }

    @Override
    public List<StudentAnswer> findAnswerByTeacherHomeWorkAndStudent(TeacherHomework teacherHomework, Student student) {
        return answerRepository.findAnswerByhomeworkAndStudent(teacherHomework.getHomeworkId(), student.getStudentId());

    }

    @Override
    public StudentAnswer findAnswerById(String answerId) {
        return answerRepository.findStudentAnswerByAnswerId(answerId);
    }

    @Override
    public StudentAnswer updateAnswer(StudentAnswer answer) {
        answer.setState(1);

        return answerRepository.saveAndFlush(answer);
    }

}
