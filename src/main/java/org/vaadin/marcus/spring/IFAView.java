package org.vaadin.marcus.spring;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "IFA", layout = MainLayout.class)
public class IFAView extends VerticalLayout {
    HorizontalLayout horizontal1,horizontal2;
    public IFAView(@Autowired GuestApplication service){
        Button btn_CreateTeam=new Button("Create Team");
        Button btn_Policy=new Button("Setting Policy");
        horizontal1=new HorizontalLayout(btn_CreateTeam,btn_Policy);
        horizontal1.addClassName("ifa-div");
        Button btn_existsOwner=new Button("existsOwner");
        Button btn_newOwner=new Button("New Owner");
        horizontal2=new HorizontalLayout(btn_existsOwner,btn_newOwner);
        horizontal2.addClassName("ifa-div");
        btn_CreateTeam.addClickListener(e ->{
            removeAll();
            add(horizontal2);
        });
        add(horizontal1);
        addClassName("ifa-view");
        UI.getCurrent();
    }
}
