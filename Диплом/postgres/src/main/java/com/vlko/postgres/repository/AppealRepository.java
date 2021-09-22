package com.vlko.postgres.repository;

import com.vlko.postgres.entity.AppealEntity;
import com.vlko.postgres.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppealRepository extends JpaRepository<AppealEntity, Long> {

}
