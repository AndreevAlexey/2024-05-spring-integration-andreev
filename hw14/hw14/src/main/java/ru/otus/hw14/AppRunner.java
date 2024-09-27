package ru.otus.hw14;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.hw14.config.DevelopGateway;
import ru.otus.hw14.model.Release;
import ru.otus.hw14.model.TechTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;


@Slf4j
@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    public static final int DELAY_TIME = 7000;

    private static final String[] TEXT = {"AAA jar", "BBB war", "CCC jar", "DDD war", "EEE jar", "FFF war"};

    private final DevelopGateway developGateway;

    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {

        ForkJoinPool pool = ForkJoinPool.commonPool();
        for (int i = 0; i < 5; i++) {
            int num = i + 1;
            pool.execute(() -> {
                Collection<TechTask> items = generateTechTaskItems();
                log.info("{} generated tasks: {}", num,
                        items
                        .stream()
                        .map(TechTask::getTitle)
                        .collect(Collectors.joining(", ")));
                Collection<Release> releases = developGateway.process(items);
                if (releases != null) {
                    log.info("! released tasks: {}",
                            releases
                                    .stream()
                                    .map(Release::getCode)
                                    .collect(Collectors.joining(", ")));
                }
            });
            delay();
        }
    }

    private Collection<TechTask> generateTechTaskItems() {
        List<TechTask> items = new ArrayList<>();
        for (int i = 0; i < random.nextInt(1, 5); i++) {
            items.add(generateTechTaskItem(i));
        }
        return items;
    }

    private TechTask generateTechTaskItem(int seqNum) {
        int i = random.nextInt(0, TEXT.length);
        return
                new TechTask("title_" + seqNum + i, TEXT[i]);
    }

    private void delay() {
        try {
            Thread.sleep(DELAY_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
