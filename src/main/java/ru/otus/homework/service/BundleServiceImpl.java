package ru.otus.homework.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ResourceBundle;

@Service("bundleService")
@AllArgsConstructor
public class BundleServiceImpl implements BundleService {

    private final ResourceBundle resourceBundle;

    @Override
    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}
