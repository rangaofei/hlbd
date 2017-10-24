package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.ConstData;
import com.hanlinbode.hlbd.bean.BaseBean;
import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.bean.Token;
import com.hanlinbode.hlbd.dao.StudentDao;
import com.hanlinbode.hlbd.dao.TeacherDao;
import com.hanlinbode.hlbd.dao.TeacherSubjectDao;
import com.hanlinbode.hlbd.util.JWTUtil;
import com.hanlinbode.hlbd.util.UUIDUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private TeacherSubjectDao teacherSubjectDao;

    @RequestMapping(value = "/auth/student/register", method = RequestMethod.POST)
    public BaseBean<Student> response(@RequestBody Student input) {
        System.out.println(input.toString());
        System.out.println(input.getPhone());
        BaseBean baseBean = new BaseBean();
        if (null == studentDao.findStudentByPhone(input.getPhone())) {

            studentDao.saveStudent(input);
            input.setToken(studentDao.generateStudentToken(input));
            baseBean.setCode(ConstData.POST_SUCCESS);
            baseBean.setMessage("创建成功");
            baseBean.setBody(input);
            try {
                System.out.println(JWTUtil.parseJWT(input.getToken().getAccessToken()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return baseBean;
        } else {
            baseBean.setCode(ConstData.NO_RESULT);
            baseBean.setMessage("该手机号已经注册");
            baseBean.setBody(input);
            return baseBean;
        }
    }

    @RequestMapping(value = "/auth/student/login", method = RequestMethod.POST)
    public BaseBean<Student> logResponse(@RequestBody Student student) {
        System.out.println(student.toString());
        BaseBean<Student> result = new BaseBean<>();
        Student s = studentDao.findStudentByPhone(student.getPhone());
        if (null == s) {
            result.setCode(ConstData.NO_RESULT);
            result.setMessage("用户未注册");
            result.setBody(student);
        } else {
            result.setCode(ConstData.NO_RESULT);
            result.setMessage("密码错误");
            result.setBody(student);

            if (s.getPassword().equals(student.getPassword())) {
                System.out.println(s.toString());
                s.setToken(studentDao.generateStudentToken(s));
                result.setCode(ConstData.POST_SUCCESS);
                result.setMessage("success");
                result.setBody(s);
            }

        }
        return result;
    }

    @ApiOperation(value = "用户注册", notes = "根据User对象创建用户")
//    @ApiImplicitParam(name = "Teacher", value = "用户详细实体user", required = true, dataType = "Teacher")
    @RequestMapping(value = "/auth/teacher/register", method = RequestMethod.POST)
    public BaseBean<Teacher> response(@RequestBody Teacher input) {
        System.out.println(input.toString());
        BaseBean baseBean = new BaseBean();
        if (teacherDao.finTeacherByPhone(input.getPhone()) == null) {
            input.setCreatedTime(new Date());
            input.setTeacherId(UUIDUtil.generateId());
            teacherDao.saveTeacher(input);
            input.setToken(teacherDao.generateTeacherToken(input));
            baseBean.setCode(ConstData.POST_SUCCESS);
            baseBean.setMessage("创建成功");
            baseBean.setBody(input);
            return baseBean;
        } else {
            baseBean.setCode(ConstData.NO_RESULT);
            baseBean.setMessage("该手机号已经注册");
            baseBean.setBody(input);
            return baseBean;
        }
    }

    @RequestMapping(value = "/auth/teacher/login", method = RequestMethod.POST)
    public BaseBean<Teacher> logResponse(@RequestBody Teacher teacher) {
        System.out.println(teacher.toString());
        BaseBean<Teacher> result = new BaseBean<>();
        List<Teacher> teacherList = teacherDao.findTeachersByName(teacher.getName());
        if (teacherList.size() < 1) {
            result.setCode(ConstData.NO_RESULT);
            result.setMessage("用户未注册");
            result.setBody(teacher);
        } else {
            result.setCode(ConstData.NO_RESULT);
            result.setMessage("密码错误");
            result.setBody(teacher);
            for (Teacher t : teacherList) {
                if (t.getPassword().equals(teacher.getPassword())) {
                    System.out.println(t.toString());
                    teacher.setPhone(t.getPhone());
                    t.setToken(teacherDao.generateTeacherToken(teacher));
                    result.setCode(ConstData.POST_SUCCESS);
                    result.setMessage("success");
                    result.setBody(t);
                    break;
                }
            }

        }
        return result;
    }

    @RequestMapping(value = "/auth/refreashtoken", method = RequestMethod.POST)
    public BaseBean<Token> refreashToken(@RequestBody Token token) {
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
