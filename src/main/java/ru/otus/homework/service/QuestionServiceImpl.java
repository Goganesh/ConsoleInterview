package ru.otus.homework.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.QuestionDao;
import ru.otus.homework.dao.QuestionDaoCSV;
import ru.otus.homework.exeption.QuestionLoadingException;
import ru.otus.homework.model.Question;
import java.util.List;


@Service("questionService")
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    private static Logger logger = LoggerFactory.getLogger(QuestionDaoCSV.class);

    @Override
    public List<Question> getAllQuestions() {
        List<Question> questions = null;
        try {
            questions = questionDao.getAllQuestions();
        } catch (QuestionLoadingException ex) {
            logger.error(ex.getMessage());
        }
        return questions;
    }
}
