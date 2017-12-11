package com.hanlinbode.hlbd.facade;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.composbean.CreateHomeWork;
import com.hanlinbode.hlbd.exception.ResultNotFoundException;
import com.hanlinbode.hlbd.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateHomeworkFacade {
    private static final Logger logger = LoggerFactory.getLogger(CreateHomeworkFacade.class);
    @Autowired
    private HomeWorkService homeWorkService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private HomeworkQuestionService homeworkQuestionService;
    @Autowired
    private AnswerQuestionService answerQuestionService;

    public TeacherHomework createHomeWork(String teacherId, CreateHomeWork createHomeWork) {
        if (null == teacherService.findTeacherByTeacherId(teacherId)) {
            throw new ResultNotFoundException("老师ID不存在");
        }
        TeacherHomework teacherHomework = homeWorkService.createHomeWork(teacherId,
                createHomeWork.getTeacherHomework(),
                createHomeWork.getTeams(),
                teamService.getTeamsVolume(createHomeWork.getTeams()));
        teamService.saveTeacherHomework(createHomeWork.getTeams(), createHomeWork.getTeacherHomework());

        List<Team> teamList = teamService.findTeamsByTeams(createHomeWork.getTeams());
        List<StudentAnswer> list = answerService.saveAnswerByTeams(createHomeWork.getTeacherHomework(), teamList);
        List<TeacherHomeworkQuestion> l = homeworkQuestionService.setQuestionHomeworkId(
                createHomeWork.getTeacherHomework().getTeacherHomeworkQuestions(), teacherHomework.getHomeworkId());

        answerQuestionService.createAnswerQuestion(l, list);
        return teacherHomework;
    }

    public List<TeacherHomework> deleteHomework(String teacherId, List<String> homeworkIdList) {
        for (String homeworkId : homeworkIdList) {
            homeWorkService.deleteTeacherHomework(homeworkId);//删除老师作业表中的数据
            homeworkQuestionService.deleteHomeworkByHomeworkId(homeworkId);//删除老师作业题目表中的数据
            answerService.deleteAnswerByHomeworkId(homeworkId);//删除学生作业
            for (TeacherHomeworkQuestion s : homeworkQuestionService.findQuestionsByHomeworkId(homeworkId)) {
                answerQuestionService.deleteAnswerQuestionByHomeworkId(s.getQuestionId());//删除学生作业题目表中的数据
            }


        }
        logger.info("老师的id" + teacherId);
        return homeWorkService.findHomeWorkByTeacherId(teacherId);
    }
}
