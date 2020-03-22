package ru.otus.homework.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.exeption.QuestionLoadingException;
import ru.otus.homework.model.Question;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Test class QuestionDaoCSV")
class QuestionDaoCSVTest {

    @Autowired
    private QuestionDao questionDao;

    @Configuration
    @ComponentScan(basePackages = {"ru.otus.homework.config", "ru.otus.homework.dao"})
    static class QuestionDaoTestConfiguration {
    }

    @Test
    void checkSizeOfSource() throws QuestionLoadingException {
        List<Question> questions = questionDao.getAllQuestions();
        assertEquals(5, questions.size());
    }
}