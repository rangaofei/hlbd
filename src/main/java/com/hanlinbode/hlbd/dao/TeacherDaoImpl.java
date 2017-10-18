package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class TeacherDaoImpl implements TeacherDao {
    @Autowired
    private TeacherRepository repository;

    @Override
    public List<Teacher> findTeachers(String phone) {
        List<Teacher> teachers = repository.findTeacherByPhone(phone);
        return teachers;
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        repository.save(teacher);
        return teacher;
    }
}
