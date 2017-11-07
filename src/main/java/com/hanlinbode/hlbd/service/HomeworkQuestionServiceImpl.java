package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import com.hanlinbode.hlbd.dao.HomeworkQuestionRepository;
import com.hanlinbode.hlbd.enums.AnswerState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public List<TeacherHomeworkQuestion> setQuestionHomeworkId(List<TeacherHomeworkQuestion> list, String homeworkId) {
        for (TeacherHomeworkQuestion question : list) {
            question.setTeacherHomeworkId(homeworkId);
            question.setState(AnswerState.NOT_COMMIT);
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
    public AnswerState homeworkState(String homeworkId) {
        List<TeacherHomeworkQuestion> list = homeworkQuestionRepository
                .findTeacherHomeworkQuestionsByTeacherHomeworkId(homeworkId);
        for (TeacherHomeworkQuestion t : list) {
            if (t.getState() == AnswerState.WAIT_CORRECT) {
                return AnswerState.WAIT_CORRECT;
            }
        }
        return AnswerState.COMMIT;
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
