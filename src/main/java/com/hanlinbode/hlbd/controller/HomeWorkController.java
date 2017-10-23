package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.ConstData;
import com.hanlinbode.hlbd.bean.BaseBean;
import com.hanlinbode.hlbd.bean.HomeWork;
import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.dao.HomeWorkDao;
import com.hanlinbode.hlbd.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeWorkController {
    @Autowired
    private HomeWorkDao homeWorkDao;
    @Autowired
    private TeacherDao teacherDao;

    @RequestMapping(path = "/teacher/{teacher_id}/createhomework", method = RequestMethod.POST)
    public BaseBean<HomeWork> createHomeWork(@PathVariable("teacher_id") String id, @RequestBody HomeWork homeWork) {
        BaseBean<HomeWork> result = new BaseBean<>();
        HomeWork h = homeWorkDao.createHomeWork(id, homeWork);
        result.setCode(ConstData.POST_SUCCESS);
        result.setBody(h);
        result.setMessage("创建成功");
        return result;
    }

    @RequestMapping(path = "/teacher/{teacher_id}/getallhomeworks", method = RequestMethod.GET)
    public BaseBean<List<HomeWork>> getAllHomeWorks(@PathVariable("teacher_id") String id) {
        BaseBean<List<HomeWork>> result = new BaseBean<>();
        List<HomeWork> homeWorks = homeWorkDao.findHomeWorkByTeacherId(id);
        result.setBody(homeWorks);
        result.setMessage("success");
        result.setCode(ConstData.GET_SUCCESS);
        return result;
    }
}
