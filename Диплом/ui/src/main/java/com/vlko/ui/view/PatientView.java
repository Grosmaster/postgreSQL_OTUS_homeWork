package com.vlko.ui.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vlko.ui.dto.Doctor;
import com.vlko.ui.dto.Patient;
import com.vlko.ui.services.DoctorService;
import com.vlko.ui.services.PatientService;

import java.util.List;

@Route("patient")
public class PatientView extends VerticalLayout {

    private PatientService patientService;
    private Grid<Patient> grid;

    public PatientView() {
        patientService = new PatientService();
        grid = new Grid<>(Patient.class);
        add(grid);
        List<Patient> patients = patientService.getAll();
        grid.setItems(patients);
    }
}