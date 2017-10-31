package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.*;
import com.hanlinbode.hlbd.util.UUIDUtil;
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

    @Override
    public StudentAnswer saveAnswer(TeacherHomeWork teacherHomeWork, Student student) {
        StudentAnswer studentAnswer = new StudentAnswer();
//        studentAnswer.setAnswerTeacherHomeWork(teacherHomeWork);
        studentAnswer.setStudent(student);
        answerRepository.save(studentAnswer);
        return null;
    }

    @Override
    public StudentAnswer saveAnserByTeam(TeacherHomeWork teacherHomeWork, Team team) {
        for (Student s : team.getStudents()) {
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.initWithHomeWork(teacherHomeWork);
            studentAnswer.setStudent(s);
            List<StudentAnswerList> re = new ArrayList<>();
            for (TeacherHomeworkList ts : teacherHomeWork.getTeacherHomeworkLists()) {
                StudentAnswerList studentAnswerList = new StudentAnswerList(ts);
                re.add(studentAnswerList);
            }
            studentAnswer.setStudentAnswerLists(re);
            for (StudentAnswerList sList : studentAnswer.getStudentAnswerLists()) {
                sList.setStudentAnswer(studentAnswer);
            }
            answerRepository.save(studentAnswer);
        }
        return null;
    }

    @Override
    public List<StudentAnswer> findAnswerByTeacherHomeWorkAndStudent(TeacherHomeWork teacherHomeWork, Student student) {
        return answerRepository.findAnswerByhomeworkAndStudent(teacherHomeWork.getHomeworkId(), student.getStudentId());

    }

    @Override
    public StudentAnswer findAnswerById(String answerId) {
        return answerRepository.findStudentAnswerByAnswerId(answerId);
    }

    @Override
    public StudentAnswer updateAnswer(StudentAnswer answer) {
        answer.setState(1);
        for (StudentAnswerList list : answer.getStudentAnswerLists()) {
            if (list.getQuestiontypeId() == 2) {
                if (list.getAnswer().equals(questionRepository.findQuestionById(list.getQuestionId()))) {
                    list.setScore(100);
                } else {
                    list.setScore(0);
                }
            }
        }
        return answerRepository.saveAndFlush(answer);
    }

}
