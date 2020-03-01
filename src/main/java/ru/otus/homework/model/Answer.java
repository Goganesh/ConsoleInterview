package ru.otus.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {
    private int id;
    private Question question;
    private String answer;
    private User user;
}
