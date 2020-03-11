package ru.otus.homework.dao;

import ru.otus.homework.exeption.QuestionException;
import ru.otus.homework.model.Question;
import java.io.IOException;
import java.util.List;

public interface QuestionDao {

    List<Question> getAllQuestions() throws QuestionException;
}
