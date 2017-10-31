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

        return teacherSubjectRepository.findTeacherSubjectsByTeacherId(teacherId);
    }

    @Override
    public List<TeacherSubject> saveTeacherSubject(String id, List<TeacherSubject> teacherSubjects) {
        for (TeacherSubject t : teacherSubjects) {
            t.setTeacherId(id);
        }
        List<TeacherSubject> teacherSubjectList = findSubjectsByTeacherId(id);
        teacherSubjects.removeAll(teacherSubjectList);
        teacherSubjectRepository.save(teacherSubjects);
        return findSubjectsByTeacherId(id);
    }


}
