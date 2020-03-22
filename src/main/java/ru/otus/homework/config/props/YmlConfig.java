package ru.otus.homework.config.props;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
@AllArgsConstructor
public class YmlConfig {
    private final LocalizationConfig localizationConfig;
}
