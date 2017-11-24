package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.StudentAnswerQuestion;
import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import com.hanlinbode.hlbd.composbean.BaseBean;
import com.hanlinbode.hlbd.composbean.StudentAndToken;
import com.hanlinbode.hlbd.composbean.TeacherAndToken;
import com.hanlinbode.hlbd.composbean.Token;
import com.hanlinbode.hlbd.enums.AnswerState;
import com.hanlinbode.hlbd.service.HomeworkQuestionService;
import com.hanlinbode.hlbd.service.StudentService;
import com.hanlinbode.hlbd.service.TeacherService;
import com.hanlinbode.hlbd.util.ConstData;
import com.hanlinbode.hlbd.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private HomeworkQuestionService homeworkQuestionService;


    @RequestMapping(value = "/auth/test/{homework_id}", method = RequestMethod.GET)
    public AnswerState get(@PathVariable("homework_id") String id) {
        logger.info(id);
        AnswerState re = homeworkQuestionService.homeworkState(id);
        logger.info(re.name() + "--");
        return re;
    }

    @RequestMapping(value = "/auth/student/register", method = RequestMethod.POST)
    public BaseBean<StudentAndToken> studentRegister(@RequestBody Student input) {
        BaseBean<StudentAndToken> baseBean = new BaseBean<>();
        logger.info(input.toString());
        baseBean.setMessage("用户创建成功");
        baseBean.setBody(studentService.registerStudent(input));
        return baseBean;
    }

    @RequestMapping(value = "/auth/student/login", method = RequestMethod.POST)
    public BaseBean<StudentAndToken> studentLogin(@RequestBody Student student) {
        BaseBean<StudentAndToken> result = new BaseBean<>();
        result.setCode(ConstData.POST_SUCCESS);
        result.setMessage("用户登录成功");
        result.setBody(studentService.loginStudent(student));
        return result;
    }

    @RequestMapping(value = "/auth/teacher/register", method = RequestMethod.POST)
    public BaseBean<TeacherAndToken> TeacherRegister(@RequestBody Teacher input) {
        BaseBean<TeacherAndToken> baseBean = new BaseBean<>();
        logger.info(input.toString());
        TeacherAndToken teacherAndToken = teacherService.registerTeacher(input);
        baseBean.setCode(ConstData.POST_SUCCESS);
        baseBean.setMessage("创建成功");
        baseBean.setBody(teacherAndToken);
        return baseBean;

    }

    @RequestMapping(value = "/auth/teacher/login", method = RequestMethod.POST)
    public BaseBean<TeacherAndToken> teacherLogin(@RequestBody Teacher teacher) {
        logger.info(teacher.toString());
        BaseBean<TeacherAndToken> result = new BaseBean<>();
        result.setCode(ConstData.POST_SUCCESS);
        result.setMessage("登录成功");
        result.setBody(teacherService.loginTeacher(teacher));

        return result;
    }

    @RequestMapping(value = "/auth/student/refreshtoken", method = RequestMethod.POST)
    public BaseBean<Token> refreshStudentToken(@RequestBody Token token) {
        BaseBean<Token> result = new BaseBean<>();
        try {
            Token newToken = Token.generateToken(JWTUtil.parseJWT(token.getRefreshToken()).getSubject());
            result.setMessage("刷新成功");
            result.setCode(ConstData.POST_SUCCESS);
            result.setBody(newToken);
        } catch (Exception e) {
            e.printStackTrace();
            result.setBody(null);
            result.setCode(ConstData.NO_RESULT);
            result.setMessage("token失效");
        }
        return result;
    }

    @RequestMapping(value = "/auth/teacher/refreashtoken", method = RequestMethod.POST)
    public BaseBean<Token> refreshTeacherToken(@RequestBody Token token) {
        return null;
    }
}
