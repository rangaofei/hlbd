package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.ConstData;
import com.hanlinbode.hlbd.bean.*;
import com.hanlinbode.hlbd.dao.HomeWorkDao;
import com.hanlinbode.hlbd.dao.StudentDao;
import com.hanlinbode.hlbd.dao.TeacherDao;
import com.hanlinbode.hlbd.dao.TeamDao;
import com.hanlinbode.hlbd.responsebean.BaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeWorkController {
    @Autowired
    private HomeWorkDao homeWorkDao;
    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeamDao teamDao;

    @RequestMapping(path = "/teacher/{teacher_id}/createhomework", method = RequestMethod.POST)
    public BaseBean<HomeWork> createHomeWork(@PathVariable("teacher_id") String id, @RequestBody CreateHomeWork createHomeWork) {
        BaseBean<HomeWork> result = new BaseBean<>();
        HomeWork homeWork = createHomeWork.getHomeWork();
        List<Team> teams = createHomeWork.getTeams();
        HomeWork h = homeWorkDao.createHomeWork(id, homeWork, teams);
        result.setCode(ConstData.POST_SUCCESS);
        result.setBody(h);
        result.setMessage("创建成功");
        return result;
    }


    @RequestMapping(path = "/teacher/{teacher_id}/getallhomeworks", method = RequestMethod.GET)
    public BaseBean<List<HomeWork>> getAllHomeWorks(@PathVariable("teacher_id") String id) {
        BaseBean<List<HomeWork>> result = new BaseBean<>();
        List<HomeWork> homeWorks = homeWorkDao.findHomeWorkByTeacherId(id);
        if (homeWorks.size() < 1) {
            result.setMessage("没有作业");
            result.setCode(ConstData.NO_RESULT);
        } else {
            result.setMessage("success");
            result.setCode(ConstData.GET_SUCCESS);
        }
        result.setBody(homeWorks);

        return result;
    }

    @RequestMapping(path = "/student/{student_id}/{class_id}/gethomeworks", method = RequestMethod.GET)
    public BaseBean<List<HomeWork>> getStudentAllHomeworks(@PathVariable("student_id") String studentid,
                                                           @PathVariable("class_id") String classid) {
        BaseBean<List<HomeWork>> result = new BaseBean<>();
        Student student = studentDao.findStudentByStudentId(studentid);
        Team team = teamDao.findTeamByTeamId(classid);
        List<HomeWork> homeWork = new ArrayList<>();
        homeWork.addAll(team.getHomeWorkList());
        result.setBody(homeWork);
        if (homeWork.size() < 1) {
            result.setMessage("无作业");
            result.setCode(ConstData.NO_RESULT);
        } else {
            result.setMessage("success");
            result.setCode(ConstData.GET_SUCCESS);
        }
        return result;
    }
}
