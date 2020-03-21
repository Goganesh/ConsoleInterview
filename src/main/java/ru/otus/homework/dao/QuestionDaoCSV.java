package ru.otus.homework.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import ru.otus.homework.exeption.QuestionLoadingException;
import ru.otus.homework.model.Question;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository("questionDao")
public class QuestionDaoCSV implements QuestionDao {
    @Autowired
    private final Resource dataSource;

    private static Logger logger = LoggerFactory.getLogger(QuestionDaoCSV.class);

    public QuestionDaoCSV(@Qualifier("dataSource") Resource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Question> getAllQuestions() throws QuestionLoadingException {
        List<Question> questions = new ArrayList<>();
        try {
            InputStream in = dataSource.getInputStream();
            Reader targetReader = new InputStreamReader(in);

            ICsvBeanReader beanReader = new CsvBeanReader(targetReader, CsvPreference.STANDARD_PREFERENCE);
            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();

            Question question;
            try {
                while ((question = beanReader.read(Question.class, header, processors)) != null) {
                    // process question
                    logger.debug(question.toString());
                    questions.add(question);

                }
            } finally {
                if (beanReader != null) {
                    beanReader.close();
                }
            }
        } catch (Exception ex) {
            throw new QuestionLoadingException(ex);
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
