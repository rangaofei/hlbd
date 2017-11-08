package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.HlbdApplication;
import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import com.hanlinbode.hlbd.config.WebSecurityConfig;
import com.hanlinbode.hlbd.service.HomeWorkService;
import com.hanlinbode.hlbd.service.HomeworkQuestionService;
import com.hanlinbode.hlbd.service.HomeworkQuestionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HlbdApplication.class)
@ContextConfiguration(classes = WebSecurityConfig.class)
@WebAppConfiguration
@Rollback(value = false)
public class HomeworkQuestionReTest {


//    @Autowired
//    private HomeworkQuestionRepository homeworkQuestionRepository;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void findQuestion() {
//        List<TeacherHomeworkQuestion> list = homeworkQuestionRepository
//                .findTeacherHomeworkQuestionsByTeacherHomeworkId("15101273058850");
//        System.out.println(list.size());
//    }
}
