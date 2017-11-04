package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.util.ConstData;
import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.dao.StudentDao;
import com.hanlinbode.hlbd.dao.TeacherDao;
import com.hanlinbode.hlbd.composbean.BaseBean;
import com.hanlinbode.hlbd.composbean.StudentAndToken;
import com.hanlinbode.hlbd.composbean.TeacherAndToken;
import com.hanlinbode.hlbd.composbean.Token;
import com.hanlinbode.hlbd.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;


    @RequestMapping(value = "/auth/student/register", method = RequestMethod.POST)
    public BaseBean<StudentAndToken> response(@RequestBody Student input) {
        BaseBean<StudentAndToken> baseBean = new BaseBean<>();
        logger.info(input.toString());
        if (null == studentDao.findStudentByPhone(input.getPhone())) {
            baseBean.setCode(ConstData.POST_SUCCESS);
            baseBean.setMessage("创建成功");
            baseBean.setBody(studentDao.registerStudent(input));
            return baseBean;
        } else {
            baseBean.setCode(ConstData.NO_RESULT);
            baseBean.setMessage("该手机号已经注册");
            baseBean.setBody(null);
            return baseBean;
        }
    }

    @RequestMapping(value = "/auth/student/login", method = RequestMethod.POST)
    public BaseBean<StudentAndToken> logResponse(@RequestBody Student student) {
        BaseBean<StudentAndToken> result = new BaseBean<>();
        Student s = studentDao.findStudentByPhone(student.getPhone());
        if (null == s) {
            result.setCode(ConstData.NO_RESULT);
            result.setMessage("用户未注册");
            result.setBody(null);
        } else {
            if (s.getPassword().equals(student.getPassword())) {
                StudentAndToken studentAndToken = new StudentAndToken(s, studentDao.generateStudentToken(student));
                result.setCode(ConstData.POST_SUCCESS);
                result.setMessage("success");
                result.setBody(studentAndToken);
            }

        }
        return result;
    }

    @RequestMapping(value = "/auth/teacher/register", method = RequestMethod.POST)
    public BaseBean<TeacherAndToken> response(@RequestBody Teacher input) {
        BaseBean<TeacherAndToken> baseBean = new BaseBean<>();
        if (teacherDao.findTeacherByPhone(input.getPhone()) == null) {
            TeacherAndToken teacherAndToken = teacherDao.registerTeacher(input);
            baseBean.setCode(ConstData.POST_SUCCESS);
            baseBean.setMessage("创建成功");
            baseBean.setBody(teacherAndToken);
            return baseBean;
        } else {
            baseBean.setCode(ConstData.NO_RESULT);
            baseBean.setMessage("该手机号已经注册");
            baseBean.setBody(null);
            return baseBean;
        }
    }

    @RequestMapping(value = "/auth/teacher/login", method = RequestMethod.POST)
    public BaseBean<TeacherAndToken> logResponse(@RequestBody Teacher teacher) {
        BaseBean<TeacherAndToken> result = new BaseBean<>();
        Teacher tmp = teacherDao.findTeacherByPhone(teacher.getPhone());
        if (null == tmp) {
            result.setCode(ConstData.NO_RESULT);
            result.setMessage("用户未注册");
            result.setBody(null);
        } else {
            if (tmp.getPassword().equals(teacher.getPassword())) {
                teacher.setPhone(tmp.getPhone());
                Token token = teacherDao.generateTeacherToken(tmp);
                TeacherAndToken teacherAndToken = new TeacherAndToken(tmp, token);
                result.setCode(ConstData.POST_SUCCESS);
                result.setMessage("success");
                result.setBody(teacherAndToken);
            } else {
                result.setCode(ConstData.WRONG_PARAM);
                result.setMessage("密码错误");
                result.setBody(null);
            }

        }
        return result;
    }

    @RequestMapping(value = "/auth/refreashtoken", method = RequestMethod.POST)
    public BaseBean<Token> refreshToken(@RequestBody Token token) {
        BaseBean<Token> result = new BaseBean<>();
        try {
            Token newToken = new Token(JWTUtil.parseJWT(token.getRefreshToken()).getSubject());
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
}
