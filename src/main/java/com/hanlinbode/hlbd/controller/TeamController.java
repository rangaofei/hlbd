package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.composbean.BaseBean;
import com.hanlinbode.hlbd.composbean.TeamAndStudent;
import com.hanlinbode.hlbd.service.StudentService;
import com.hanlinbode.hlbd.service.TeamService;
import com.hanlinbode.hlbd.util.ConstData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private StudentService studentService;


    @RequestMapping(path = "teacher/{teacher_id}/createclass", method = RequestMethod.POST)
    public BaseBean<Team> createTeacherClass(@PathVariable("teacher_id") String teacherId, @RequestBody Team team) {
        BaseBean<Team> result = new BaseBean<>();
        Team t = teamService.saveTeamByTeacher(teacherId, team);
        result.setBody(t);
        return result;
    }

    @RequestMapping(path = "teacher/{teacher_id}/getclasses", method = RequestMethod.GET)
    public BaseBean<List<Team>> getTeacherClasses(@PathVariable("teacher_id") String id) {
        System.out.println(id);
        BaseBean<List<Team>> listBaseBean = new BaseBean<>();
        List<Team> teamList = teamService.findTeamsByTeacherId(id);
        if (teamList.size() < 1) {
            listBaseBean.setCode(ConstData.NO_RESULT);
            listBaseBean.setBody(null);
            listBaseBean.setMessage("没有创建班级");
            return listBaseBean;
        } else {
            listBaseBean.setCode(ConstData.GET_SUCCESS);
            listBaseBean.setBody(teamList);
            listBaseBean.setMessage("success");
            return listBaseBean;
        }

    }

    @RequestMapping(path = "teacher/{team_id}/getstudent", method = RequestMethod.GET)
    public BaseBean<TeamAndStudent> getStudent(@PathVariable("team_id") String teamId) {
        BaseBean<TeamAndStudent> result = new BaseBean<>();
        TeamAndStudent teamAndStudent = new TeamAndStudent();
        Team team = teamService.findTeamByTeamId(teamId);

        List<Student> students = team.getStudents();
        teamAndStudent.setStudentList(students);
        teamAndStudent.setTeam(team);
        result.setCode(200);
        result.setMessage("获取成功");
        result.setBody(teamAndStudent);
        return result;

    }

    @RequestMapping(path = "student/{student_id}/joinclass", method = RequestMethod.POST)
    public BaseBean<Team> joinClass(@PathVariable("student_id") String studentid, @RequestParam("class_id") String classid) {
        BaseBean<Team> result = new BaseBean<>();
        result.setMessage("保存成功");
        Team team = studentService.joinTeam(studentid, classid);
        if (team == null) {
            result.setMessage("您已加入过该班级");
        }
        result.setCode(201);
        result.setBody(team);
        return result;
    }

    @RequestMapping(path = "student/{student_id}/getclasses", method = RequestMethod.GET)
    public BaseBean<List<Team>> getStudentClass(@PathVariable("student_id") String studentId) {
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
        return result;
    }


}
