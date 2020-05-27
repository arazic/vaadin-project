package org.vaadin.marcus.spring;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@Route(value = "login", layout = MainLayout.class)
public class LoginView extends VerticalLayout {

    @Autowired
    private MainLayout mainLayout;

       public LoginView(@Autowired GuestApplication service ) {
        FormLayout loginForm=new FormLayout();
        TextField userName=new TextField("Username");
        PasswordField passwordField=new PasswordField("password");
        Button btn_Enter=new Button("Enter");
        loginForm.add(userName,passwordField,btn_Enter);
        add(loginForm);
        addClassName("centered-content");
        btn_Enter.addClickListener( e -> {
            String[] user=service.login(userName.getValue(),passwordField.getValue());
            String respond=user[0];
            if(respond.equalsIgnoreCase("respond")) {
                String userType = user[1];
                String name = user[3];
                VaadinSession.getCurrent().setAttribute("user", userName.getValue());
                VaadinSession.getCurrent().setAttribute("name", name);
                VaadinSession.getCurrent().setAttribute("type", userType);
                UI.getCurrent().navigate(userType);
                mainLayout.getBtn_logout().setVisible(true);
                mainLayout.getBtn_message().setVisible(true);
                mainLayout.getBtn_login().setVisible(false);
                passwordField.clear();
                userName.clear();
            }
            else if(respond.equalsIgnoreCase("Fail")){
                Notification.show("ERROR "+user[1]);
            }
        });
    }
}
