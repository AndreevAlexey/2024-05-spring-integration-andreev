package ru.otus.hw14.service.release;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.hw14.model.Program;
import ru.otus.hw14.model.Release;

@Slf4j
@Service("releaseService")
public class ReleaseServiceImpl implements ReleaseService {

    public static final String CODE_POSTFIX = "_release";

    public static final String PACK_JAR = "jar";

    public static final String PACK_WAR = "war";

    public static final int DELAY_TIME = 1000;


    @Override
    public Release createPackageJar(Program program) {
        log.info("release program jar: {}", program);
        delay();
        String code = program.getCode() + CODE_POSTFIX;
        return
                new Release(code, PACK_JAR, program);
    }

    @Override
    public Release createPackageWar(Program program) {
        log.info("release program war: {}", program);
        delay();
        String code = program.getCode() + CODE_POSTFIX;
        return
                new Release(code, PACK_WAR, program);
    }

    private void delay() {
        try {
            log.info("program release delay start");
            Thread.sleep(DELAY_TIME);
            log.info("program release delay end");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
