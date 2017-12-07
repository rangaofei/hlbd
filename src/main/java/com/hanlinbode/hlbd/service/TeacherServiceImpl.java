package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.composbean.TeacherAndToken;
import com.hanlinbode.hlbd.composbean.Token;
import com.hanlinbode.hlbd.dao.TeacherRepository;
import com.hanlinbode.hlbd.exception.ParamIncorrectException;
import com.hanlinbode.hlbd.exception.ResultAlreadyExistException;
import com.hanlinbode.hlbd.exception.ResultNotFoundException;
import com.hanlinbode.hlbd.util.RegexUtil;
import com.hanlinbode.hlbd.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher checkTeacherExist(Teacher teacher) {

        return teacherRepository.findTeacherByPhone(teacher.getPhone());
    }

    @Override
    public TeacherAndToken registerTeacher(Teacher teacher) {
        if (!RegexUtil.isMobile(teacher.getPhone())) {
            throw new ParamIncorrectException("不是正确的手机号码");
        }
        if (null != teacherRepository.findTeacherByPhone(teacher.getPhone())) {
            throw new ResultAlreadyExistException("手机号码已注册", new TeacherAndToken(teacher, null));
        }
        TeacherAndToken teacherAndToken = new TeacherAndToken();
        teacher.setCreatedTime(new Date());
        teacher.setTeacherId(UUIDUtil.generateId());
        teacherRepository.saveAndFlush(teacher);
        teacherAndToken.setTeacher(teacher);
        teacherAndToken.setToken(generateToken(teacher.getPhone()));
        return teacherAndToken;
    }

    @Override
    public Teacher findTeacherByTeacherId(String teacherId) {
        return teacherRepository.findTeacherByTeacherId(teacherId);
    }

    @Override
    public TeacherAndToken loginTeacher(Teacher teacher) {
        Teacher t = teacherRepository.findTeacherByPhone(teacher.getPhone());
        if (t == null) {
            throw new ResultNotFoundException("用户未注册", new TeacherAndToken(teacher, null));
        }
        if (!t.getPassword().equals(teacher.getPassword())) {
            throw new ParamIncorrectException("密码错误", new TeacherAndToken(teacher, null));
        }
        return new TeacherAndToken(t, generateToken(teacher.getPhone()));
    }

    @Override
    public Token generateToken(String src) {
        return Token.generateToken("TTT" + src);
    }
}
