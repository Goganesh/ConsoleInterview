package ru.otus.homework.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import ru.otus.homework.exeption.QuestionException;
import ru.otus.homework.model.Question;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("QuestionDaoCSV")
class QuestionDaoCSVTest {

    @Autowired
    private QuestionDao questionDao;

    public QuestionDaoCSVTest(@Qualifier("questionDao") QuestionDaoCSV questionDao){
        this.questionDao = questionDao;
    }

    @Test
    void checkSizeOfSource() throws QuestionException {
        List<Question> questions = questionDao.getAllQuestions();
        assertEquals(5, questions.size());
    }
}