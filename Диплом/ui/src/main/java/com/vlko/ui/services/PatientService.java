package com.vlko.ui.services;

import com.vlko.ui.dto.Doctor;
import com.vlko.ui.dto.Patient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PatientService {
    private final RestTemplate restTemplate;

    public PatientService() {
        restTemplate = new RestTemplate();
    }

    public List<Patient> getAll() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject("http://localhost:8090/patient", Patient[].class)).clone());
    }
}
