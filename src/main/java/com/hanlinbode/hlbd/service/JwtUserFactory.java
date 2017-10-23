package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Teacher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(Teacher teacher) {
        ArrayList<String> list = new ArrayList<>();
        list.add(teacher.getRole());
        return new JwtUser(
                teacher.getTeacherId(),
                teacher.getName(),
                teacher.getPassword(),
                mapToGrantedAuthorities(list),
                teacher.getCreatedTime()
        );
    }

    public static JwtUser create(Student student) {
        ArrayList<String> list = new ArrayList<>();
        list.add(student.getRole());
        return new JwtUser(
                student.getStudentId(),
                student.getName(),
                student.getPassword(),
                mapToGrantedAuthorities(list),
                student.getCrteatedTime()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}

