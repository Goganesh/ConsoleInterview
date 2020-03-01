package ru.otus.homework.service;

import ru.otus.homework.dao.QuestionDao;
import ru.otus.homework.model.Question;

import java.io.IOException;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> getAllQuestions() {
        List<Question> questions = null;
        try {
            questions = questionDao.getAllQuestions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
