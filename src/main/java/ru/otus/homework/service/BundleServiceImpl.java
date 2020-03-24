package ru.otus.homework.service;

import lombok.AllArgsConstructor;
import java.util.ResourceBundle;

@AllArgsConstructor
public class BundleServiceImpl implements BundleService {

    private final ResourceBundle resourceBundle;

    @Override
    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}
