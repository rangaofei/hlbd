package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.bean.TeacherSubject;
import com.hanlinbode.hlbd.composbean.BaseBean;
import com.hanlinbode.hlbd.composbean.TeacherAndSubject;
import com.hanlinbode.hlbd.service.TeacherService;
import com.hanlinbode.hlbd.service.TeacherSubjectService;
import com.hanlinbode.hlbd.util.ConstData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TeacherAuth {
    private static final Logger logger = LoggerFactory.getLogger(TeacherAuth.class);
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherSubjectService teacherSubjectService;


    @RequestMapping(value = "teacher/{teacher_id}/setsubject", method = RequestMethod.POST)
    public BaseBean<List<TeacherSubject>> setSubject(@PathVariable("teacher_id") String id, @RequestBody List<TeacherSubject> teacherSubjects) {
        logger.info("设置科目（teacher_id=%s）", id);
        List<TeacherSubject> teacherSubject = teacherSubjectService.saveTeacherSubject(id, teacherSubjects);
        BaseBean<List<TeacherSubject>> result = new BaseBean<>();
        result.setMessage("保存成功");
        result.setCode(ConstData.POST_SUCCESS);
        result.setBody(teacherSubject);
        return result;
    }

    @RequestMapping(value = "teacher/{teacher_id}/getsubject", method = RequestMethod.GET)
    public BaseBean<TeacherAndSubject> getSubject(@PathVariable("teacher_id") String id) {
        BaseBean<TeacherAndSubject> result = new BaseBean<>();
        TeacherAndSubject teacherAndSubject = new TeacherAndSubject();
        List<TeacherSubject> teacherSubjectList = teacherSubjectService.findSubjectsByTeacherId(id);
        teacherAndSubject.setTeacher(teacherService.findTeacherByTeacherId(id));
        teacherAndSubject.setTeacherSubjects(teacherSubjectList);
        result.setBody(teacherAndSubject);
        if (teacherSubjectList == null || teacherSubjectList.size() < 1) {
            result.setCode(ConstData.NO_RESULT);
            result.setMessage("没有科目");
        } else {
            result.setCode(ConstData.GET_SUCCESS);
            result.setMessage("获取成功");
        }
        return result;
    }

    @RequestMapping(value = "teacher/{teacher_id}/deletesubjects", method = RequestMethod.DELETE)
    public BaseBean<List<TeacherSubject>> deleteSubject(@PathVariable("teacher_id") String teacherId,
                                                        @RequestBody List<TeacherSubject> teacherSubjects) {
        List<TeacherSubject> teacherSubject = teacherSubjectService.deleteTeacherSubject(teacherId, teacherSubjects);
        BaseBean<List<TeacherSubject>> result = new BaseBean<>();
        result.setMessage("保存成功");
        result.setCode(ConstData.POST_SUCCESS);
        result.setBody(teacherSubject);
        return result;
    }
}
