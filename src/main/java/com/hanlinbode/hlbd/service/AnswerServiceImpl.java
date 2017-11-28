package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.*;
import com.hanlinbode.hlbd.composbean.StudentAndToken;
import com.hanlinbode.hlbd.composbean.StudentCostTime;
import com.hanlinbode.hlbd.composbean.StudentRate;
import com.hanlinbode.hlbd.dao.AnswerQuestionRepository;
import com.hanlinbode.hlbd.dao.AnswerRepository;
import com.hanlinbode.hlbd.dao.QuestionRepository;
import com.hanlinbode.hlbd.enums.AnswerState;
import com.hanlinbode.hlbd.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AnswerServiceImpl implements AnswerService {
    private static final Logger logger = LoggerFactory.getLogger(AnswerServiceImpl.class);
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerQuestionRepository answerQuestionRepository;
    @Autowired
    private AnswerQuestionService answerQuestionService;
    @Autowired
    private HomeworkQuestionService homeworkQuestionService;
    @Autowired
    private HomeWorkService homeWorkService;


    @Override
    public StudentAnswer saveAnswer(TeacherHomework teacherHomework, Student student) {
        StudentAnswer studentAnswer = new StudentAnswer();
//        studentAnswer.setAnswerTeacherHomework(teacherHomework);
//        studentAnswer.setStudent(student);
        answerRepository.save(studentAnswer);
        return null;
    }

    @Override
    public List<StudentAnswer> saveAnswerByTeam(TeacherHomework teacherHomework, Team team) {
        List<StudentAnswer> list = new ArrayList<>();
        for (Student s : team.getStudents()) {
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.initWithHomeWork(teacherHomework);
            studentAnswer.setStudentId(s.getStudentId());
            studentAnswer.setStudentName(s.getName());
            studentAnswer.setTeamId(team.getTeamId());
            list.add(studentAnswer);
        }
        return answerRepository.save(list);
    }

    @Override
    public List<StudentAnswer> saveAnswerByTeams(TeacherHomework teacherHomework, List<Team> teams) {
        List<StudentAnswer> list = new ArrayList<>();
        for (Team team : teams) {
            for (Student s : team.getStudents()) {
                StudentAnswer studentAnswer = new StudentAnswer();
                studentAnswer.initWithHomeWork(teacherHomework);
                studentAnswer.setStudentId(s.getStudentId());
                studentAnswer.setStudentName(s.getName());
                studentAnswer.setTeamId(team.getTeamId());
                studentAnswer.setState(AnswerState.NOT_COMMIT);
                list.add(studentAnswer);
            }
        }
        return answerRepository.save(list);
    }

    @Override
    public List<StudentAnswer> saveAnswerByStudents(TeacherHomework teacherHomework, List<Student> students) {
        List<StudentAnswer> list = new ArrayList<>();
        for (Student s : students) {
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.initWithHomeWork(teacherHomework);
            studentAnswer.setStudentId(s.getStudentId());
            studentAnswer.setStudentName(s.getName());
            list.add(studentAnswer);
        }
        return null;
    }

    @Override
    public List<StudentAnswer> findAnswerByTeamAndStudent(String teamId, String studentId) {
        return answerRepository.findStudentAnswersByTeamIdAndStudentId(teamId, studentId);
    }


    @Override
    public StudentAnswer findAnswerById(String answerId) {
        return answerRepository.findStudentAnswerByAnswerId(answerId);
    }

    @Override
    public StudentAnswer updateAnswer(StudentAnswer answer) {
        return answerRepository.saveAndFlush(answer);
    }

    @Override
    public StudentAnswer calucateCorrectRate(String answerId) {
        StudentAnswer studentAnswer = findAnswerById(answerId);
        studentAnswer.setCorrectRate(answerQuestionService.calculateStudentAnswerCorrectRate(answerId));
        //更新老师表中的正确率

        return answerRepository.save(studentAnswer);
    }

    @Override
    public Map<String, Integer> getStudentCostTime(String studentId) {
        Map<String, Integer> costTime = new HashMap<>();
        List<String> subject = answerRepository.getAllSubject(studentId);
        costTime.put("totalTime",
                answerRepository.getTotalTime(studentId) == null ? 0 : answerRepository.getTotalTime(studentId));
        if (subject.size() > 0) {
            for (String name : subject) {
                costTime.put(name, answerRepository.getCostTimeBySubjectName(studentId, name));
            }
        }
        return costTime;
    }

    @Override
    public List<Map<String, StudentRate>> getStudentHistoryRate(String studentId, int num, int index) {
        List<Map<String, StudentRate>> result = new ArrayList<>();
        List<String> subject = answerRepository.getAllSubject(studentId);
        for (String name : subject) {
            Map<String, StudentRate> map = new HashMap<>();
            StudentRate studentRate = new StudentRate();
            List<StudentAnswer> tmp = answerRepository.getAnswerBySubjectAndStudentId(studentId, name);
            Map<String, Float> selfRate = new LinkedHashMap<>();
            Map<String, Float> averageRate = new LinkedHashMap<>();
            for (StudentAnswer s : tmp) {
                System.out.println(s.getCreatedTime());
                String date = DateUtil.dateToString(s.getCreatedTime());
                selfRate.put(date, s.getCorrectRate());
                averageRate.put(date, homeWorkService.findHomeWorkByHomeWorkId(s.getHomeworkId()).getCorrectRate());
            }
            studentRate.setAverage(averageRate);
            studentRate.setSelf(selfRate);
            map.put(name, studentRate);
            result.add(map);
        }
        return result;
    }

}
