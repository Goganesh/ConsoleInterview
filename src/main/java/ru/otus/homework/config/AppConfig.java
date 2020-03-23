package ru.otus.homework.config;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.homework.config.props.LocalizationConfig;
import ru.otus.homework.dao.QuestionDao;
import ru.otus.homework.dao.QuestionDaoCSV;
import ru.otus.homework.service.BundleService;
import ru.otus.homework.service.BundleServiceImpl;

import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
@PropertySource("classpath:application.yml")
@AllArgsConstructor
public class AppConfig {

    private final LocalizationConfig localizationConfig;

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean("bundleService")
    @ConditionalOnClass(name = "ru.otus.homework.config.props.LocalizationConfig")
    @ConditionalOnProperty("locale.defaultLocale")
    public BundleService getBundleService(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/bundle", Locale.forLanguageTag(localizationConfig.getDefaultLocale()), new EncodingControl("windows-1251"));
        return new BundleServiceImpl(resourceBundle);
    }

    @Bean("questionDao")
    public QuestionDao getQuestionDaoCSV(){
        String locale = localizationConfig.getDefaultLocale();
        String classpath = localizationConfig.getLocaleClasspath().get(locale);
        ClassPathResource classPathResource = new ClassPathResource(classpath);
        return new QuestionDaoCSV(classPathResource);
    }
}
