package com.hanlinbode.hlbd.security;

import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.dao.StudentRepository;
import com.hanlinbode.hlbd.dao.TeacherRepository;
import com.hanlinbode.hlbd.exception.ResultNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.startsWith("TTT")) {
            Teacher teacher = teacherRepository.findTeacherByPhone(username.substring(3));
            if (teacher == null) {
                throw new ResultNotFoundException("您的账户已不存在");
            } else {
                return JwtUserFactory.create(teacher);
            }
        } else {
            Student student = studentRepository.findStudentByPhone(username.substring(3));
            if (student == null) {
                throw new ResultNotFoundException("您的账户已不存在");
            } else {
                return JwtUserFactory.create(student);
            }
        }


    }
}

