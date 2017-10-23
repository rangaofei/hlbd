package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Answer;
import com.hanlinbode.hlbd.bean.HomeWork;
import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerDaoImpl implements AnswerDao {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer saveAnswer(HomeWork homeWork, Student student) {
        Answer answer = new Answer();
        answer.setAnswerHomeWork(homeWork);
        answer.setStudent(student);
        answerRepository.save(answer);
        return null;
    }

    @Override
    public Answer saveAnserByTeam(HomeWork homeWork, Team team) {
        for(Student s:team.getStudents()){
            Answer answer = new Answer();
            answer.setAnswerHomeWork(homeWork);
            answer.setAnswerId(UUIDUtil.generateId());
            answer.setAnswerContent("123");
            answer.setStudent(s);
            answerRepository.save(answer);
        }
        return null;
    }

}
