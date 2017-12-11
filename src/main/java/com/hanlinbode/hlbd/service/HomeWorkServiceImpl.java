package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.Question;
import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.dao.HomeWorkRepository;
import com.hanlinbode.hlbd.dao.HomeworkQuestionRepository;
import com.hanlinbode.hlbd.dao.QuestionRepository;
import com.hanlinbode.hlbd.dao.TeamRepository;
import com.hanlinbode.hlbd.enums.AnswerState;
import com.hanlinbode.hlbd.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class HomeWorkServiceImpl implements HomeWorkService {
    private static final Logger logger = LoggerFactory.getLogger(HomeworkQuestionServiceImpl.class);
    @Autowired
    private HomeWorkRepository homeWorkRepository;

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private HomeworkQuestionRepository homeworkQuestionRepository;

    @Override
    public TeacherHomework createHomeWork(String teacherId, TeacherHomework teacherHomework, List<Team> teams, int totalStudent) {
        teacherHomework.setCreatedTime(new Date());//设置创建时间
        teacherHomework.setHomeworkId(UUIDUtil.generateId());//自动生成作业的id
        teacherHomework.setTeacherId(teacherId);//设置发布作业的老师字段
        teacherHomework.setQuestionCount(teacherHomework.getTeacherHomeworkQuestions().size());//设置生成的题目数量字段
        teacherHomework.setDifficult(calculateDifficult(teacherHomework.getTeacherHomeworkQuestions()));//计算题目的平均难度
        teacherHomework.setTotalStudent(totalStudent);
        teacherHomework.setState(AnswerState.NOT_COMMIT);
        return homeWorkRepository.save(teacherHomework);
    }

    @Override
    public List<TeacherHomework> findHomeWorkByTeacherId(String teacherId) {
        logger.info(teacherId);
        List<TeacherHomework> result = homeWorkRepository.findTeacherHomeworksByTeacherId(teacherId);
        return result;
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

    @Override
    public TeacherHomework setHomeworkTotalStudent(int count, TeacherHomework teacherHomework) {
        teacherHomework.setTotalStudent(count);
        return teacherHomework;
    }


    @Override
    public TeacherHomework saveTeacherHomework(TeacherHomework teacherHomework) {
        return homeWorkRepository.saveAndFlush(teacherHomework);
    }
    @Modifying
    @Transactional
    @Override
    public int deleteTeacherHomework(String homeworkId) {
        logger.info("删除的item:" + homeworkId);
        return homeWorkRepository.deleteByHomeworkId(homeworkId);
    }
}
