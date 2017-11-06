package com.hanlinbode.hlbd.facade;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.composbean.CreateHomeWork;
import com.hanlinbode.hlbd.exception.ResultNotFoundException;
import com.hanlinbode.hlbd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateHomeworkFacade {
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
        System.out.println(teacherHomework.getHomeworkId());
        List<TeacherHomeworkQuestion> l = homeworkQuestionService.findQuestionsByHomeworkId(teacherHomework.getHomeworkId());
        answerQuestionService.createAnswerQuestion(l, list);
        return teacherHomework;
    }
}
