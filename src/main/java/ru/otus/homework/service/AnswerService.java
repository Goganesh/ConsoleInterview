package ru.otus.homework.service;

import ru.otus.homework.model.Answer;
import ru.otus.homework.model.Question;

public interface AnswerService {
    Answer saveAnswer(Question question, String answer);
}
