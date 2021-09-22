package com.vlko.ui.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vlko.ui.dto.Doctor;
import com.vlko.ui.services.DoctorService;

import java.util.List;
import java.util.stream.Collectors;

@Route("doctor")
public class DoctorView extends VerticalLayout{

    private DoctorService doctorService;
    private Grid<Doctor> grid;

    public DoctorView() {
        doctorService = new DoctorService();
        grid = new Grid<>(Doctor.class);
        add(grid);
        List<Doctor> doctors = doctorService.getAll();
        grid.setItems(doctors);
    }
}
