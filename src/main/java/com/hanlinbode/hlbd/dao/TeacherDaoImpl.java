package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.composbean.TeacherAndToken;
import com.hanlinbode.hlbd.composbean.Token;
import com.hanlinbode.hlbd.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TeacherDaoImpl implements TeacherDao {
    @Autowired
    private TeacherRepository repository;

    @Override
    public List<Teacher> findTeachers(String phone) {
//        List<Teacher> teachers = repository.findTeacherByPhone(phone);
        return null;
    }


    @Override
    public Teacher saveTeacher(Teacher teacher) {
        repository.save(teacher);
        return teacher;
    }

    @Override
    public Teacher findTeacherByPhone(String phone) {
        return repository.findTeacherByPhone(phone);
    }

    @Override
    public Token generateTeacherToken(Teacher teacher) {
        return new Token("TTT" + teacher.getPhone());
    }

    @Override
    public List<Teacher> findTeachersByName(String name) {
        return repository.findTeacherByName(name);
    }

    @Override
    public Teacher findTeacherByTeacherId(String teacherId) {
        return repository.findTeacherByTeacherId(teacherId);
    }

    @Override
    public TeacherAndToken registerTeacher(Teacher teacher) {
        TeacherAndToken teacherAndToken = new TeacherAndToken();
        teacher.setCreatedTime(new Date());
        teacher.setTeacherId(UUIDUtil.generateId());
        saveTeacher(teacher);
        teacherAndToken.setTeacher(teacher);
        teacherAndToken.setToken(generateTeacherToken(teacher));
        return teacherAndToken;
    }

}
