package ru.otus.homework.service;

public interface TestService {
    String askUserName(String callName);
    String callAnswerForQuestion(String callAnswer);
    void tellRules(String rules);
    void tellResult(String result);
}
