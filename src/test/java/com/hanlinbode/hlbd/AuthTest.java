package com.hanlinbode.hlbd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.composbean.Token;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HlbdApplication.class)
@WebAppConfiguration
@Rollback(value = true)
@Transactional(transactionManager = "transactionManager")
public class AuthTest {
    private static final Logger logger = LoggerFactory.getLogger(AuthTest.class);

    private static final String refreshToken = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MDk4NTM5MjYsInN1YiI6IlNTU2pydCIsImV4cCI6MTUyNzk5NzkyNn0.UvywOgRf9VnpZe8_PqmZAYNfw9v5dV0gZyenaB69hnU";
    @Resource
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testTeacherRegister() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setName("冉高飞");
        teacher.setPassword("123");
        teacher.setPhone("131");
        ObjectMapper o = new ObjectMapper();
        String content = o.writeValueAsString(teacher);
        mvc.perform(MockMvcRequestBuilders.post("/auth/teacher/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content.getBytes())
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testTeacherLogin() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setPassword("123");
        teacher.setPhone("131");
        ObjectMapper o = new ObjectMapper();
        String content = o.writeValueAsString(teacher);
        mvc.perform(MockMvcRequestBuilders.post("/auth/teacher/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testStudentRegister() throws Exception {
        Student student = new Student("小刚", "789", "jrt");
        ObjectMapper o = new ObjectMapper();
        String content = o.writeValueAsString(student);

        mvc.perform(MockMvcRequestBuilders.post("/auth/student/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testStudentLogin() throws Exception {
        Student student = new Student("789", "jrt");
        ObjectMapper o = new ObjectMapper();
        String content = o.writeValueAsString(student);

        mvc.perform(MockMvcRequestBuilders.post("/auth/student/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testRefreshToken() throws Exception {
        Token token = new Token(refreshToken);
        ObjectMapper o = new ObjectMapper();
        String content = o.writeValueAsString(token);
        mvc.perform(MockMvcRequestBuilders.post("/auth/refreshtoken")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
