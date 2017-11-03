package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.ConstData;
import com.hanlinbode.hlbd.bean.*;
import com.hanlinbode.hlbd.composbean.CreateHomeWork;
import com.hanlinbode.hlbd.composbean.HomeWorkAndList;
import com.hanlinbode.hlbd.dao.*;
import com.hanlinbode.hlbd.composbean.BaseBean;
import com.hanlinbode.hlbd.composbean.StudentAnswerAndList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class HomeWorkController {
    private Logger logger = Logger.getLogger(this.getClass().getName());
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
    @Autowired
    private HomeworkQuestionDao homeworkQuestionDao;
    @Autowired
    private AnswerQuestionRepository answerQuestionRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerQuestionDao answerQuestionDao;

    /**
     * 创建作业
     * 1.将作业和班级关联，作业和题目关联
     * 2.将作业按班级分发给学生
     * 3.将作业状态标记为不需要批改
     */
    @RequestMapping(path = "/teacher/{teacher_id}/createhomework", method = RequestMethod.POST)
    public BaseBean<TeacherHomework> createHomeWork(@PathVariable("teacher_id") String id, @RequestBody CreateHomeWork createHomeWork) {
        BaseBean<TeacherHomework> result = new BaseBean<>();
        TeacherHomework h = homeWorkDao.createHomeWork(id, createHomeWork.getTeacherHomework(), createHomeWork.getTeams());
        result.setCode(ConstData.POST_SUCCESS);
        result.setBody(h);
        result.setMessage("创建成功");
        return result;
    }


    @RequestMapping(path = "/teacher/{teacher_id}/getallhomeworks", method = RequestMethod.GET)
    public BaseBean<List<TeacherHomework>> getAllHomeWorks(@PathVariable("teacher_id") String id) {
        BaseBean<List<TeacherHomework>> result = new BaseBean<>();
        List<TeacherHomework> teacherHomeworks = homeWorkDao.findHomeWorkByTeacherId(id);
        if (teacherHomeworks.size() < 1) {
            result.setMessage("没有作业");
            result.setCode(ConstData.NO_RESULT);
        } else {
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
        TeacherHomework teacherHomework = homeWorkDao.findHomeWorkByHomeWorkId(homeworkId);
        homeWorkAndList.setTeacherHomework(teacherHomework);
        List<TeacherHomeworkQuestion> lists = homeworkQuestionDao.findQuestionsByHomeworkId(homeworkId);
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
        result.setBody(homeworkQuestionDao.findQuestionsByHomeworkId(homeworkId));
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
        List<StudentAnswerQuestion> list = answerQuestionDao.findAnserQuesitonByHomeworkQuestionId(id);
        result.setBody(list);
        return result;
    }

    /**
     * 批改作业
     *
     * @return
     */
    @RequestMapping(path = "/teacher/{homework_id}/{question_id}/correctanswer", method = RequestMethod.GET)
    public BaseBean<String> getHomeworkReport() {

        return null;
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
        List<StudentAnswer> studentAnswers = answerDao
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
        StudentAnswer studentAnswer = answerDao.findAnswerById(answerId);
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
        StudentAnswer studentAnswer = answerDao.findAnswerById(answerId);
        homeWorkDao.updateCommitCount(studentAnswer.getHomeworkId());
        studentAnswer.setCommitedStudentCount(studentAnswer.getCommitedStudentCount() + 1);
        studentAnswer.setFinishTime(list.getAnswer().getFinishTime());
        studentAnswer.setCostTime(list.getAnswer().getCostTime());
        answerDao.updateAnswer(studentAnswer);
        answerQuestionDao.commitAnswer(list.getAnswerList(), studentAnswer);
        StudentAnswerAndList re = new StudentAnswerAndList(studentAnswer, answerQuestionDao.findAnswerQuestionByAnswerId(answerId));
        result.setBody(re);
        result.setMessage("获取成功");
        result.setCode(ConstData.GET_SUCCESS);
        return result;
    }
}
