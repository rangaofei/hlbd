package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.composbean.BaseBean;
import com.hanlinbode.hlbd.composbean.TeamAndStudent;
import com.hanlinbode.hlbd.facade.ClassFacade;
import com.hanlinbode.hlbd.service.StudentService;
import com.hanlinbode.hlbd.service.TeamService;
import com.hanlinbode.hlbd.util.ConstData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {
    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
    @Autowired
    private TeamService teamService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassFacade classFacade;


    @RequestMapping(path = "teacher/{teacher_id}/createclass", method = RequestMethod.POST)
    public BaseBean<Team> createTeacherClass(@PathVariable("teacher_id") String teacherId, @RequestBody Team team) {
        logger.info("老师(teacher_id={})创建班级：{}", teacherId, team.toString());
        BaseBean<Team> result = new BaseBean<>();
        Team t = classFacade.createTeam(teacherId, team);
        result.setBody(t);
        logger.info("老师(teacher_id={})创建班级成功：{}", teacherId, result.toString());
        return result;
    }

    @RequestMapping(path = "teacher/{teacher_id}/getclasses", method = RequestMethod.GET)
    public BaseBean<List<Team>> getTeacherClasses(@PathVariable("teacher_id") String id) {
        logger.info("老师(teacher_id={})获取所有班级信息", id);
        BaseBean<List<Team>> listBaseBean = new BaseBean<>();
        List<Team> teamList = classFacade.getTeacherTeams(id);
        listBaseBean.setCode(ConstData.GET_SUCCESS);
        listBaseBean.setBody(teamList);
        listBaseBean.setMessage("获取班级成功");
        logger.info("老师(teacher_id={})获取所有班级信息成功：{}", id, listBaseBean.toString());
        return listBaseBean;
    }

    @RequestMapping(path = "teacher/{team_id}/getteaminfo", method = RequestMethod.GET)
    public BaseBean<Team> getStudent(@PathVariable("team_id") String teamId) {
        logger.info("获取班级(teamd_id={})信息", teamId);
        BaseBean<Team> result = new BaseBean<>();
        Team team = teamService.getTeamInfo(teamId);
        result.setCode(200);
        result.setMessage("获取成功");
        result.setBody(team);
        logger.info("获取班级(team_id={})信息成功：{}", teamId, result.toString());
        return result;
    }

    @RequestMapping(path = "teacher/{team_id}/getteamstudents")
    public BaseBean<List<Student>> getTeamStudent(@PathVariable("team_id") String teamId) {
        logger.info("老师获取班级(id={})里的所有学生", teamId);
        BaseBean<List<Student>> result = new BaseBean<>();
        Team team = teamService.getTeamInfo(teamId);
        result.setCode(200);
        result.setMessage("获取成功");
        result.setBody(team.getStudents());
        logger.info("老师获取班级(id={})里的所有学生成功：{}\n", teamId, result.toString());
        return result;
    }

    @RequestMapping(path = "student/{student_id}/joinclass", method = RequestMethod.POST)
    public BaseBean<Team> joinClass(@PathVariable("student_id") String studentid, @RequestParam("class_id") String classid) {
        logger.info("学生（student_id={}）请求加入班级(team_id={})", studentid, classid);
        BaseBean<Team> result = new BaseBean<>();
        result.setMessage("保存成功");
        Team team = classFacade.JoinTeam(studentid, classid);
        result.setCode(201);
        result.setBody(team);
        logger.info("学生（student_id={}）请求加入班级(team_id={})成功：%s", studentid, classid, result.toString());
        return result;
    }

    @RequestMapping(path = "student/{student_id}/getclasses", method = RequestMethod.GET)
    public BaseBean<List<Team>> getStudentClass(@PathVariable("student_id") String studentId) {
        logger.info("学生(student_id={})获取所有的班级", studentId);
        BaseBean<List<Team>> result = new BaseBean<>();
        Student student = studentService.findStudentByStudentId(studentId);
        if (student.getTeamList() == null || student.getTeamList().size() < 1) {
            result.setMessage("没有加入班级");
            result.setCode(ConstData.NO_RESULT);
            return result;
        }
        result.setBody(student.getTeamList());
        result.setCode(ConstData.POST_SUCCESS);
        result.setMessage("成功");
        logger.info("学生(student_id={})获取所有的班级成功：{}", studentId, result.toString());
        return result;
    }
}
