package com.vlko.ui.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vlko.ui.dto.AppealFront;
import com.vlko.ui.services.AppealService;

import java.util.List;
import java.util.stream.Collectors;

@Route("appeal")
public class AppealView extends VerticalLayout {

    private AppealService appealService;
    private Grid<AppealFront> grid;

    public AppealView() {
        appealService = new AppealService();
        grid = new Grid<>(AppealFront.class);
        add(grid);
        List<AppealFront> appeals = appealService.getAll().stream().map(AppealFront::new).collect(Collectors.toList());
        grid.setItems(appeals);
    }
}
