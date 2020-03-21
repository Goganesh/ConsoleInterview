package ru.otus.homework.config.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("locale")
@Data
public class Locale {
    private String defaultLocale;
    private Map<String, String> localeClasspath;
}
