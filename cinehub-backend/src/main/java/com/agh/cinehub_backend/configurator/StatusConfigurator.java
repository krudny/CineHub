package com.agh.cinehub_backend.configurator;

import com.agh.cinehub_backend.model.Genre;
import com.agh.cinehub_backend.model.Status;
import com.agh.cinehub_backend.repository.StatusRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatusConfigurator {
    private final StatusRepository statusRepository;

    public StatusConfigurator(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @PostConstruct
    public void init() {
        if (statusRepository.count() == 0) {
            createStatus("Pending");
        }
    }

    private void createStatus(String statusName) {
        Status status = new Status();
        status.setName(statusName);
        statusRepository.save(status);
    }
}
