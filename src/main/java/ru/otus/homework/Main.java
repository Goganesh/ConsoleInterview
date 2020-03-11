package ru.otus.homework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.controller.TestController;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

@Configuration
@ComponentScan

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        TestController testController = context.getBean(TestController.class);
        testController.initTest();
    }
}
