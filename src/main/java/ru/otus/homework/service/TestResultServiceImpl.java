package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.model.Answer;
import ru.otus.homework.model.Question;
import ru.otus.homework.model.TestResult;
import ru.otus.homework.model.User;

import java.util.List;

@Service("testResult")
public class TestResultServiceImpl implements TestResultService {

    @Override
    public TestResult saveTestResult(User user, List<Answer> answers, int sumQuestions) {
        return new TestResult(user, answers, sumQuestions);
    }
}
