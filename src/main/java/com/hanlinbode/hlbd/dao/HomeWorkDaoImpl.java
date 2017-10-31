package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.*;
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
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public TeacherHomeWork createHomeWork(String teacherId, TeacherHomeWork teacherHomeWork, List<Team> teams) {
        teacherHomeWork.setCreatedTime(new Date());
        teacherHomeWork.setHomeworkId(UUIDUtil.generateId());
        for (TeacherHomeworkList tlist : teacherHomeWork.getTeacherHomeworkLists()) {
            tlist.setTeacherHomeWork(teacherHomeWork);
        }//保存题目列表的题目外键
        Teacher teacher = teacherRepository.findTeacherByTeacherId(teacherId);
        teacherHomeWork.setTeacherHomeWork(teacher);//保存老师的外键
        teacherHomeWork.setQuestionCount(teacherHomeWork.getTeacherHomeworkLists().size());
        float sum = 0;
        for (TeacherHomeworkList list : teacherHomeWork.getTeacherHomeworkLists()) {
            Question question = questionRepository.findQuestionById(list.getQuestionId());
            sum += question.getDifficult();
        }
        System.out.println("总难度"+sum);
        teacherHomeWork.setDifficult(sum / teacherHomeWork.getTeacherHomeworkLists().size());
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

    @Override
    public TeacherHomeWork findHomeWorkByHomeWorkId(String homeworkId) {
        return homeWorkRepository.findTeacherHomeWorkByHomeworkId(homeworkId);
    }

    @Override
    public TeacherHomeWork updateTeacherHomeWork(TeacherHomeWork teacherHomeWork) {

        return homeWorkRepository.saveAndFlush(teacherHomeWork);
    }


}
