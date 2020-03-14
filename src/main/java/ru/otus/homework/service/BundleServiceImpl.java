package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ResourceBundle;

@Service("bundleService")
public class BundleServiceImpl implements BundleService {

    private final ResourceBundle resourceBundle;

    public BundleServiceImpl(@Qualifier("bundle") ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}
