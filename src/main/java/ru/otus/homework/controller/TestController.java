package ru.otus.homework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.homework.model.Answer;
import ru.otus.homework.model.Question;
import ru.otus.homework.model.TestResult;
import ru.otus.homework.model.User;
import ru.otus.homework.service.*;
import java.util.*;

@ShellComponent
public class TestController {
    private final QuestionService questionService;
    private final UserService userService;
    private final IOService ioService;
    private final BundleService bundleService;
    private final TestResultService testResultService;
    private final AnswerService answerService;
    private boolean isInformed;
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    public TestController(QuestionService questionService, UserService userService, IOService ioService, BundleService bundleService, TestResultService testResultService, AnswerService answerService) {
        this.questionService = questionService;
        this.userService = userService;
        this.ioService = ioService;
        this.bundleService = bundleService;
        this.testResultService = testResultService;
        this.answerService = answerService;
    }

    @ShellMethod(key = {"info", "i"}, value = "information about testing")
    public void greeeting(){
        ioService.printRequest(bundleService.getString("user.data.greeting"));
        this.isInformed = true;
    }

    @ShellMethod(key = {"start", "s"}, value = "start testing")
    @ShellMethodAvailability("availabilityInitTest")
    public void initTest() {
        String userName = ioService.printResponse(bundleService.getString("user.data.request"));
        User user = userService.saveUser(userName);
        ioService.printRequest(bundleService.getString("user.data.rules"));

        List<Question> questions = questionService.getAllQuestions();
        List<Answer> answers = new ArrayList<>();
        for(Question question : questions){
            Answer answer = answerService.saveAnswer(question, ioService.printResponse(question.getQuestion()));
            if (answer.getAnswer().equals("+")) {
                break;
            }
            answers.add(answer);
            logger.debug(answer.toString());
        }
        TestResult testResult = testResultService.saveTestResult(user, answers, questions.size());
        ioService.printRequest(bundleService.getString("user.data.final1") + answers.size() + " " + bundleService.getString("user.data.final2") + questions.size());
        logger.info(testResult.toString());
    }

    public Availability availabilityInitTest(){
        return isInformed
                ? Availability.available()
                : Availability.unavailable("read information before testing");
    }

}
