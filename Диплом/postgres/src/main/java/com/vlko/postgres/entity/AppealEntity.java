package com.vlko.postgres.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "appeals")
public class AppealEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Instant createDate;
    private String description;
    private Integer priority;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private DoctorEntity doctorEntity;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private PatientEntity patientEntity;
    private String status;
}
