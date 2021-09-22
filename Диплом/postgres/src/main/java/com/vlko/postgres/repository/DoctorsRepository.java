package com.vlko.postgres.repository;

import com.vlko.postgres.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorsRepository extends JpaRepository<DoctorEntity, Long> {
    DoctorEntity findByName(String name);
    DoctorEntity findBySpecification(String specification);
}
