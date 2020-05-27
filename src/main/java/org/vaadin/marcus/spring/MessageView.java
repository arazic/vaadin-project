package org.vaadin.marcus.spring;


import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Route(value = "message", layout = MainLayout.class)
public class MessageView extends VerticalLayout {

    public MessageView(@Autowired GuestApplication service) {
        VerticalLayout msgTable = new VerticalLayout();
        add(new H1("Msg"),
                msgTable);
        String userName= (String) VaadinSession.getCurrent().getAttribute("user");
        ArrayList<String> msgList=service.getMsg(userName);
        for (String msg:msgList) {
            msgTable.add(new H3(msg));
        }
    }

}
