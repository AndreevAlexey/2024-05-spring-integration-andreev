package ru.otus.hw14.config;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.hw14.model.Release;
import ru.otus.hw14.model.TechTask;

import java.util.Collection;

@MessagingGateway
public interface DevelopGateway {

    @Gateway(requestChannel = "techTasksChanel", replyChannel = "releasesChanel")
    Collection<Release> process(Collection<TechTask> tasks);
}
