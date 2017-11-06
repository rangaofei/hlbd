package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.composbean.TeacherAndToken;
import com.hanlinbode.hlbd.composbean.Token;
import com.hanlinbode.hlbd.dao.TeacherRepository;
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
    public Token generateToken(String src) {
        return Token.generateToken("TTT" + src);
    }
}
