package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HomeworkQuestionDaoImpl implements HomeworkQuestionDao {
    @Autowired
    private HomeworkQuestionRepository homeworkQuestionRepository;

    @Override
    public List<TeacherHomeworkQuestion> findQuestionsByHomeworkId(String homeworkId) {
        return homeworkQuestionRepository.findTeacherHomeworkQuestionsByTeacherHomeworkId(homeworkId);
    }

    @Override
    public List<TeacherHomeworkQuestion> updateCommitState(String homeworkQuestionId) {
        return null;
    }

    @Override
    public TeacherHomeworkQuestion findTeacherHomeworkQuestionById(int homeworkQuestionId) {
        return homeworkQuestionRepository.findTeacherHomeworkQuestionById(homeworkQuestionId);
    }

    @Override
    public TeacherHomeworkQuestion saveTeacherHomeworkQuestion(TeacherHomeworkQuestion question) {
        return homeworkQuestionRepository.save(question);
    }


}
