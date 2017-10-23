package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.bean.TeacherSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherSubjectDaoImpl implements TeacherSubjectDao {
    @Autowired
    private TeacherSubjectRepository teacherSubjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<TeacherSubject> findSubjectsByTeacherId(String teacherId) {

        return teacherRepository.findTeacherByTeacherId(teacherId).getTeacherSubjects();
    }

    @Override
    public List<TeacherSubject> saveTeacherSubject(String id, List<TeacherSubject> teacherSubjects) {
        Teacher teacher = teacherRepository.findTeacherByTeacherId(id);
        for (TeacherSubject teacherSubject : teacherSubjects) {
            teacherSubject.setTeacher(teacher);
        }
        teacherSubjects.removeAll(teacher.getTeacherSubjects());
        teacher.getTeacherSubjects().addAll(teacherSubjects);
        teacherRepository.save(teacher);
        return teacherSubjects;
    }


}
