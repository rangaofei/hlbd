package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.StudentAnswerQuestion;
import com.hanlinbode.hlbd.bean.TeacherHomework;
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

    @Autowired
    private HomeWorkDao homeWorkDao;

    @Override
    public void commitAnswer(List<StudentAnswerQuestion> questionList, StudentAnswer studentAnswer) {
        for (StudentAnswerQuestion list : questionList) {
            list.setStudentId(studentAnswer.getStudentId());
            list.setStudentName(studentAnswer.getStudentName());
            list.setAnswerId(studentAnswer.getAnswerId());
            list.setTeacherHomeworkQuestionId(answerQuestionRepository
                    .findStudentAnswerQuestionById(list.getId()).getTeacherHomeworkQuestionId());
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
        for (StudentAnswerQuestion s : questionList) {
            TeacherHomeworkQuestion teacherHomeworkQuestion = homeworkQuestionDao
                    .findTeacherHomeworkQuestionById(s.getTeacherHomeworkQuestionId());
            teacherHomeworkQuestion.setAwaitCorrect(waitCorrect(s.getTeacherHomeworkQuestionId()));
        }
        TeacherHomework teacherHomework = homeWorkDao.findHomeWorkByHomeWorkId(studentAnswer.getHomeworkId());
        teacherHomework.setAwaitCorrect(homeworkQuestionDao.waitCorrect(studentAnswer.getHomeworkId()));
        homeWorkDao.updateTeacherHomeWork(teacherHomework);
    }

    @Override
    public List<StudentAnswerQuestion> findAnswerQuestionByAnswerId(String answerId) {
        return answerQuestionRepository.findStudentAnswerQuestionsByAnswerId(answerId);
    }

    @Override
    public float calculateStudentAnswerCorrectRate(String answerId) {
        List<StudentAnswerQuestion> list = answerQuestionRepository.findStudentAnswerQuestionsByAnswerId(answerId);
        float sum = 0F;
        for (StudentAnswerQuestion question : list) {
            sum += question.getScore();
            TeacherHomeworkQuestion q = homeworkQuestionDao.findTeacherHomeworkQuestionById(question.getTeacherHomeworkQuestionId());
            q.setCorrectRate((int) calculateHomeworkQuestionCorrectRate(question.getTeacherHomeworkQuestionId()));
            homeworkQuestionDao.saveTeacherHomeworkQuestion(q);
        }
        return sum / list.size();
    }

    @Override
    public float calculateHomeworkQuestionCorrectRate(int homeworkQuestionId) {
        List<StudentAnswerQuestion> list = answerQuestionRepository
                .findStudentAnswerQuestionsByTeacherHomeworkQuestionId(homeworkQuestionId);
        float sum = 0F;
        for (StudentAnswerQuestion question : list) {
            sum += question.getScore();
        }
        return sum / list.size();
    }

    @Override
    public boolean waitCorrect(int homeworkQuestionId) {
        boolean waitCorrect = false;
        List<StudentAnswerQuestion> list = answerQuestionRepository
                .findStudentAnswerQuestionsByTeacherHomeworkQuestionId(homeworkQuestionId);
        for (StudentAnswerQuestion s : list) {
            if (s.isAwaitCorrect()) {
                waitCorrect = true;
                break;
            }
        }
        return waitCorrect;
    }
}
