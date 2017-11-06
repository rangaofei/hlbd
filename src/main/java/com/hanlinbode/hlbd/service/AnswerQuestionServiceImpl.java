package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.StudentAnswerQuestion;
import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import com.hanlinbode.hlbd.dao.AnswerQuestionRepository;
import com.hanlinbode.hlbd.dao.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerQuestionServiceImpl implements AnswerQuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerQuestionRepository answerQuestionRepository;
    @Autowired
    private HomeworkQuestionService homeworkQuestionService;
    @Autowired
    private AnswerService answerService;

    @Autowired
    private HomeWorkService homeWorkService;

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
                TeacherHomeworkQuestion question = homeworkQuestionService
                        .findTeacherHomeworkQuestionById(list.getTeacherHomeworkQuestionId());
                question.setAwaitCorrect(true);
                homeworkQuestionService.saveTeacherHomeworkQuestion(question);
            }
        }
        answerQuestionRepository.save(questionList);
        answerService.calucateCorrectRate(studentAnswer.getAnswerId());
        for (StudentAnswerQuestion s : questionList) {
            TeacherHomeworkQuestion teacherHomeworkQuestion = homeworkQuestionService
                    .findTeacherHomeworkQuestionById(s.getTeacherHomeworkQuestionId());
            teacherHomeworkQuestion.setAwaitCorrect(waitCorrect(s.getTeacherHomeworkQuestionId()));
        }
        TeacherHomework teacherHomework = homeWorkService.findHomeWorkByHomeWorkId(studentAnswer.getHomeworkId());
        teacherHomework.setAwaitCorrect(homeworkQuestionService.waitCorrect(studentAnswer.getHomeworkId()));
        teacherHomework.setCorrectRate(homeworkQuestionService.calculateCorrectRate(studentAnswer.getHomeworkId()));
        homeWorkService.updateTeacherHomeWork(teacherHomework);
    }

    @Override
    public List<StudentAnswerQuestion> findAnswerQuestionByAnswerId(String answerId) {
        return answerQuestionRepository.findStudentAnswerQuestionsByAnswerId(answerId);
    }

    @Override
    public List<StudentAnswerQuestion> findAnserQuesitonByHomeworkQuestionId(int id) {
        return answerQuestionRepository.findStudentAnswerQuestionsByTeacherHomeworkQuestionId(id);
    }

    @Override
    public float calculateStudentAnswerCorrectRate(String answerId) {
        List<StudentAnswerQuestion> list = answerQuestionRepository.findStudentAnswerQuestionsByAnswerId(answerId);
        float sum = 0F;
        for (StudentAnswerQuestion question : list) {
            sum += question.getScore();
            TeacherHomeworkQuestion q = homeworkQuestionService.findTeacherHomeworkQuestionById(question.getTeacherHomeworkQuestionId());
            q.setCorrectRate((int) calculateHomeworkQuestionCorrectRate(question.getTeacherHomeworkQuestionId()));
            homeworkQuestionService.saveTeacherHomeworkQuestion(q);
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

    @Override
    public void createAnswerQuestion(List<TeacherHomeworkQuestion> teacherHomeworkQuestionList, List<StudentAnswer> studentAnswerList) {
        List<StudentAnswerQuestion> list = new ArrayList<>();
        System.out.println(teacherHomeworkQuestionList.size());
        for (StudentAnswer s : studentAnswerList) {
            System.out.println(s.toString());
            for (TeacherHomeworkQuestion ts : teacherHomeworkQuestionList) {
                System.out.println(ts.toString());
                StudentAnswerQuestion studentAnswerQuestion = new StudentAnswerQuestion(ts);
                studentAnswerQuestion.setStudentId(s.getStudentId());
                studentAnswerQuestion.setStudentName(s.getStudentName());
                studentAnswerQuestion.setAnswerId(s.getAnswerId());
                list.add(studentAnswerQuestion);
            }
        }
        answerQuestionRepository.save(list);
    }
}
