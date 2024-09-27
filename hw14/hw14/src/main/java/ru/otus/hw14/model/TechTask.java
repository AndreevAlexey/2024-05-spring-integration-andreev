package ru.otus.hw14.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TechTask {

    private String title;

    private String text;
}
