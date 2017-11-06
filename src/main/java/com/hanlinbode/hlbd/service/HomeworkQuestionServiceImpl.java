package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import com.hanlinbode.hlbd.dao.HomeworkQuestionRepository;
import com.hanlinbode.hlbd.service.HomeworkQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HomeworkQuestionServiceImpl implements HomeworkQuestionService {
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
    public List<TeacherHomeworkQuestion> setQuestionHomeworkId(List<TeacherHomeworkQuestion> list,String homeworkId) {
        for(TeacherHomeworkQuestion question:list){
            question.setTeacherHomeworkId(homeworkId);
        }
        return homeworkQuestionRepository.save(list);
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
