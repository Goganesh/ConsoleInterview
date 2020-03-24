package ru.otus.homework.service;

import ru.otus.homework.model.Answer;
import ru.otus.homework.model.Question;
import ru.otus.homework.model.TestResult;
import ru.otus.homework.model.User;

import java.util.List;

public interface TestResultService {
    TestResult saveTestResult(User user, List<Answer> answers, int sumQuestions);
}
