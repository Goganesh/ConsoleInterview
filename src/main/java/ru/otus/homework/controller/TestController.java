package ru.otus.homework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.homework.model.Answer;
import ru.otus.homework.model.Question;
import ru.otus.homework.model.TestResult;
import ru.otus.homework.model.User;
import ru.otus.homework.service.BundleService;
import ru.otus.homework.service.QuestionService;
import ru.otus.homework.service.IOService;
import java.util.*;

@Service
public class TestController {

    private final QuestionService questionService;
    private final IOService IOService;
    private final BundleService bundleService;

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    public TestController(@Qualifier("questionService")QuestionService questionService,
                          @Qualifier("bundleService") BundleService bundleService,
                          @Qualifier("ioService") IOService IOService) {
        this.questionService = questionService;
        this.bundleService = bundleService;
        this.IOService = IOService;
    }

    public void initTest() {
        TestResult result = new TestResult();
        List<Answer> answers = new ArrayList<>();
        User user = new User();
        result.setAnswers(answers);
        result.setUser(user);

        String userName = IOService.printResponse(bundleService.getString("user.data.request"));
        user.setName(userName);

        IOService.printRequest(bundleService.getString("user.data.rules"));

        List<Question> questions = questionService.getAllQuestions();
        for(Question question : questions){

            Answer answer = new Answer();
            answer.setQuestion(question);
            answer.setAnswer(IOService.printResponse(question.getQuestion()));

            if (answer.getAnswer().equals("+")) {
                break;
            }

            answers.add(answer);
            logger.debug(answer.getQuestion().getQuestion() + " - " + answer.getAnswer());
        }
        IOService.printRequest(bundleService.getString("user.data.final1") + answers.size() + " " + bundleService.getString("user.data.final2") + questions.size());
        logger.debug(result.toString());
    }

}
