package com.vlko.ui.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        Text text = new Text("Please use /patient or /doctor or /appeal for get data");
        add(text);
    }
}