package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Teacher;
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

    @Override
    public boolean waitCorrect(String homeworkId) {
        boolean result = false;
        List<TeacherHomeworkQuestion> list = homeworkQuestionRepository
                .findTeacherHomeworkQuestionsByTeacherHomeworkId(homeworkId);
        for (TeacherHomeworkQuestion t : list) {
            if (t.isAwaitCorrect()) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public float calculateCorrectRate(String homeworkId) {
        float result = 0F;
        List<TeacherHomeworkQuestion> list = homeworkQuestionRepository.findTeacherHomeworkQuestionsByTeacherHomeworkId(homeworkId);
        for (TeacherHomeworkQuestion t : list) {
            result += t.getCorrectRate();
        }

        return result / list.size();
    }


}
