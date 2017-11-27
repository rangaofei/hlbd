package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.composbean.BaseBean;
import com.hanlinbode.hlbd.composbean.StudentCostTime;
import com.hanlinbode.hlbd.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/student/{student_id}/getrecentlimit", method = RequestMethod.GET)
    public BaseBean<Map<String, Float>> getRecentRate(@PathVariable("student_id") String studentId,
                                                      @RequestParam("num") int num,
                                                      @RequestParam("index") int index) {
        BaseBean<Map<String, Float>> result = new BaseBean<>();
        result.setCode(200);
        result.setMessage("获取成功");
        result.setBody(answerService.getStudentHistoryRate(studentId, num, index));
        return result;
    }
}
