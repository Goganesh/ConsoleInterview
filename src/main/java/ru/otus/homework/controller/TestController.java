package ru.otus.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import ru.otus.homework.model.Answer;
import ru.otus.homework.model.Question;
import ru.otus.homework.model.User;
import ru.otus.homework.service.QuestionService;
import ru.otus.homework.service.QuestionServiceImpl;
import java.util.*;

@Controller
@PropertySource("classpath:app.properties")
public class TestController {
    @Autowired
    private final QuestionService questionService;
    @Autowired
    private final ResourceBundle bundle;

    public TestController(QuestionServiceImpl questionService, @Qualifier("bundle") ResourceBundle bundle) {
        this.questionService = questionService;
        this.bundle = bundle;
    }

    public void initTest() {
        List<Answer> answers = new ArrayList<>();

        System.out.println(bundle.getString("FIRST_QUESTION"));
        User user = initUser();
        System.out.println(bundle.getString("RULES"));

        List<Question> questions = questionService.getAllQuestions();
        for(Question question : questions){
            int id = 1;
            System.out.println(question.getQuestion());
            Answer answer = initAnswer(question, user, id);

            if (answer.getAnswer().equals("+")) {
                break;
            }

            answers.add(answer);
            id++;
            System.out.println(answer.getQuestion().getQuestion() + " - " + answer.getAnswer());
        }
        System.out.println(bundle.getString("FINAL_WORD_1") + answers.size() + " " + bundle.getString("FINAL_WORD_2") + questions.size());

    }

    private User initUser(){
        Scanner scan = new Scanner(System.in);
        String userName = scan.nextLine();

        return new User(1, userName);
    }

    private Answer initAnswer(Question question, User user, int answerId) {
        Scanner scan = new Scanner(System.in);
        String answerLine = scan.nextLine();

        return new Answer(answerId, question, answerLine, user);
    }
}
