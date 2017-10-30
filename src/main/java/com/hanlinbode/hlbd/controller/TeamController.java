package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.ConstData;
import com.hanlinbode.hlbd.composbean.BaseBean;
import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.dao.StudentDao;
import com.hanlinbode.hlbd.dao.TeacherDao;
import com.hanlinbode.hlbd.dao.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private StudentDao studentDao;


    @RequestMapping(path = "teacher/{teacher_id}/createclass", method = RequestMethod.POST)
    public BaseBean<Team> createTeacherClass(@PathVariable("teacher_id") String teacherId, @RequestBody Team team) {
        BaseBean<Team> result = new BaseBean<>();
        Team t = teamDao.saveTeamByTeacher(teacherId, team);
        result.setBody(t);
        return result;
    }

    @RequestMapping(path = "teacher/{teacher_id}/getclasses", method = RequestMethod.GET)
    public BaseBean<List<Team>> getTeacherClasses(@PathVariable("teacher_id") String id) {
        System.out.println(id);
        BaseBean<List<Team>> listBaseBean = new BaseBean<>();
        List<Team> teamList = teacherDao.findTeacherByTeacherId(id).getTeams();
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

    @RequestMapping(path = "student/{student_id}/joinclass", method = RequestMethod.POST)
    public BaseBean<Team> joinClass(@PathVariable("student_id") String studentid, @RequestParam("class_id") String classid) {
        BaseBean<Team> result = new BaseBean<>();
        result.setMessage("保存成功");
        Team team = studentDao.joinTeam(studentid, classid);
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
        Student student = studentDao.findStudentByStudentId(studentId);
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
