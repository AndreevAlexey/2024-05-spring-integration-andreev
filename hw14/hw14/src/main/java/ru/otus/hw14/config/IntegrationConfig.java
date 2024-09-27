package ru.otus.hw14.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.PollerSpec;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.hw14.model.Program;

@Slf4j
@Configuration
public class IntegrationConfig {


    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerSpec poller() {
        return
                Pollers
                        .fixedRate(100)
                        .maxMessagesPerPoll(2);
    }

    @Bean
    public MessageChannelSpec<?, ?> techTasksChanel() {
        return
                MessageChannels.queue(10);
    }

    @Bean
    public MessageChannelSpec<?, ?> releasesChanel() {
        return
                MessageChannels.publishSubscribe();
    }

    @Bean
    public IntegrationFlow techTaskFlow() {
        return
                IntegrationFlow
                        .from(techTasksChanel())
                        .split()
                        .handle("programService", "createCode")
                        .<Program, Boolean>route(program -> program.getTechTask().getText().contains("jar"),
                                mapping -> mapping
                                        .subFlowMapping(true, subflow -> subflow
                                                .handle("releaseService", "createPackageJar"))
                                        .subFlowMapping(false, subflow -> subflow
                                                .handle("releaseService", "createPackageWar")
                                        )
                        )
                        .aggregate()
                        .channel(releasesChanel())
                        .get();
    }

    @Bean
    public IntegrationFlow errorFlow() {
        return
                f -> f.handle((m) -> log.error(m.getPayload().toString()));
    }
}
