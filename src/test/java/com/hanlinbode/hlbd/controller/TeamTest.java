package com.hanlinbode.hlbd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanlinbode.hlbd.HlbdApplication;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.config.JwtAuthenticationTokenFilter;
import com.hanlinbode.hlbd.config.WebSecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HlbdApplication.class)
@ContextConfiguration(classes = WebSecurityConfig.class)
@WebAppConfiguration
@Rollback(value = false)
public class TeamTest {
    private static final Logger logger = LoggerFactory.getLogger(TeamTest.class);
    private String token = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MDk4MDY4MzgsInN1YiI6IlRUVDEzMSIsImV4cCI6MTUwOTgwNjg0OH0.GcWW2DIYExgOcWinz560NhEXwv91c7vr4krhcNT185Y";
    private String studentToken = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MDk5NDIxNTgsInN1YiI6IjEyMzQ1NiIsImV4cCI6MTUxMDU0Njk1OH0.8zDH4W2hfXdVK4d8pZMfzVz1Qs6DiDTWpa5PmwXHhJQ";
    private MockMvc mvc;
    @Resource
    private WebApplicationContext context;
    private WebSecurityConfig webSecurityConfig;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
//                .addFilters(new JwtAuthenticationTokenFilter())
                .build();
    }

    @Test
    public void createTeam() throws Exception {
        Team team = new Team();
        team.setTeamName("二年级");
        team.setTeamIntroduction("第一个班级");
        ObjectMapper o = new ObjectMapper();
        String content = o.writeValueAsString(team);
        mvc.perform(MockMvcRequestBuilders.post("/teacher/15098054982613/createclass")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer " + token)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getTeacherTeam() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/teacher/{teacher_id}/getclasses", "15087427115547")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getTeamInfo() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/teacher/{team_id}/getteaminfo", "9730570060")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getTeamStudents() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/teacher/{team_id}/getteamstudents", "9730570060")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void joinTeam() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/student/{student_id}/joinclass","15099449006271")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer " + studentToken)
                .param("class_id", "0222362465"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getClasses() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/student/{student_id}/getclasses", "15098521959830")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer " + studentToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}
