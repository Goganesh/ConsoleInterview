package ru.otus.homework.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.homework.exeption.QuestionLoadingException;
import ru.otus.homework.model.Question;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DisplayName("Test class QuestionDaoCSV")
class QuestionDaoCSVTest {

    @Autowired
    private QuestionDao questionDao;

    @Configuration
    @ComponentScan(basePackages = {"ru.otus.homework.config"})
    static class QuestionDaoTestConfiguration {
    }

    @Test
    void checkSizeOfSource() throws QuestionLoadingException {
        List<Question> questions = questionDao.getAllQuestions();
        assertEquals(5, questions.size());
    }
}