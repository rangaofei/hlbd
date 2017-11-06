package com.hanlinbode.hlbd.facade;

import com.hanlinbode.hlbd.service.HomeWorkService;
import com.hanlinbode.hlbd.service.HomeworkQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateHomeworkFacade {
    @Autowired
    private HomeWorkService homeWorkService;
    @Autowired
    private HomeworkQuestionService homeworkQuestionService;

    public void createHomeWork(){

    }
}
