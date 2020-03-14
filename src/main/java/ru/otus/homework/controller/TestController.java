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
import ru.otus.homework.service.TestService;
import java.util.*;

@Service
public class TestController {

    private final QuestionService questionService;
    private final TestService testService;
    private final BundleService bundleService;

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    public TestController(@Qualifier("questionService")QuestionService questionService,
                          @Qualifier("bundleService") BundleService bundleService,
                          @Qualifier("testService") TestService testService) {
        this.questionService = questionService;
        this.bundleService = bundleService;
        this.testService = testService;
    }

    public void initTest() {
        TestResult result = new TestResult();
        List<Answer> answers = new ArrayList<>();
        User user = new User();
        result.setAnswers(answers);
        result.setUser(user);

        String userName = testService.askUserName(bundleService.getString("FIRST_QUESTION"));
        user.setName(userName);

        testService.tellRules(bundleService.getString("RULES"));

        List<Question> questions = questionService.getAllQuestions();
        for(Question question : questions){

            Answer answer = new Answer();
            answer.setQuestion(question);
            answer.setAnswer(testService.callAnswerForQuestion(question.getQuestion()));

            if (answer.getAnswer().equals("+")) {
                break;
            }

            answers.add(answer);
            logger.debug(answer.getQuestion().getQuestion() + " - " + answer.getAnswer());
        }
        testService.tellResult(bundleService.getString("FINAL_WORD_1") + answers.size() + " " + bundleService.getString("FINAL_WORD_2") + questions.size());
        logger.debug(result.toString());
    }

}
