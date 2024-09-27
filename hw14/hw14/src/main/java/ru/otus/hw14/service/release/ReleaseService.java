package ru.otus.hw14.service.release;

import ru.otus.hw14.model.Program;
import ru.otus.hw14.model.Release;

public interface ReleaseService {

    Release createPackageJar(Program program);

    Release createPackageWar(Program program);
}
