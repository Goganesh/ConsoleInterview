package ru.otus.homework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.homework.config.yml.YmlConfig;
import ru.otus.homework.controller.TestController;

import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
@PropertySource("classpath:application.yml")
public class AppConfig {

    private YmlConfig ymlConfig;

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    public AppConfig(YmlConfig ymlConfig) {
        this.ymlConfig = ymlConfig;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean("bundle")
    @ConditionalOnProperty("locale.defaultLocale")
    public ResourceBundle getResourceBundle(){
        return ResourceBundle.getBundle("i18n/bundle", Locale.forLanguageTag(ymlConfig.getLocale().getDefaultLocale()), new EncodingControl("windows-1251"));
    }

    @Bean("dataSource")
    public Resource getResourceForCSV(){
        String locale = ymlConfig.getLocale().getDefaultLocale();
        String classpath = ymlConfig.getLocale().getLocaleClasspath().get(locale);
        return new ClassPathResource(classpath);
    }

    @Bean
    @ConditionalOnClass(name = "ru.otus.homework.controller.TestController")
    public CommandLineRunner starter(TestController testController){
        logger.info("Start student testing");
        return args ->  testController.initTest();
    }
}
