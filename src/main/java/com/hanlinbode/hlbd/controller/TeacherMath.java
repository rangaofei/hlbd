package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.composbean.BaseBean;
import com.hanlinbode.hlbd.facade.TeamInfoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherMath {
    @Autowired
    private TeamInfoFacade teamInfoFacade;

    @RequestMapping(path = "/teacher/{team_id}/getrecentrate", method = RequestMethod.GET)
    public BaseBean<List<TeacherHomework>> getRecentRate(@PathVariable("team_id") String teamId) {
        BaseBean<List<TeacherHomework>> result = new BaseBean<>();
        List<TeacherHomework> teacherHomeworkList = teamInfoFacade.getAllHomework(teamId);
        if (teacherHomeworkList == null) {
            result.setMessage("获取失败");
            result.setCode(404);
            return result;
        }
        result.setMessage("获取成功");
        result.setCode(200);
        result.setBody(teacherHomeworkList);
        return result;
    }
}
