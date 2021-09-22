package com.vlko.ui.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class Appeal {
    private Long id;
    private Instant createDate;
    private String description;
    private Integer priority;
    private Doctor doctorEntity;
    private Patient patientEntity;
    private String status;
}
