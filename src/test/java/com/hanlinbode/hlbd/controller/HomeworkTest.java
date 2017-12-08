package com.hanlinbode.hlbd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanlinbode.hlbd.HlbdApplication;
import com.hanlinbode.hlbd.bean.StudentAnswerQuestion;
import com.hanlinbode.hlbd.config.WebSecurityConfig;
import com.hanlinbode.hlbd.service.AnswerQuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HlbdApplication.class)
@WebAppConfiguration
@Rollback(value = true)
@Transactional(transactionManager = "transactionManager")
public class HomeworkTest {
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
    public void getHomeworks() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/student/{student_id}/{class_id}/gethomeworks",
                "15088135711311", "4886501581")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer " + studentToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getAnswerQuestions() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/teacher/{homework_id}/{question_id}/correctanswer",
                "15100485788253", "15")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer " + studentToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

//    @Test
//    public void correctAnswerQuestions() throws Exception {
//        ObjectMapper o = new ObjectMapper();
//        List<StudentAnswerQuestion> result = new ArrayList<>();
//        StudentAnswerQuestion question = new StudentAnswerQuestion(196, 100, "123", "123", "15088135711311",
//                3, "15101273059704");
//        result.add(question);
//        String content = o.writeValueAsString(result);
//        logger.info(content);
//        mvc.perform(MockMvcRequestBuilders.post("/teacher/{homework_id}/{question_id}/correctanswer",
//                "15126143004500", "3")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .header("Authorization", "Bearer " + studentToken)
//                .content(content))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
}
