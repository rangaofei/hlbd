package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.ConstData;
import com.hanlinbode.hlbd.bean.*;
import com.hanlinbode.hlbd.composbean.CreateHomeWork;
import com.hanlinbode.hlbd.dao.*;
import com.hanlinbode.hlbd.composbean.BaseBean;
import com.hanlinbode.hlbd.composbean.StudentAnswerAndList;
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
    @Autowired
    private AnswerDao answerDao;

    @RequestMapping(path = "/teacher/{teacher_id}/createhomework", method = RequestMethod.POST)
    public BaseBean<TeacherHomeWork> createHomeWork(@PathVariable("teacher_id") String id, @RequestBody CreateHomeWork createHomeWork) {
        BaseBean<TeacherHomeWork> result = new BaseBean<>();
        TeacherHomeWork h = homeWorkDao.createHomeWork(id, createHomeWork.getTeacherHomeWork(), createHomeWork.getTeams());
        result.setCode(ConstData.POST_SUCCESS);
        result.setBody(h);
        result.setMessage("创建成功");
        return result;
    }


    @RequestMapping(path = "/teacher/{teacher_id}/getallhomeworks", method = RequestMethod.GET)
    public BaseBean<List<TeacherHomeWork>> getAllHomeWorks(@PathVariable("teacher_id") String id) {
        BaseBean<List<TeacherHomeWork>> result = new BaseBean<>();
        List<TeacherHomeWork> teacherHomeWorks = homeWorkDao.findHomeWorkByTeacherId(id);
        if (teacherHomeWorks.size() < 1) {
            result.setMessage("没有作业");
            result.setCode(ConstData.NO_RESULT);
        } else {
            result.setMessage("success");
            result.setCode(ConstData.GET_SUCCESS);
        }
        result.setBody(teacherHomeWorks);

        return result;
    }

    @RequestMapping(path = "/student/{student_id}/{class_id}/gethomeworks", method = RequestMethod.GET)
    public BaseBean<List<StudentAnswer>> getStudentAllHomeworks(@PathVariable("student_id") String studentid,
                                                                @PathVariable("class_id") String classid) {
        BaseBean<List<StudentAnswer>> result = new BaseBean<>();
        Student student = studentDao.findStudentByStudentId(studentid);
        Team team = teamDao.findTeamByTeamId(classid);
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        for (TeacherHomeWork teacherHomeWork : team.getTeacherHomeWorkList()) {
            List<StudentAnswer> studentAnswer = answerDao
                    .findAnswerByTeacherHomeWorkAndStudent(teacherHomeWork, student);
            studentAnswers.addAll(studentAnswer);
        }
        result.setBody(studentAnswers);
        if (studentAnswers.size() < 1) {
            result.setMessage("无作业");
            result.setCode(ConstData.NO_RESULT);
        } else {
            result.setMessage("success");
            result.setCode(ConstData.GET_SUCCESS);
        }
        return result;
    }

    @RequestMapping(path = "/student/{answer_id}/getanswer")
    public BaseBean<StudentAnswerAndList> getStudentAnswer(@PathVariable("answer_id") String answerId) {
        BaseBean<StudentAnswerAndList> result = new BaseBean<>();
        StudentAnswer studentAnswer = answerDao.findAnswerById(answerId);
        StudentAnswerAndList l = new StudentAnswerAndList(studentAnswer, studentAnswer.getStudentAnswerLists());
        result.setBody(l);
        result.setMessage("获取成功");
        result.setCode(ConstData.GET_SUCCESS);
        return result;
    }

    @RequestMapping(path = "/student/{answer_id}/commitanswer")
    public BaseBean<StudentAnswerAndList> commitStudentAnswer(@PathVariable("answer_id") String answerId,
                                                              @RequestBody List<StudentAnswerList> studentAnswerList) {
        BaseBean<StudentAnswerAndList> result = new BaseBean<>();
        StudentAnswer studentAnswer = answerDao.findAnswerById(answerId);
        TeacherHomeWork teacherHomeWork = homeWorkDao.findHomeWorkByHomeWorkId(studentAnswer.getFk_homework_id());
        teacherHomeWork.setCommitedCount(teacherHomeWork.getCommitedCount() + 1);
        homeWorkDao.updateTeacherHomeWork(teacherHomeWork);
        studentAnswer.setStudentAnswerLists(studentAnswerList);
        answerDao.updateAnswer(studentAnswer);
        StudentAnswerAndList re = new StudentAnswerAndList(studentAnswer, studentAnswerList);
        result.setBody(re);
        result.setMessage("获取成功");
        result.setCode(ConstData.GET_SUCCESS);
        return result;
    }
}
