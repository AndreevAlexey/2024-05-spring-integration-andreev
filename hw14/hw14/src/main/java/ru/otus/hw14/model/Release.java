package ru.otus.hw14.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Release {

    private String code;

    private String packType;

    private Program program;
}
