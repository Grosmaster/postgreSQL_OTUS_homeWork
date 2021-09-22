package com.vlko.postgres.controller;


import com.vlko.postgres.entity.PatientEntity;
import com.vlko.postgres.repository.PatientsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("patient")
@Slf4j
public class PatientsController {
    @Autowired
    private PatientsRepository patientsRepository;

    @GetMapping
    public List<PatientEntity> findAll() {
        return patientsRepository.findAll();
    }

    @DeleteMapping()
    public void deleteById(@PathVariable Long id) {
        patientsRepository.deleteById(id);
    }

    @PostMapping
    public void save(@RequestBody PatientEntity patientEntity) {
        patientsRepository.save(patientEntity);
    }

}
