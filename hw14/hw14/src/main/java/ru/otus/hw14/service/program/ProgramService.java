package ru.otus.hw14.service.program;

import ru.otus.hw14.model.Program;
import ru.otus.hw14.model.TechTask;

public interface ProgramService {

    Program createCode(TechTask techTask);
}
