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
import ru.otus.homework.service.BundleService;
import ru.otus.homework.service.QuestionService;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.UserService;
import java.util.*;

@ShellComponent
public class TestController {

    private final QuestionService questionService;
    private final UserService userService;
    private final IOService ioService;
    private final BundleService bundleService;

    private boolean isInformed;

    public TestController(QuestionService questionService, UserService userService, IOService ioService, BundleService bundleService) {
        this.questionService = questionService;
        this.userService = userService;
        this.ioService = ioService;
        this.bundleService = bundleService;
    }

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @ShellMethod(key = {"info", "i"}, value = "information about testing")
    public void greeeting(){
        ioService.printRequest(bundleService.getString("user.data.greeting"));
        this.isInformed = true;
    }

    @ShellMethod(key = {"start", "s"}, value = "start testing")
    @ShellMethodAvailability("availabilityInitTest")
    public void initTest() {
        TestResult result = new TestResult();
        List<Answer> answers = new ArrayList<>();
        result.setAnswers(answers);

        String userName = ioService.printResponse(bundleService.getString("user.data.request"));
        User user = userService.saveUser(userName);
        result.setUser(user);

        ioService.printRequest(bundleService.getString("user.data.rules"));

        List<Question> questions = questionService.getAllQuestions();
        for(Question question : questions){

            Answer answer = new Answer();
            answer.setQuestion(question);
            answer.setAnswer(ioService.printResponse(question.getQuestion()));

            if (answer.getAnswer().equals("+")) {
                break;
            }

            answers.add(answer);
            logger.debug(answer.getQuestion().getQuestion() + " - " + answer.getAnswer());
        }
        ioService.printRequest(bundleService.getString("user.data.final1") + answers.size() + " " + bundleService.getString("user.data.final2") + questions.size());
        logger.info(result.toString());
    }

    public Availability availabilityInitTest(){
        return isInformed
                ? Availability.available()
                : Availability.unavailable("read information before testing");
    }

}
