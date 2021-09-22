package com.vlko.postgres.controller;

import com.vlko.postgres.entity.DoctorEntity;
import com.vlko.postgres.repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    private DoctorsRepository doctorsRepository;

    @GetMapping
    public Object findAll() {
        return doctorsRepository.findAll();
    }

    @DeleteMapping()
    public void deleteById(@RequestParam Long id) {
        doctorsRepository.deleteById(id);
    }

    @PostMapping
    public void save(@RequestBody DoctorEntity doctorEntity) {
        doctorsRepository.save(doctorEntity);
    }
}
