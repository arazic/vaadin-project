package org.vaadin.marcus.spring;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

public class HomeView extends VerticalLayout {
    public HomeView(@Autowired GuestApplication service){

    }
}
