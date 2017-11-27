package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentMath {
    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "/student/{student_id}/getcosttime", method = RequestMethod.GET)
    public void getSubjectCostTime(@PathVariable("student_id") String studentId) {
        answerService.getStudentCostTime(studentId);
    }
}
