package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.*;
import com.hanlinbode.hlbd.dao.AnswerQuestionRepository;
import com.hanlinbode.hlbd.dao.AnswerRepository;
import com.hanlinbode.hlbd.dao.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerQuestionRepository answerQuestionRepository;
    @Autowired
    private AnswerQuestionService answerQuestionService;
    @Autowired
    private HomeworkQuestionService homeworkQuestionService;

    @Override
    public StudentAnswer saveAnswer(TeacherHomework teacherHomework, Student student) {
        StudentAnswer studentAnswer = new StudentAnswer();
//        studentAnswer.setAnswerTeacherHomework(teacherHomework);
//        studentAnswer.setStudent(student);
        answerRepository.save(studentAnswer);
        return null;
    }

    @Override
    public List<StudentAnswer> saveAnswerByTeam(TeacherHomework teacherHomework, Team team) {
        List<StudentAnswer> list = new ArrayList<>();
        for (Student s : team.getStudents()) {
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.initWithHomeWork(teacherHomework);
            studentAnswer.setStudentId(s.getStudentId());
            studentAnswer.setStudentName(s.getName());
            studentAnswer.setTeamId(team.getTeamId());
            list.add(studentAnswer);
        }
        return answerRepository.save(list);
    }

    @Override
    public List<StudentAnswer> saveAnswerByTeams(TeacherHomework teacherHomework, List<Team> teams) {
        List<StudentAnswer> list = new ArrayList<>();
        for (Team team : teams) {
            for (Student s : team.getStudents()) {
                StudentAnswer studentAnswer = new StudentAnswer();
                studentAnswer.initWithHomeWork(teacherHomework);
                studentAnswer.setStudentId(s.getStudentId());
                studentAnswer.setStudentName(s.getName());
                studentAnswer.setTeamId(team.getTeamId());
                list.add(studentAnswer);
            }
        }
        return answerRepository.save(list);
    }

    @Override
    public List<StudentAnswer> saveAnswerByStudents(TeacherHomework teacherHomework, List<Student> students) {
        List<StudentAnswer> list = new ArrayList<>();
        for (Student s : students) {
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.initWithHomeWork(teacherHomework);
            studentAnswer.setStudentId(s.getStudentId());
            studentAnswer.setStudentName(s.getName());
            list.add(studentAnswer);
        }
        return null;
    }

    @Override
    public List<StudentAnswer> findAnswerByTeamAndStudent(String teamId, String studentId) {
        return answerRepository.findStudentAnswersByTeamIdAndStudentId(teamId, studentId);
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

    @Override
    public StudentAnswer calucateCorrectRate(String answerId) {
        StudentAnswer studentAnswer = findAnswerById(answerId);
        studentAnswer.setCorrectRate(answerQuestionService.calculateStudentAnswerCorrectRate(answerId));
        //更新老师表中的正确率

        return answerRepository.save(studentAnswer);
    }

}
