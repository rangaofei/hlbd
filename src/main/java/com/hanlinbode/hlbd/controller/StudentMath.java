package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.composbean.BaseBean;
import com.hanlinbode.hlbd.composbean.StudentRate;
import com.hanlinbode.hlbd.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StudentMath {
    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "/student/{student_id}/getcosttime", method = RequestMethod.GET)
    public BaseBean<Map<String, Integer>> getSubjectCostTime(@PathVariable("student_id") String studentId) {
        BaseBean<Map<String, Integer>> result = new BaseBean<>();
        result.setCode(200);
        result.setMessage("获取成功");
        result.setBody(answerService.getStudentCostTime(studentId));
        return result;
    }

    @RequestMapping(value = "/student/{student_id}/getrecentrate", method = RequestMethod.GET)
    public BaseBean<List<Map<String, StudentRate>>> getRecentRate(@PathVariable("student_id") String studentId) {
        BaseBean<List<Map<String, StudentRate>>> result = new BaseBean<>();
        result.setCode(200);
        result.setMessage("获取成功");
        result.setBody(answerService.getStudentHistoryRate(studentId, 10, 1));
        return result;
    }
}
