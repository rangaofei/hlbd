package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.ConstData;
import com.hanlinbode.hlbd.bean.BaseBean;
import com.hanlinbode.hlbd.bean.TeacherClass;
import com.hanlinbode.hlbd.dao.TeacherClassDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClassService {
    @Autowired
    private TeacherClassDao teacherClassDao;

    @RequestMapping(path = "/{teacherid}/createclass", method = RequestMethod.POST)
    public TeacherClass createTeacherClass(@PathVariable("teacherid") int teacherId, @RequestBody TeacherClass teacherClass) {
        System.out.println(teacherClass.toString());
        teacherClassDao.createClass(teacherClass);
        return teacherClass;
    }

    @RequestMapping(path = "/{teacherid}/getclasses", method = RequestMethod.GET)
    public BaseBean<List<TeacherClass>> getTeacherClasses(@PathVariable("teacherid") int teahcerId) {
        System.out.println(teahcerId);
        BaseBean<List<TeacherClass>> teacherClasses = new BaseBean<>();
        List<TeacherClass> teacherClassList = teacherClassDao.getTeacherclasses(teahcerId);
        if (teacherClassList.size() < 1) {
            teacherClasses.setCode(ConstData.NO_RESULT);
            teacherClasses.setBody(null);
            teacherClasses.setMessage("没有创建班级");
            return teacherClasses;
        }else {
            teacherClasses.setCode(ConstData.GET_SUCCESS);
            teacherClasses.setBody(teacherClassList);
            teacherClasses.setMessage("success");
            return teacherClasses;
        }

    }
}
