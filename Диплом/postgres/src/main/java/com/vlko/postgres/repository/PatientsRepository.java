package com.vlko.postgres.repository;

import com.vlko.postgres.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsRepository extends JpaRepository<PatientEntity, Long> {
    PatientEntity findByName(String name);
}
