package ru.otus.homework.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.config.AppConfig;
import ru.otus.homework.exeption.QuestionException;
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
    void checkSizeOfSource() throws QuestionException {
        List<Question> questions = questionDao.getAllQuestions();
        assertEquals(5, questions.size());
    }
}