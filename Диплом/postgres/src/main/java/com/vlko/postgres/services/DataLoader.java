package com.vlko.postgres.services;

import com.vlko.postgres.entity.AppealEntity;
import com.vlko.postgres.entity.DoctorEntity;
import com.vlko.postgres.entity.PatientEntity;
import com.vlko.postgres.repository.AppealRepository;
import com.vlko.postgres.repository.DoctorsRepository;
import com.vlko.postgres.repository.PatientsRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DataLoader implements ApplicationRunner {
    private final AppealRepository appealRepository;
    private final PatientsRepository patientsRepository;
    private final DoctorsRepository doctorsRepository;

    public DataLoader(AppealRepository appealRepository, PatientsRepository patientsRepository, DoctorsRepository doctorsRepository) {
        this.appealRepository = appealRepository;
        this.patientsRepository = patientsRepository;
        this.doctorsRepository = doctorsRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setName("namePatient");
        patientEntity.setPhone("89033359770");
        patientsRepository.save(patientEntity);

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setName("nameDoctor");
        doctorEntity.setSpecification("testSpecification");
        doctorsRepository.save(doctorEntity);

        AppealEntity appealEntity = new AppealEntity();
        appealEntity.setDoctorEntity(doctorEntity);
        appealEntity.setPatientEntity(patientEntity);
        appealEntity.setCreateDate(Instant.now());
        appealEntity.setDescription("Test appeal");
        appealEntity.setStatus("active");
        appealEntity.setPriority(1);
        appealRepository.save(appealEntity);
    }
}
