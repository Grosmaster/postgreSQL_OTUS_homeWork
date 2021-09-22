package com.vlko.ui.services;

import com.vlko.ui.dto.Doctor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class DoctorService {
    private final RestTemplate restTemplate;

    public DoctorService() {
        restTemplate = new RestTemplate();
    }

    public List<Doctor> getAll() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject("http://localhost:8090/doctor", Doctor[].class)).clone());
    }
}
