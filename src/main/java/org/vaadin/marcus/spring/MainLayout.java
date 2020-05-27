package org.vaadin.marcus.spring;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route("")
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@Component
public class MainLayout extends AppLayout {
    private Button btn_login;
    private Button btn_logout;
    private Button btn_message;

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainLayout(@Autowired GuestApplication service) {
        createHeader();

    }
    private void createHeader() {
        H1 logo = new H1("Welcome to Israeli Football Association ");
        logo.addClassName("logo");
        btn_login=new Button("login");
        btn_logout=new Button("logout");
        btn_message=new Button("message");
        btn_logout.setVisible(false);
        btn_message.setVisible(false);
        btn_message.addClickListener
                (click ->UI.getCurrent().navigate("message"));

        btn_logout.addClickListener(buttonClickEvent -> {
            VaadinSession.setCurrent(null);
            btn_logout.setVisible(false);
            btn_login.setVisible(true);
            btn_message.setVisible(false);
            UI.getCurrent().navigate("");
        });

        btn_login.addClickListener(buttonClickEvent ->
                UI.getCurrent().navigate("login"));
        HorizontalLayout header = new HorizontalLayout(logo, btn_login,btn_message,btn_logout);
        header.addClassName("header");
        header.setWidth("100%");
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        addToNavbar(header);
    }
    public Button getBtn_login() {
        return btn_login;
    }

    public Button getBtn_logout() {
        return btn_logout;
    }

    public Button getBtn_message() {
        return btn_message;
    }

}
