package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.TeacherClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherClassDaoImpl implements TeacherClassDao {
    @Autowired
    private TeacherClassRepository teacherClassRepository;

    @Override
    public TeacherClass createClass(TeacherClass teacherClass) {
        teacherClassRepository.save(teacherClass);
        return teacherClass;
    }

    @Override
    public List<TeacherClass> getTeacherclasses(int teacherId) {
        return teacherClassRepository.findTeacherClassesById(teacherId);
    }
}
