package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.StudentAnswerQuestion;
import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import com.hanlinbode.hlbd.dao.AnswerQuestionRepository;
import com.hanlinbode.hlbd.dao.QuestionRepository;
import com.hanlinbode.hlbd.enums.AnswerState;
import com.hanlinbode.hlbd.util.QuestionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
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
//        for (StudentAnswerQuestion list : questionList) {
//            list.setStudentId(studentAnswer.getStudentId());
//            list.setStudentName(studentAnswer.getStudentName());
//            list.setAnswerId(studentAnswer.getAnswerId());
//            list.setTeacherHomeworkQuestionId(answerQuestionRepository
//                    .findStudentAnswerQuestionById(list.getId()).getTeacherHomeworkQuestionId());
//            if (QuestionUtil.isForCorrect(list.getQuestiontypeId())) {
//                if (list.getAnswer().equals(questionRepository.findQuestionById(list.getQuestionId()).getAnswer())) {
//                    list.setScore(100);
//                } else {
//                    list.setScore(0);
//                }
//                list.setState(AnswerState.COMMIT);
//
//            } else {
//                list.setState(AnswerState.WAIT_CORRECT);
//                TeacherHomeworkQuestion question = homeworkQuestionService
//                        .findTeacherHomeworkQuestionById(list.getTeacherHomeworkQuestionId());
//                question.setState(AnswerState.WAIT_CORRECT);
//                homeworkQuestionService.saveTeacherHomeworkQuestion(question);
//            }
//        }
//        answerQuestionRepository.save(questionList);
//        answerService.calucateCorrectRate(studentAnswer.getAnswerId());
//        for (StudentAnswerQuestion s : questionList) {
//            TeacherHomeworkQuestion teacherHomeworkQuestion = homeworkQuestionService
//                    .findTeacherHomeworkQuestionById(s.getTeacherHomeworkQuestionId());
//            teacherHomeworkQuestion.setAwaitCorrect(waitCorrect(s.getTeacherHomeworkQuestionId()));
//        }
//        TeacherHomework teacherHomework = homeWorkService.findHomeWorkByHomeWorkId(studentAnswer.getHomeworkId());
//        teacherHomework.setAwaitCorrect(homeworkQuestionService.waitCorrect(studentAnswer.getHomeworkId()));
//        teacherHomework.setCorrectRate(homeworkQuestionService.calculateCorrectRate(studentAnswer.getHomeworkId()));
//        homeWorkService.updateTeacherHomeWork(teacherHomework);
    }

    @Override
    public void commitAnswerQuestion(List<StudentAnswerQuestion> questionList, StudentAnswer studentAnswer) {
        for (StudentAnswerQuestion answerQuestion : questionList) {
            int id = answerQuestionRepository.findStudentAnswerQuestionById(answerQuestion.getId()).getTeacherHomeworkQuestionId();
            answerQuestion.setStudentId(studentAnswer.getStudentId());
            answerQuestion.setStudentName(studentAnswer.getStudentName());
            answerQuestion.setAnswerId(studentAnswer.getAnswerId());
            answerQuestion.setTeacherHomeworkQuestionId(id);
            if (QuestionUtil.isForCorrect(answerQuestion.getQuestiontypeId())) {
                if (answerQuestion.getAnswer() != null &&
                        answerQuestion.getAnswer().equals(questionRepository.findQuestionById(answerQuestion.getQuestionId()).getAnswer().replace(" ", ""))) {
                    answerQuestion.setScore(100);
                } else {
                    answerQuestion.setScore(0);
                }
                answerQuestion.setState(AnswerState.COMMIT);
            } else {
                if (answerQuestion.getAnswer() == null || answerQuestion.getAnswer().equals("")) {
                    answerQuestion.setState(AnswerState.CORRECT);
                    answerQuestion.setScore(0);
                } else {
                    answerQuestion.setState(AnswerState.WAIT_CORRECT);
                }
            }
        }
        answerQuestionRepository.save(questionList);
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
    public StudentAnswerQuestion findAnswerQuestionById(int id) {
        return answerQuestionRepository.findStudentAnswerQuestionById(id);
    }

    @Override
    public float calculateStudentAnswerCorrectRate(String answerId) {
        List<StudentAnswerQuestion> list = answerQuestionRepository.findStudentAnswerQuestionsByAnswerId(answerId);
        float sum = 0F;
        for (StudentAnswerQuestion question : list) {
            if (question.getState() == AnswerState.COMMIT || question.getState() == AnswerState.CORRECT) {
                sum += question.getScore();
            }
//            TeacherHomeworkQuestion q = homeworkQuestionService.findTeacherHomeworkQuestionById(question.getTeacherHomeworkQuestionId());
//            q.setCorrectRate((int) calculateHomeworkQuestionCorrectRate(question.getTeacherHomeworkQuestionId()));
//            homeworkQuestionService.saveTeacherHomeworkQuestion(q);
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
    public AnswerState studentAnswerState(String answerId) {
        List<StudentAnswerQuestion> list = answerQuestionRepository.findStudentAnswerQuestionsByAnswerId(answerId);
        for (StudentAnswerQuestion s : list) {
            if (s.getState() == AnswerState.NOT_COMMIT) {
                return AnswerState.NOT_COMMIT;
            }
            if (s.getState() == AnswerState.WAIT_CORRECT) {
                return AnswerState.WAIT_CORRECT;
            }
        }
        return AnswerState.CORRECT;
    }

    @Override
    public AnswerState teacherHomeworkState(int homeworkquestionId) {
        List<StudentAnswerQuestion> list = answerQuestionRepository
                .findStudentAnswerQuestionsByTeacherHomeworkQuestionId(homeworkquestionId);
        for (StudentAnswerQuestion s : list) {
            if (s.getState() == AnswerState.WAIT_CORRECT) {
                return AnswerState.WAIT_CORRECT;
            }
        }
        return AnswerState.COMMIT;
    }

    @Override
    public void correctAnswerList(List<StudentAnswerQuestion> list) {
        answerQuestionRepository.save(list);
    }

    @Override
    public void correctAnswer(StudentAnswerQuestion question) {
        answerQuestionRepository.save(question);
    }


    @Override
    public void createAnswerQuestion(List<TeacherHomeworkQuestion> teacherHomeworkQuestionList, List<StudentAnswer> studentAnswerList) {
        List<StudentAnswerQuestion> list = new ArrayList<>();
        for (StudentAnswer s : studentAnswerList) {
            System.out.println(s.toString());
            for (TeacherHomeworkQuestion ts : teacherHomeworkQuestionList) {
                System.out.println(ts.toString());
                StudentAnswerQuestion studentAnswerQuestion = new StudentAnswerQuestion(ts);
                studentAnswerQuestion.setStudentId(s.getStudentId());
                studentAnswerQuestion.setStudentName(s.getStudentName());
                studentAnswerQuestion.setAnswerId(s.getAnswerId());
                studentAnswerQuestion.setState(AnswerState.NOT_COMMIT);
                list.add(studentAnswerQuestion);
            }
        }
        answerQuestionRepository.save(list);
    }
}
