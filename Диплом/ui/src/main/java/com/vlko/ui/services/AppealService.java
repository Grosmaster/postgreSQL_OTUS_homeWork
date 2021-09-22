package com.vlko.ui.services;

import com.vlko.ui.dto.Appeal;
import com.vlko.ui.dto.Doctor;
import com.vlko.ui.dto.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AppealService {

    private final RestTemplate restTemplate;

    public AppealService() {
        restTemplate = new RestTemplate();
    }

    public List<Appeal> getAll() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject("http://localhost:8090/appeal", Appeal[].class)).clone());
    }

    public void save(Appeal appeal) {
        log.info("save");
    }

    public void delete(Appeal appeal) {
        log.info("rm");
    }
}
