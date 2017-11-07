package com.hanlinbode.hlbd.facade;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.StudentAnswerQuestion;
import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import com.hanlinbode.hlbd.composbean.StudentAnswerAndList;
import com.hanlinbode.hlbd.enums.AnswerState;
import com.hanlinbode.hlbd.service.AnswerQuestionService;
import com.hanlinbode.hlbd.service.AnswerService;
import com.hanlinbode.hlbd.service.HomeWorkService;
import com.hanlinbode.hlbd.service.HomeworkQuestionService;
import com.hanlinbode.hlbd.util.QuestionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommitAnswerFacade {
    @Autowired
    private HomeWorkService homeWorkService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private AnswerQuestionService answerQuestionService;

    @Autowired
    private HomeworkQuestionService homeworkQuestionService;

    public void commitAnswer(String answerId, StudentAnswerAndList list) {
        StudentAnswer studentAnswer = answerService.findAnswerById(answerId);
        //提交学生的答案，更新表`student_answer_question`
        answerQuestionService.commitAnswerQuestion(list.getAnswerList(), studentAnswer);
        //更新表`student_answer`,-设置是否需要批改，-计算正确率
        studentAnswer.setCommitedStudentCount(studentAnswer.getCommitedStudentCount() + 1);
        studentAnswer.setFinishTime(list.getAnswer().getFinishTime());
        studentAnswer.setCostTime(list.getAnswer().getCostTime());
        studentAnswer.setState(answerQuestionService.studentAnswerState(answerId));
        studentAnswer.setCorrectRate(answerQuestionService.calculateStudentAnswerCorrectRate(answerId));
        answerService.updateAnswer(studentAnswer);
        //更新表`teacher_homework_question`，-计算正确率，-设置状态
        for (StudentAnswerQuestion question : list.getAnswerList()) {
            StudentAnswerQuestion targets = answerQuestionService.findAnswerQuestionById(question.getId());
            TeacherHomeworkQuestion teacherHomeworkQuestion =
                    homeworkQuestionService.findTeacherHomeworkQuestionById(targets.getTeacherHomeworkQuestionId());
            teacherHomeworkQuestion.setState(answerQuestionService.teacherHomeworkState(targets.getTeacherHomeworkQuestionId()));
            teacherHomeworkQuestion.setCorrectRate(answerQuestionService.calculateHomeworkQuestionCorrectRate(targets.getTeacherHomeworkQuestionId()));
            homeworkQuestionService.saveTeacherHomeworkQuestion(teacherHomeworkQuestion);
        }
        //更新表`teacher_homework`,-更新提交人数，-计算正确率，-设置状态
        homeWorkService.updateCommitCount(studentAnswer.getHomeworkId());
        TeacherHomework teacherHomework = homeWorkService.findHomeWorkByHomeWorkId(studentAnswer.getHomeworkId());
        AnswerState result = homeworkQuestionService.homeworkState(studentAnswer.getHomeworkId());
        teacherHomework.setState(result);
        teacherHomework.setCorrectRate(homeworkQuestionService.calculateCorrectRate(teacherHomework.getHomeworkId()));
        homeWorkService.saveTeacherHomework(teacherHomework);
    }

}
