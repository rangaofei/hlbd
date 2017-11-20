package com.hanlinbode.hlbd.controller;


import com.hanlinbode.hlbd.bean.*;
import com.hanlinbode.hlbd.composbean.BaseBean;
import com.hanlinbode.hlbd.composbean.CreateHomeWork;
import com.hanlinbode.hlbd.composbean.HomeWorkAndList;
import com.hanlinbode.hlbd.composbean.StudentAnswerAndList;
import com.hanlinbode.hlbd.dao.AnswerQuestionRepository;
import com.hanlinbode.hlbd.facade.CommitAnswerFacade;
import com.hanlinbode.hlbd.facade.CorrectAnswerFacade;
import com.hanlinbode.hlbd.facade.CreateHomeworkFacade;
import com.hanlinbode.hlbd.service.*;
import com.hanlinbode.hlbd.util.ConstData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class HomeWorkController {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    private HomeWorkService homeWorkService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeamService teamService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private HomeworkQuestionService homeworkQuestionService;
    @Autowired
    private AnswerQuestionRepository answerQuestionRepository;
    @Autowired
    private AnswerQuestionService answerQuestionService;
    @Autowired
    private CreateHomeworkFacade createHomeworkFacade;
    @Autowired
    private CommitAnswerFacade commitAnswerFacade;
    @Autowired
    private CorrectAnswerFacade correctAnswerFacade;

    /**
     * 创建作业
     * 1.将作业和班级关联，作业和题目关联
     * 2.将作业按班级分发给学生
     * 3.将作业状态标记为不需要批改
     */
    @RequestMapping(path = "/teacher/{teacher_id}/createhomework", method = RequestMethod.POST)
    public BaseBean<TeacherHomework> createHomeWork(@PathVariable("teacher_id") String id, @RequestBody CreateHomeWork createHomeWork) {
        BaseBean<TeacherHomework> result = new BaseBean<>();
        TeacherHomework h = createHomeworkFacade.createHomeWork(id, createHomeWork);
        result.setCode(ConstData.POST_SUCCESS);
        result.setBody(h);
        result.setMessage("创建成功");
        return result;
    }


    @RequestMapping(path = "/teacher/{teacher_id}/getallhomeworks", method = RequestMethod.GET)
    public BaseBean<List<TeacherHomework>> getAllHomeWorks(@PathVariable("teacher_id") String id) {
        BaseBean<List<TeacherHomework>> result = new BaseBean<>();
        List<TeacherHomework> teacherHomeworks = homeWorkService.findHomeWorkByTeacherId(id);
        if (teacherHomeworks.size() < 1) {
            result.setMessage("没有作业");
            result.setCode(ConstData.NO_RESULT);
        } else {
            Collections.sort(teacherHomeworks);
            result.setMessage("success");
            result.setCode(ConstData.GET_SUCCESS);
        }
        result.setBody(teacherHomeworks);

        return result;
    }

    /**
     * 获取作业报告
     *
     * @return
     */
    @RequestMapping(path = "/teacher/{homework_id}/gethomeworkreport", method = RequestMethod.GET)
    public BaseBean<HomeWorkAndList> getHomeworkReport(@PathVariable("homework_id") String homeworkId) {
        BaseBean<HomeWorkAndList> result = new BaseBean<>();
        HomeWorkAndList homeWorkAndList = new HomeWorkAndList();
        TeacherHomework teacherHomework = homeWorkService.findHomeWorkByHomeWorkId(homeworkId);
        homeWorkAndList.setTeacherHomework(teacherHomework);
        List<TeacherHomeworkQuestion> lists = homeworkQuestionService.findQuestionsByHomeworkId(homeworkId);
        homeWorkAndList.setTeacherHomeworkQuestion(lists);
        result.setBody(homeWorkAndList);
        logger.info("chenggong");
        return result;
    }

    /**
     * 获取作业问题列表
     */
    @RequestMapping(path = "/teacher/{homework_id}/getquestionlist", method = RequestMethod.GET)
    public BaseBean<List<TeacherHomeworkQuestion>> getHomeworkQuestions(@PathVariable("homework_id") String homeworkId) {
        BaseBean<List<TeacherHomeworkQuestion>> result = new BaseBean<>();
        result.setBody(homeworkQuestionService.findQuestionsByHomeworkId(homeworkId));
        return result;
    }

    /**
     * 获取某一题的作业详情
     *
     * @return
     */
    @RequestMapping(path = "/teacher/{homework_id}/{question_id}/getsquestiondetails", method = RequestMethod.GET)
    public BaseBean<List<StudentAnswerQuestion>> getQuestionDetails(@PathVariable("question_id") int id) {
        BaseBean<List<StudentAnswerQuestion>> result = new BaseBean<>();
        List<StudentAnswerQuestion> list = answerQuestionService.findAnserQuesitonByHomeworkQuestionId(id);
        logger.info(list.get(0).toString());
        result.setBody(list);
        return result;
    }

    /**
     * 批改作业
     *
     * @return
     */
    @RequestMapping(path = "/teacher/{homework_id}/{question_id}/correctanswer", method = RequestMethod.GET)
    public BaseBean<List<StudentAnswerQuestion>> getHomeworkQuestionList(@PathVariable("homework_id") String homeworkId,
                                                                         @PathVariable("question_id") int questionId) {
        BaseBean<List<StudentAnswerQuestion>> result = new BaseBean<>();
        result.setMessage("成功");
        result.setCode(200);
        result.setBody(answerQuestionService.findAnserQuesitonByHomeworkQuestionId(questionId));
        return result;
    }

    @RequestMapping(path = "/teacher/{homework_id}/{question_id}/correctanswer", method = RequestMethod.POST)
    public BaseBean<String> correctHomeworkList(@PathVariable("homework_id") String homeworkId,
                                                @PathVariable("question_id") int questionId,
                                                @RequestBody List<StudentAnswerQuestion> list) {
        correctAnswerFacade.correctAnswer(questionId, list);
        BaseBean<String> result = new BaseBean<>();
        result.setCode(201);
        result.setMessage("批改成功");
        return result;
    }

    /**
     * 获取某一题的答题情况
     *
     * @return
     */
//    @RequestMapping(path = "/teacher/{homework_id}/{student_id}/getsutdentreport", method = RequestMethod.GET)
//    public BaseBean<String> getHomeworkReport() {
//
//    }
    @RequestMapping(path = "/student/{student_id}/{class_id}/gethomeworks", method = RequestMethod.GET)
    public BaseBean<List<StudentAnswer>> getStudentAllHomeworks(@PathVariable("student_id") String studentid,
                                                                @PathVariable("class_id") String classid) {
        BaseBean<List<StudentAnswer>> result = new BaseBean<>();
        List<StudentAnswer> studentAnswers = answerService
                .findAnswerByTeamAndStudent(classid, studentid);

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
        StudentAnswer studentAnswer = answerService.findAnswerById(answerId);
        List<StudentAnswerQuestion> list = answerQuestionRepository.findStudentAnswerQuestionsByAnswerId(answerId);
        StudentAnswerAndList l = new StudentAnswerAndList(studentAnswer, list);
        result.setBody(l);
        result.setMessage("获取成功");
        result.setCode(ConstData.GET_SUCCESS);
        return result;
    }

    /**
     * 将作业状态标记为假如需要批改已批改
     *
     * @param answerId
     * @return
     */
    @RequestMapping(path = "/student/{answer_id}/commitanswer")
    public BaseBean<StudentAnswerAndList> commitStudentAnswer(@PathVariable("answer_id") String answerId,
                                                              @RequestBody StudentAnswerAndList list) {
        BaseBean<StudentAnswerAndList> result = new BaseBean<>();
        StudentAnswer studentAnswer = answerService.findAnswerById(answerId);
        commitAnswerFacade.commitAnswer(answerId, list);
        StudentAnswerAndList re = new StudentAnswerAndList(studentAnswer, answerQuestionService.findAnswerQuestionByAnswerId(answerId));
        result.setBody(re);
        result.setMessage("获取成功");
        result.setCode(ConstData.GET_SUCCESS);
        return result;
    }
}
