package ru.otus.homework.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.config.AppConfig;
import ru.otus.homework.exeption.QuestionLoadingException;
import ru.otus.homework.model.Question;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test class QuestionDaoCSV")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class, QuestionDaoCSV.class})
class QuestionDaoCSVTest {

    @Autowired
    private QuestionDao questionDao;

    @Test
    void checkSizeOfSource() throws QuestionLoadingException {
        List<Question> questions = questionDao.getAllQuestions();
        assertEquals(5, questions.size());
    }
}