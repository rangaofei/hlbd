package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Answer;
import com.hanlinbode.hlbd.bean.HomeWork;
import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class HomeWorkDaoImpl implements HomeWorkDao {
    @Autowired
    private HomeWorkRepository homeWorkRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private AnswerDaoImpl answerDao;

    @Override
    public HomeWork createHomeWork(String teacherId, HomeWork homeWork, List<Team> teams) {
        homeWork.setCrteatedTime(new Date());
        homeWork.setHomeworkId(UUIDUtil.generateId());
        for (Team t : teams) {
            Team team = teamRepository.findTeamByTeamId(t.getTeamId());
            team.getHomeWorkList().add(homeWork);
            answerDao.saveAnserByTeam(homeWork, team);
        }
        Teacher teacher = teacherRepository.findTeacherByTeacherId(teacherId);
        homeWork.setTeacherHomeWork(teacher);
        return homeWorkRepository.save(homeWork);

    }

    @Override
    public List<HomeWork> findHomeWorkByTeacherId(String teacherId) {
        return homeWorkRepository.findHomeWorkByTeacherId(teacherId);
    }
}
