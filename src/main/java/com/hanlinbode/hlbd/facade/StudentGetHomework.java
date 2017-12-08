package com.hanlinbode.hlbd.facade;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.enums.AnswerState;
import com.hanlinbode.hlbd.service.AnswerService;
import com.hanlinbode.hlbd.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentGetHomework {
    private static final Logger logger = LoggerFactory.getLogger(StudentGetHomework.class);
    @Autowired
    private AnswerService answerService;
    @Autowired
    private TeamService teamService;

    public List<StudentAnswer> getStudentAnswerByStudent(String teamId, String studentId) {
        Team team = teamService.findTeamByTeamId(teamId);
        List<StudentAnswer> list = answerService.findAnswerByTeamAndStudent(teamId, studentId);

        for (StudentAnswer s : list) {
            s.setStudentCount(answerService.findAnswerByTeamAndHomework(teamId,s.getHomeworkId(),AnswerState.OTHER).size());
            int i = answerService.findAnswerByTeamAndHomework(teamId, s.getHomeworkId(),AnswerState.NOT_COMMIT).size();
            s.setCommitedStudentCount( i);
        }
        return list;
    }
}
