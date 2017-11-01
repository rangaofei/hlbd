package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.StudentAnswerQuestion;
import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnswerQuestionDaoImpl implements AnswerQuestionDao {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerQuestionRepository answerQuestionRepository;
    @Autowired
    private HomeworkQuestionDao homeworkQuestionDao;
    @Autowired
    private AnswerDao answerDao;

    @Override
    public void commitAnswer(List<StudentAnswerQuestion> questionList, StudentAnswer studentAnswer) {
        for (StudentAnswerQuestion list : questionList) {
            list.setStudentId(studentAnswer.getStudentId());
            list.setAnswerId(studentAnswer.getAnswerId());
            if (list.getQuestiontypeId() == 2
                    || list.getQuestiontypeId() == 3
                    || list.getQuestiontypeId() == 4
                    || list.getQuestiontypeId() == 5
                    || list.getQuestiontypeId() == 6
                    || list.getQuestiontypeId() == 7
                    || list.getQuestiontypeId() == 39
                    || list.getQuestiontypeId() == 66
                    || list.getQuestiontypeId() == 78) {

                if (list.getAnswer().equals(questionRepository.findQuestionById(list.getQuestionId()).getAnswer())) {
                    list.setScore(100);
                } else {
                    list.setScore(0);
                }
                list.setAwaitCorrect(false);

            } else {
                list.setAwaitCorrect(true);
                TeacherHomeworkQuestion question = homeworkQuestionDao
                        .findTeacherHomeworkQuestionById(list.getTeacherHomeworkQuestionId());
                question.setAwaitCorrect(true);
                homeworkQuestionDao.saveTeacherHomeworkQuestion(question);
            }
        }
        answerQuestionRepository.save(questionList);
        answerDao.calucateCorrectRate(studentAnswer.getAnswerId());

    }

    @Override
    public List<StudentAnswerQuestion> findAnswerQuestionByAnswerId(String answerId) {
        return answerQuestionRepository.findStudentAnswerQuestionsByAnswerId(answerId);
    }
}
