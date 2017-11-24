package com.hanlinbode.hlbd.facade;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.StudentAnswerQuestion;
import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import com.hanlinbode.hlbd.enums.AnswerState;
import com.hanlinbode.hlbd.service.AnswerQuestionService;
import com.hanlinbode.hlbd.service.AnswerService;
import com.hanlinbode.hlbd.service.HomeWorkService;
import com.hanlinbode.hlbd.service.HomeworkQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CorrectAnswerFacade {
    private Logger logger = LoggerFactory.getLogger(CorrectAnswerFacade.class);
    @Autowired
    private AnswerQuestionService answerQuestionService;
    @Autowired
    private HomeworkQuestionService homeworkQuestionService;
    @Autowired
    private HomeWorkService homeWorkService;
    @Autowired
    private AnswerService answerService;

    /**
     * 更新状态，
     *
     * @param homeworkquestionId
     * @param list
     */
    public void correctAnswer(int homeworkquestionId, List<StudentAnswerQuestion> list) {
        logger.info(list.size() + "个信息");

        //修改student_answer_question表数据
        List<StudentAnswerQuestion> result = new ArrayList<>();
        for (StudentAnswerQuestion question : list) {
            StudentAnswerQuestion s = answerQuestionService.findAnswerQuestionById(question.getId());
            s.setState(AnswerState.CORRECT);
            s.setCorrect(question.getCorrect());
            s.setScore(question.getScore());
            s.setCorrectComment(question.getCorrectComment());
            result.add(s);
        }
        logger.info(result.get(0).toString());
        answerQuestionService.correctAnswerList(result);

        //修改teacher_homework_question表数据
        TeacherHomeworkQuestion teacherHomeworkQuestion = homeworkQuestionService.findTeacherHomeworkQuestionById(homeworkquestionId);
        teacherHomeworkQuestion.setState(AnswerState.CORRECT);
        teacherHomeworkQuestion.setCorrectRate(answerQuestionService.calculateHomeworkQuestionCorrectRate(teacherHomeworkQuestion.getId()));
        logger.info(teacherHomeworkQuestion.toString());
        homeworkQuestionService.saveTeacherHomeworkQuestion(teacherHomeworkQuestion);

        //修改teacher_homework表数据
        TeacherHomework teacherHomework = homeWorkService.findHomeWorkByHomeWorkId(teacherHomeworkQuestion.getTeacherHomeworkId());
        logger.info(teacherHomework.toString());
        teacherHomework.setState(homeworkQuestionService.homeworkState(teacherHomework.getHomeworkId()));
        teacherHomework.setCorrectRate(homeworkQuestionService.calculateCorrectRate(teacherHomework.getHomeworkId()));
        homeWorkService.saveTeacherHomework(teacherHomework);

        //修改student_answer表数据
        StudentAnswer studentAnswer = answerService.findAnswerById(list.get(0).getAnswerId());
        studentAnswer.setCorrectRate(answerQuestionService.calculateStudentAnswerCorrectRate(studentAnswer.getAnswerId()));
        studentAnswer.setState(AnswerState.CORRECT);
        answerService.updateAnswer(studentAnswer);
    }
}
