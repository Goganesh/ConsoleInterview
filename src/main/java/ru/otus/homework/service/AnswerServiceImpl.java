package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.model.Answer;
import ru.otus.homework.model.Question;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Override
    public Answer saveAnswer(Question question, String answer) {
        return new Answer(question, answer);
    }
}
