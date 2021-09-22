package com.vlko.postgres.controller;

import com.vlko.postgres.entity.AppealEntity;
import com.vlko.postgres.repository.AppealRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("appeal")
@Slf4j
public class AppealController {
    @Autowired
    private AppealRepository appealRepository;

    @GetMapping
    public List<AppealEntity> findAll() {
        return appealRepository.findAll();
    }

    @DeleteMapping()
    public void deleteById(@PathVariable Long id) {
        appealRepository.deleteById(id);
    }

    @PostMapping
    public void save(@RequestBody AppealEntity appealEntity) {
        appealEntity.setCreateDate(Instant.now());
        appealRepository.save(appealEntity);
    }

}
