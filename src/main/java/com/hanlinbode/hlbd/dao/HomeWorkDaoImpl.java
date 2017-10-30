package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.TeacherHomeWork;
import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.bean.TeacherHomeworkList;
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
    public TeacherHomeWork createHomeWork(String teacherId, TeacherHomeWork teacherHomeWork, List<Team> teams) {
        teacherHomeWork.setCreatedTime(new Date());
        teacherHomeWork.setHomeworkId(UUIDUtil.generateId());
        for (TeacherHomeworkList tlist : teacherHomeWork.getTeacherHomeworkLists()) {
            tlist.setTeacherHomeWork(teacherHomeWork);
        }//保存题目列表的题目外键
        Teacher teacher = teacherRepository.findTeacherByTeacherId(teacherId);
        teacherHomeWork.setTeacherHomeWork(teacher);//保存老师的外键
        int totalStudent = 0;
        //把题目发给班级

        for (Team m : teams) {
            Team team = teamRepository.findTeamByTeamId(m.getTeamId());
            totalStudent += team.getTeamColumn();
        }
        teacherHomeWork.setTotalStudent(totalStudent);

        for (Team t : teams) {
            Team team = teamRepository.findTeamByTeamId(t.getTeamId());
            team.getTeacherHomeWorkList().add(teacherHomeWork);
            answerDao.saveAnserByTeam(teacherHomeWork, team);

        }
        return homeWorkRepository.save(teacherHomeWork);

    }

    @Override
    public List<TeacherHomeWork> findHomeWorkByTeacherId(String teacherId) {
        return homeWorkRepository.findHomeWorkByTeacherId(teacherId);
    }
}
