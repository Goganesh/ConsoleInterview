package ru.otus.homework.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.homework.config.yml.YmlConfig;
import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
@PropertySource("classpath:application.yml")
public class AppConfig {

    private YmlConfig ymlConfig;

    public AppConfig(YmlConfig ymlConfig) {
        this.ymlConfig = ymlConfig;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean("bundle")
    @ConditionalOnProperty("locale.prefix")
    public ResourceBundle getResourceBundle(){
        return ResourceBundle.getBundle("i18n/bundle", Locale.forLanguageTag(ymlConfig.getLocale().getPrefix()), new EncodingControl("windows-1251"));
    }

    @Bean("dataSource")
    @ConditionalOnProperty("locale.csvClasspath")
    public Resource getResourceForCSV(){
        return new ClassPathResource(ymlConfig.getLocale().getCsvClasspath());
    }
}
