package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findTeamById(int id);

    Team findTeamByTeamId(String teamId);

}
