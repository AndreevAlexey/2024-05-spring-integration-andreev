package ru.otus.hw14.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Program {

    private String code;

    private TechTask techTask;

    private LocalDateTime endDate;
}
