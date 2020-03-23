package ru.otus.homework.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@EnableConfigurationProperties
@ConfigurationProperties("locale")
@Data
public class LocalizationConfig {
    private String defaultLocale;
    private Map<String, String> localeClasspath;
}
