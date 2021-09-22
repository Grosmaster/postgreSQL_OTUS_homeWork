package com.vlko.ui.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class AppealFront {
    private Long id;
    private Instant createDate;
    private String description;
    private Integer priority;
    private String doctor;
    private String patient;
    private String status;

    public AppealFront(Appeal appeal) {
        this.id = appeal.getId();
        this.createDate = appeal.getCreateDate();
        this.description = appeal.getDescription();
        this.priority = appeal.getPriority();
        this.doctor = appeal.getDoctorEntity().getName();
        this.patient = appeal.getPatientEntity().getName();
        this.status = appeal.getStatus();
    }
}
