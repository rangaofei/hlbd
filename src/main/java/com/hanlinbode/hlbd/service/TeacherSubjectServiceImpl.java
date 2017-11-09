package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.TeacherSubject;
import com.hanlinbode.hlbd.dao.TeacherRepository;
import com.hanlinbode.hlbd.dao.TeacherSubjectRepository;
import com.hanlinbode.hlbd.service.TeacherSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherSubjectServiceImpl implements TeacherSubjectService {
    @Autowired
    private TeacherSubjectRepository teacherSubjectRepository;


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

    @Override
    public List<TeacherSubject> deleteTeacherSubject(String id, List<TeacherSubject> teacherSubjects) {
        List<Integer> i = new ArrayList<>();
        for (TeacherSubject s : teacherSubjects) {
            i.add(s.getId());
        }
        teacherSubjectRepository.deleteTeacherSubjectsById(i);
        return findSubjectsByTeacherId(id);
    }


}
