package ru.otus.homework.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean("bundle")
    public ResourceBundle getResourceBundle(@Value("${locale.prefix}") String localePrefix){
        return ResourceBundle.getBundle("i18n/bundle", Locale.forLanguageTag(localePrefix), new EncodingControl());
    }

    @Bean("dataSource")
    public Resource getResourceForCSV(@Qualifier("bundle") ResourceBundle bundle){
        return new ClassPathResource(bundle.getString("csv.classpath"));
    }
}
