package ru.otus.homework.controller;

import ru.otus.homework.model.Answer;
import ru.otus.homework.model.Question;
import ru.otus.homework.model.User;
import ru.otus.homework.service.QuestionService;
import ru.otus.homework.service.QuestionServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TestController {

    private final QuestionService questionService;

    private final static String FIRST_QUESTION = "Назовите свое Имя и Фамилию";
    private final static String RULES = "Если хотите завершить тестирование - введите символ +";
    private final static String FINAL_WORD_1 = "Опрос завершен. Вы ответили на ";
    private final static String FINAL_WORD_2 = " вопросов из ";

    public TestController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    public void initTest() {
        List<Answer> answers = new ArrayList<>();

        System.out.println(FIRST_QUESTION);
        User user = initUser();
        System.out.println(RULES);

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
        System.out.println(FINAL_WORD_1 + answers.size() + FINAL_WORD_2 + questions.size());

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
