package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public User saveUser(String name) {
        return new User(name);
    }
}
