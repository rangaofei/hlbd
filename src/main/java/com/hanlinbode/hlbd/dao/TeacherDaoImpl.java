package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.bean.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public Teacher finTeacherByPhone(String phone) {
        return repository.findTeacherByPhone(phone);
    }

    @Override
    public Token generateTeacherToken(Teacher teacher) {
        return new Token("TTT"+teacher.getPhone());
    }

    @Override
    public List<Teacher> findTeachersByName(String name) {
        return repository.findTeacherByName(name);
    }

    @Override
    public Teacher findTeacherByTeacherId(String teacherId) {
        return repository.findTeacherByTeacherId(teacherId);
    }

}
