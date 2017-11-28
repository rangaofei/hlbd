package com.hanlinbode.hlbd.facade;

import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamInfoFacade {
    private static final Logger logger = LoggerFactory.getLogger(TeamInfoFacade.class);
    @Autowired
    private TeamService teamService;

    public List<TeacherHomework> getAllHomework(String teamId) {
        Team team = teamService.findTeamByTeamId(teamId);
        if (team == null) {
            return null;
        }
        List<TeacherHomework> homeworkList = teamService.findTeamByTeamId(teamId).getTeacherHomeworkList();
        return homeworkList;
    }
}
