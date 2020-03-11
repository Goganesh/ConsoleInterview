package ru.otus.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.QuestionDao;
import ru.otus.homework.dao.QuestionDaoCSV;
import ru.otus.homework.exeption.QuestionException;
import ru.otus.homework.model.Question;
import java.util.List;


@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private final QuestionDao questionDao;

    private static Logger logger = LoggerFactory.getLogger(QuestionDaoCSV.class);

    public QuestionServiceImpl(QuestionDaoCSV questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> getAllQuestions() {
        List<Question> questions = null;
        try {
            questions = questionDao.getAllQuestions();
        } catch (QuestionException ex) {
            logger.error(ex.getMessage());
        }
        return questions;
    }
}
