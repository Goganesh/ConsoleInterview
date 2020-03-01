package ru.otus.homework.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import ru.otus.homework.model.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoCSV implements QuestionDao {
    @Getter
    @Setter
    private static @Value("dataSource") Resource dataSource;

    public List<Question> getAllQuestions() throws IOException {
        List<Question> questions = new ArrayList<>();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        InputStream in = dataSource.getInputStream();
        Reader targetReader = new InputStreamReader(in);

        ICsvBeanReader beanReader = new CsvBeanReader(targetReader, CsvPreference.STANDARD_PREFERENCE);
        final String[] header = beanReader.getHeader(true);
        final CellProcessor[] processors = getProcessors();

        Question question;
        while ((question = beanReader.read(Question.class, header, processors)) != null) {
            // process question
            questions.add(question);
        }

        return questions;
    }

    private static CellProcessor[] getProcessors(){
        return new CellProcessor[]{
                new ParseInt(),
                new NotNull()
        };
    }
}
