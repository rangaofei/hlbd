package com.hanlinbode.hlbd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.controller.AuthController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AuthTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp() {
//        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc = MockMvcBuilders.standaloneSetup(new AuthController()).build();
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
}
