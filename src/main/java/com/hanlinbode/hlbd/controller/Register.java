package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.ConstData;
import com.hanlinbode.hlbd.bean.BaseBean;
import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Register {
    @Autowired
    private TeacherDao teacherDao;

    @RequestMapping(value = "/hello")
    public String Test() {
        return "hello";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseBean<Teacher> response(@RequestBody Teacher input) {
        System.out.println(input.toString());
        BaseBean baseBean = new BaseBean();
        if (teacherDao.findTeachers(input.getPhone()).size() < 1) {
            teacherDao.saveTeacher(input);
            baseBean.setCode(ConstData.POST_SUCCESS);
            baseBean.setMessage("创建成功");
            baseBean.setBody(input);
            return baseBean;
        } else {
            baseBean.setCode(ConstData.NO_RESULT);
            baseBean.setMessage("该手机号已经注册");
            baseBean.setBody(input);
            return baseBean;
        }
    }
}
