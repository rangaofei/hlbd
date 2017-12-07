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


    @RequestMapping(value = "/auth/student/register", method = RequestMethod.POST)
    public BaseBean<StudentAndToken> studentRegister(@RequestBody Student input) {
        BaseBean<StudentAndToken> result = new BaseBean<>();
        logger.info("接收到学生注册信息：" + input.toString());
        result.setMessage("用户创建成功");
        result.setBody(studentService.registerStudent(input));
        logger.info("返回学生注册信息：" + result.toString());
        return result;
    }

    @RequestMapping(value = "/auth/student/login", method = RequestMethod.POST)
    public BaseBean<StudentAndToken> studentLogin(@RequestBody Student student) {
        logger.info("接收到学生登录信息：" + student.toString());
        BaseBean<StudentAndToken> result = new BaseBean<>();
        result.setCode(ConstData.POST_SUCCESS);
        result.setMessage("用户登录成功");
        result.setBody(studentService.loginStudent(student));
        logger.info("返回学生登录信息：" + result.toString());
        return result;
    }

    /**
     * 老师注册
     * @param input
     * @return
     */
    @RequestMapping(value = "/auth/teacher/register", method = RequestMethod.POST)
    public BaseBean<TeacherAndToken> TeacherRegister(@RequestBody Teacher input) {
        BaseBean<TeacherAndToken> result = new BaseBean<>();
        logger.info("接收到老师注册信息：" + input.toString());
        TeacherAndToken teacherAndToken = teacherService.registerTeacher(input);
        result.setCode(ConstData.POST_SUCCESS);
        result.setMessage("创建成功");
        result.setBody(teacherAndToken);
        logger.info("返回老师注册信息：" + result.toString());
        return result;

    }

    @RequestMapping(value = "/auth/teacher/login", method = RequestMethod.POST)
    public BaseBean<TeacherAndToken> teacherLogin(@RequestBody Teacher teacher) {
        logger.info("接收到老师登录信息：" + teacher.toString());
        BaseBean<TeacherAndToken> result = new BaseBean<>();
        result.setCode(ConstData.POST_SUCCESS);
        result.setMessage("登录成功");
        result.setBody(teacherService.loginTeacher(teacher));
        logger.info("返回老师登录信息：" + result.toString());
        return result;
    }

    @RequestMapping(value = "/auth/student/refreshtoken", method = RequestMethod.POST)
    public BaseBean<Token> refreshStudentToken(@RequestBody Token refresh_token) {
        logger.info("刷新学生的token：" + refresh_token.toString());
        BaseBean<Token> result = new BaseBean<>();
        Token newToken = Token.generateToken(JWTUtil.parseJWT(refresh_token.getRefreshToken()).getSubject());
        result.setMessage("刷新成功");
        result.setCode(ConstData.POST_SUCCESS);
        result.setBody(newToken);
        logger.info("刷新学生的token成功："+result.toString());
        return result;
    }

    @RequestMapping(value = "/auth/teacher/refreshtoken", method = RequestMethod.POST)
    public BaseBean<Token> refreshTeacherToken(@RequestBody Token refresh_token) {
        BaseBean<Token> result = new BaseBean<>();
        logger.info("刷新老师的token：" + refresh_token.toString());
        Token newToken = Token.generateToken(JWTUtil.parseJWT(refresh_token.getRefreshToken()).getSubject());
        result.setMessage("刷新成功");
        result.setCode(ConstData.POST_SUCCESS);
        result.setBody(newToken);
        logger.info("刷新老师的token成功："+result.toString());
        return result;
    }
}
