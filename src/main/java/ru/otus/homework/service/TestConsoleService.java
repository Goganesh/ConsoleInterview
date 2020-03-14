package ru.otus.homework.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service("testService")
public class TestConsoleService implements TestService {

    @Override
    public String askUserName(String callName) {
        System.out.println(callName);
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    @Override
    public void tellRules(String rules) {
        System.out.println(rules);
    }

    @Override
    public String callAnswerForQuestion(String callAnswer) {
        System.out.println(callAnswer);

        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    @Override
    public void tellResult(String result) {
        System.out.println(result);
    }
}
