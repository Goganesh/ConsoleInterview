package ru.otus.homework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.homework.controller.TestController;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final TestController testController;

    private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    public CommandLineAppStartupRunner(@Qualifier("testController") TestController testController) {
        this.testController = testController;
    }

    @Override
    public void run(String...args) throws Exception {
        logger.info("Start student testing");
        testController.initTest();
    }
}
