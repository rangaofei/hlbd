package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.ConstData;
import com.hanlinbode.hlbd.bean.BaseBean;
import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.bean.TeacherSubject;
import com.hanlinbode.hlbd.dao.TeacherDao;
import com.hanlinbode.hlbd.dao.TeacherSubjectDao;
import com.hanlinbode.hlbd.util.UUIDUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
public class TeacherAuth {
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private TeacherSubjectDao teacherSubjectDao;

    @RequestMapping(value = "/hello")
    public String Test() {
        return "hello";
    }



    @RequestMapping(value = "teacher/{teacher_id}/setsubject", method = RequestMethod.POST)
    public BaseBean<String> setSubject(@PathVariable("teacher_id") String id, @RequestBody List<TeacherSubject> teacherSubjects) {
        System.out.println(teacherSubjects.get(0).toString());
        teacherSubjectDao.saveTeacherSubject(id, teacherSubjects);
        BaseBean<String> result = new BaseBean<>();
        result.setMessage("保存成功");
        result.setCode(ConstData.POST_SUCCESS);
        result.setBody("success");
        return result;
    }

    @RequestMapping(value = "teacher/{teacher_id}/getsubject", method = RequestMethod.GET)
    public BaseBean<List<TeacherSubject>> getSubject(@PathVariable("teacher_id") String id) {
        System.out.println("id=" + id);
        BaseBean<List<TeacherSubject>> result = new BaseBean<>();
        List<TeacherSubject> teacherSubjectList = teacherSubjectDao.findSubjectsByTeacherId(id);
        if (teacherSubjectList == null || teacherSubjectList.size() < 1) {
            result.setCode(ConstData.NO_RESULT);
            result.setBody(null);
            result.setMessage("没有科目");
        } else {
            result.setCode(ConstData.GET_SUCCESS);
            result.setMessage("获取成功");
            result.setBody(teacherSubjectList);
        }

        return result;

    }
}
