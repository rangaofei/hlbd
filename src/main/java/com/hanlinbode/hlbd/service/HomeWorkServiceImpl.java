package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.*;
import com.hanlinbode.hlbd.dao.*;
import com.hanlinbode.hlbd.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HomeWorkServiceImpl implements HomeWorkService {
    @Autowired
    private HomeWorkRepository homeWorkRepository;

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private AnswerServiceImpl answerDao;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private HomeworkQuestionRepository homeworkQuestionRepository;

    @Override
    public TeacherHomework createHomeWork(String teacherId, TeacherHomework teacherHomework, List<Team> teams) {
        teacherHomework.setCreatedTime(new Date());
        teacherHomework.setHomeworkId(UUIDUtil.generateId());
        teacherHomework.setTeacherId(teacherId);
        teacherHomework.setQuestionCount(teacherHomework.getTeacherHomeworkQuestions().size());
        teacherHomework.setDifficult(calculateDifficult(teacherHomework.getTeacherHomeworkQuestions()));
        int totalStudent = 0;
        for (Team m : teams) {
            Team team = teamRepository.findTeamByTeamId(m.getTeamId());
            totalStudent += team.getTeamVolume();
        }
        teacherHomework.setTotalStudent(totalStudent);

        for (Team t : teams) {
            Team team = teamRepository.findTeamByTeamId(t.getTeamId());
            team.getTeacherHomeworkList().add(teacherHomework);
            answerDao.saveAnserByTeam(teacherHomework, team);
        }
        return homeWorkRepository.saveAndFlush(teacherHomework);

    }

    @Override
    public List<TeacherHomework> findHomeWorkByTeacherId(String teacherId) {
        return homeWorkRepository.findHomeWorkByTeacherId(teacherId);
    }

    @Override
    public TeacherHomework findHomeWorkByHomeWorkId(String homeworkId) {
        return homeWorkRepository.findTeacherHomeWorkByHomeworkId(homeworkId);
    }

    @Override
    public TeacherHomework updateTeacherHomeWork(TeacherHomework teacherHomework) {

        return homeWorkRepository.saveAndFlush(teacherHomework);
    }

    @Override
    public TeacherHomework updateCommitCount(String homeworkId) {
        TeacherHomework teacherHomework = homeWorkRepository.findTeacherHomeWorkByHomeworkId(homeworkId);
        teacherHomework.setCommitedCount(teacherHomework.getCommitedCount() + 1);
        return homeWorkRepository.saveAndFlush(teacherHomework);
    }

    /**
     * 计算家庭作业的难度系数
     *
     * @param questionList
     * @return
     */
    @Override
    public float calculateDifficult(List<TeacherHomeworkQuestion> questionList) {
        float sum = 0F;
        for (TeacherHomeworkQuestion question : questionList) {
            Question q = questionRepository.findQuestionById(question.getQuestionId());
            sum += q.getDifficult();
        }
        return sum / questionList.size();
    }
}
