package ru.otus.homework.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.model.Question;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("QuestionDaoCSV")
class QuestionDaoCSVTest {
    private QuestionDao questionDao;

    @BeforeEach
    void beforeEach(){
        questionDao = new QuestionDaoCSV();
    }

    @AfterEach
    void afterEach(){
        questionDao = null;
    }

    @Test
    void checkSizeOfSource() throws IOException {
        List<Question> questions = questionDao.getAllQuestions();
        assertEquals(5, questions.size());
    }
}