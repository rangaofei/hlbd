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
import com.hanlinbode.hlbd.facade.StudentGetHomework;
import com.hanlinbode.hlbd.service.*;
import com.hanlinbode.hlbd.util.ConstData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;

import java.util.Collections;
import java.util.List;

@RestController
public class HomeWorkController {
    private Logger logger = LoggerFactory.getLogger(getClass());
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
    @Autowired
    private StudentGetHomework studentGetHomework;

    /**
     * 创建作业
     * 1.将作业和班级关联，作业和题目关联
     * 2.将作业按班级分发给学生
     * 3.将作业状态标记为不需要批改
     */
    @RequestMapping(path = "/teacher/{teacher_id}/createhomework", method = RequestMethod.POST)
    public BaseBean<TeacherHomework> createHomeWork(@PathVariable("teacher_id") String id, @RequestBody CreateHomeWork createHomeWork) {
        logger.info("老师创建作业：" + createHomeWork.toString());
        BaseBean<TeacherHomework> result = new BaseBean<>();
        TeacherHomework h = createHomeworkFacade.createHomeWork(id, createHomeWork);
        result.setCode(ConstData.POST_SUCCESS);
        result.setBody(h);
        result.setMessage("创建成功");
        logger.info("老师创建作业成功：" + result.toString());
        return result;
    }


    @RequestMapping(path = "/teacher/{teacher_id}/getallhomeworks", method = RequestMethod.GET)
    public BaseBean<List<TeacherHomework>> getAllHomeWorks(@PathVariable("teacher_id") String id) {
        logger.info("老师获取所有作业：" + id);
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
        logger.info("老师获取所有作业结果：" + result.toString());
        return result;
    }

    @RequestMapping(path = "/teacher/{teacher_id}/deletehomework", method = RequestMethod.DELETE)
    public BaseBean<List<TeacherHomework>> deleteHomework(@PathVariable("teacher_id") String teacherId,
                                                          @RequestBody List<String> homeworkIdList) {
        BaseBean<List<TeacherHomework>> result = new BaseBean<>();
        List<TeacherHomework> list = createHomeworkFacade.deleteHomework(teacherId, homeworkIdList);
        Collections.sort(list);
        result.setBody(list);
        return result;
    }

    /**
     * 获取作业报告
     *
     * @return
     */
    @RequestMapping(path = "/teacher/{homework_id}/gethomeworkreport", method = RequestMethod.GET)
    public BaseBean<HomeWorkAndList> getHomeworkReport(@PathVariable("homework_id") String homeworkId) {
        logger.info("获取 {} 的作业报告", homeworkId);
        BaseBean<HomeWorkAndList> result = new BaseBean<>();
        HomeWorkAndList homeWorkAndList = new HomeWorkAndList();
        TeacherHomework teacherHomework = homeWorkService.findHomeWorkByHomeWorkId(homeworkId);
        homeWorkAndList.setTeacherHomework(teacherHomework);
        List<TeacherHomeworkQuestion> lists = homeworkQuestionService.findQuestionsByHomeworkId(homeworkId);
        homeWorkAndList.setTeacherHomeworkQuestion(lists);
        result.setBody(homeWorkAndList);
        logger.info("获取 {} 的作业报告成功：%s", homeworkId, result.toString());
        return result;
    }

    /**
     * 获取作业问题列表
     */
    @RequestMapping(path = "/teacher/{homework_id}/getquestionlist", method = RequestMethod.GET)
    public BaseBean<List<TeacherHomeworkQuestion>> getHomeworkQuestions(@PathVariable("homework_id") String homeworkId) {
        logger.info("获取 {} 的题目列表", homeworkId);
        BaseBean<List<TeacherHomeworkQuestion>> result = new BaseBean<>();
        result.setBody(homeworkQuestionService.findQuestionsByHomeworkId(homeworkId));
        result.setMessage("获取成功");
        result.setCode(200);
        logger.info("获取 {} 的题目列表成功：%s", homeworkId, result.toString());
        return result;
    }

    /**
     * 获取某一题的作业详情
     *
     * @return
     */
    @RequestMapping(path = "/teacher/{homework_id}/{question_id}/getsquestiondetails", method = RequestMethod.GET)
    public BaseBean<List<StudentAnswerQuestion>> getQuestionDetails(@PathVariable("question_id") int id) {
        logger.info("获取学生作业question_id为{} 详情", id);
        BaseBean<List<StudentAnswerQuestion>> result = new BaseBean<>();
        List<StudentAnswerQuestion> list = answerQuestionService.findAnserQuesitonByHomeworkQuestionId(id);
        result.setBody(list);
        logger.info("获取学生作业question_id为{} 详情:{}", id, result.toString());
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
        logger.info("获取question_id为{} 作业的学生答题情况", questionId);
        BaseBean<List<StudentAnswerQuestion>> result = new BaseBean<>();
        result.setMessage("成功");
        result.setCode(200);
        result.setBody(answerQuestionService.findAnserQuesitonByHomeworkQuestionId(questionId));
        logger.info("获取question_id为{} 作业的学生答题情况:{}", questionId, result.toString());
        return result;
    }

    @RequestMapping(path = "/teacher/{homework_id}/{question_id}/correctanswer", method = RequestMethod.POST)
    public BaseBean<String> correctHomeworkList(@PathVariable("homework_id") String homeworkId,
                                                @PathVariable("question_id") int questionId,
                                                @RequestBody List<StudentAnswerQuestion> list) {
        logger.info("开始批改作业");
        correctAnswerFacade.correctAnswer(questionId, list);
        BaseBean<String> result = new BaseBean<>();
        result.setCode(201);
        result.setMessage("批改成功");
        logger.info("批改作业完成" + result.toString());
        return result;
    }

    @RequestMapping(path = "/student/{student_id}/{class_id}/gethomeworks", method = RequestMethod.GET)
    public BaseBean<List<StudentAnswer>> getStudentAllHomeworks(@PathVariable("student_id") String studentid,
                                                                @PathVariable("class_id") String classid) {
        logger.info("学生（id= %s ）获取班级（id= {} ）所有的作业", studentid, classid);
        BaseBean<List<StudentAnswer>> result = new BaseBean<>();
//        List<StudentAnswer> studentAnswers = answerService
//                .findAnswerByTeamAndStudent(classid, studentid);
        List<StudentAnswer> studentAnswers = studentGetHomework.getStudentAnswerByStudent(classid, studentid);
        result.setBody(studentAnswers);
        if (studentAnswers.size() < 1) {
            result.setMessage("无作业");
            result.setCode(ConstData.NO_RESULT);
        } else {
            result.setMessage("success");
            result.setCode(ConstData.GET_SUCCESS);
        }
        logger.info("学生（id= {} ）获取班级（id= {} ）所有的作业结果：{}", studentid, classid, result.toString());
        return result;
    }

    @RequestMapping(path = "/student/{answer_id}/getanswer")
    public BaseBean<StudentAnswerAndList> getStudentAnswer(@PathVariable("answer_id") String answerId) {
        logger.info("获取答案(id=%s)", answerId);
        BaseBean<StudentAnswerAndList> result = new BaseBean<>();
        StudentAnswer studentAnswer = answerService.findAnswerById(answerId);
        List<StudentAnswerQuestion> list = answerQuestionRepository.findStudentAnswerQuestionsByAnswerId(answerId);
        StudentAnswerAndList l = new StudentAnswerAndList(studentAnswer, list);
        result.setBody(l);
        result.setMessage("获取成功");
        result.setCode(ConstData.GET_SUCCESS);
        logger.info("获取答案(id={})结果为{}", answerId, result.toString());
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
        logger.info("提交答案(answer_id={})", answerId);
        BaseBean<StudentAnswerAndList> result = new BaseBean<>();
        StudentAnswer studentAnswer = answerService.findAnswerById(answerId);
        commitAnswerFacade.commitAnswer(answerId, list);
        StudentAnswerAndList re = new StudentAnswerAndList(studentAnswer, answerQuestionService.findAnswerQuestionByAnswerId(answerId));
        result.setBody(re);
        result.setMessage("获取成功");
        result.setCode(ConstData.GET_SUCCESS);
        logger.info("提交答案(answer_id={})成功", answerId);
        return result;
    }
}
