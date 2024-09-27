package ru.otus.hw14.service.program;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.hw14.model.Program;
import ru.otus.hw14.model.TechTask;

import java.time.LocalDateTime;

@Slf4j
@Service("programService")
public class ProgramServiceImpl implements ProgramService {

    public static final String CODE_PREFIX = "Task_";

    public static final int DELAY_TIME = 2000;


    @Override
    public Program createCode(TechTask techTask) {
        log.info("program create code task: {}", techTask);
        delay();
        String code = CODE_PREFIX + techTask.getTitle();
        return
                new Program(code, techTask, LocalDateTime.now());
    }

    private void delay() {
        try {
            log.info("program create code delay start");
            Thread.sleep(DELAY_TIME);
            log.info("program create code delay end");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
