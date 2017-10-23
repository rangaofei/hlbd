package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.ConstData;
import com.hanlinbode.hlbd.bean.BaseBean;
import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.dao.StudentDao;
import com.hanlinbode.hlbd.dao.TeacherDao;
import com.hanlinbode.hlbd.dao.TeamDao;
import com.hanlinbode.hlbd.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import java.util.List;

@RestController
public class TeamService {
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private StudentDao studentDao;


    @RequestMapping(path = "teacher/{teacher_id}/createclass", method = RequestMethod.POST)
    public Team createTeacherClass(@PathVariable("teacher_id") String teacherId, @RequestBody Team team) {
        Teacher teacher = teamDao.saveTeamByTeacher(teacherId, team);
        System.out.println("teacherId=" + teacher);
        return team;
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
    public BaseBean<Student> joinClass(@PathVariable("student_id") String studentid, @RequestParam("classid") String classid) {
        BaseBean<Student> result = new BaseBean<>();
        result.setMessage("保存成功");
        Student student = studentDao.joinTeams(studentid, classid);
        if (student == null) {
            result.setMessage("您已加入过该班级");
        }

        result.setCode(201);
        result.setBody(student);
        return result;
    }

    @RequestMapping(path = "student/{student_id}/getclasses", method = RequestMethod.GET)
    public BaseBean<Student> getStudentClass(@PathVariable("student_id") String studentId) {
        BaseBean<Student> result = new BaseBean<>();
        Student student = studentDao.findStudentByStudentId(studentId);
        if (student.getTeamList() == null || student.getTeamList().size() < 1) {
            result.setMessage("没有加入班级");
            result.setCode(ConstData.NO_RESULT);
            return result;
        }
        result.setBody(student);
        result.setCode(ConstData.POST_SUCCESS);
        result.setMessage("成功");

        return result;
    }


}
