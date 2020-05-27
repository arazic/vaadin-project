package org.vaadin.marcus.spring;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Route(value = "IFA", layout = MainLayout.class)
public class IFAView extends VerticalLayout {
    HorizontalLayout mainIFA, createTeam, createPolicy;
    VerticalLayout createTeamAddOwner, createTeamExistUser;
    VerticalLayout calculatePointsAndPlace, calculateInlayPolicy;

    public IFAView(@Autowired GuestApplication service){
        Button btn_CreateTeam=new Button("Create Team");
        Button btn_Policy=new Button("Setting Policy");
        mainIFA =new HorizontalLayout(btn_CreateTeam,btn_Policy);
        mainIFA.addClassName("ifa-div");

        add(mainIFA);

        Button btn_existsOwner=new Button("existsOwner");
        Button btn_newOwner=new Button("New Owner");
        createTeam =new HorizontalLayout(btn_existsOwner,btn_newOwner);
        createTeam.addClassName("ifa-div");
        createTeam.addClassName("hidden");
        createTeamExistUser= createTeamExistUserLayout(service);
        createTeamAddOwner= createTeamAddOwnerLayout(service);
        createTeamExistUser.setAlignItems(Alignment.CENTER);
        createTeamAddOwner.setAlignItems(Alignment.CENTER);
        createTeamExistUser.addClassName("hidden");
        createTeamAddOwner.addClassName("hidden");

        Button btn_calculatePointsAndPlace=new Button("Calculate Points And Place");
        Button btn_calculateInlayPolicy =new Button("Calculate Inlay Policy");
        createPolicy =new HorizontalLayout(btn_calculatePointsAndPlace,btn_calculateInlayPolicy);
        createPolicy.addClassName("ifa-div");
        createPolicy.addClassName("hidden");

        calculatePointsAndPlace= calculatePointsAndPlaceLayout(service);
        calculateInlayPolicy= calculateInlayPolicyLayout(service);
        calculatePointsAndPlace.setAlignItems(Alignment.CENTER);
        calculateInlayPolicy.setAlignItems(Alignment.CENTER);

        calculatePointsAndPlace.addClassName("hidden");
        calculateInlayPolicy.addClassName("hidden");

        btn_CreateTeam.addClickListener(e ->{
            mainIFA.addClassName("hidden");
            add(createTeam);
            createTeam.removeClassName("hidden");
        });

        btn_Policy.addClickListener(e ->{
            mainIFA.addClassName("hidden");
            add(createPolicy);
            createPolicy.removeClassName("hidden");
        });

        btn_newOwner.addClickListener(e ->{
            removeAll();
            add(createTeamAddOwner);
            createTeamAddOwner.removeClassName("hidden");
            createTeamAddOwner.setAlignItems(Alignment.CENTER);
        });

        btn_existsOwner.addClickListener(e ->{
            removeAll();
            add(createTeamExistUser);
            createTeamExistUser.removeClassName("hidden");
            createTeamExistUser.setAlignItems(Alignment.CENTER);
        });

        btn_calculateInlayPolicy.addClickListener(e ->{
            removeAll();
            add(calculateInlayPolicy);
            calculateInlayPolicy.removeClassName("hidden");
            calculateInlayPolicy.setAlignItems(Alignment.CENTER);
        });

        btn_calculatePointsAndPlace.addClickListener(e ->{
            removeAll();
            add(calculatePointsAndPlace);
            calculatePointsAndPlace.removeClassName("hidden");
            calculatePointsAndPlace.setAlignItems(Alignment.CENTER);
        });

        UI.getCurrent();
    }

    private VerticalLayout calculateInlayPolicyLayout(GuestApplication service) {
        VerticalLayout verticalLayout= new VerticalLayout();
        TextField sessionName=new TextField("Session Name");
        TextField year=new TextField("League year");
        String[] policy= service.getCalculatorPolicy();

        //TODO: add combobox
        ComboBox<String> labelComboBox = new ComboBox<>();
        labelComboBox.setItems("Option one", "Option two");
        labelComboBox.setLabel("Label");
        labelComboBox.setVisible(true);

        Button btn_set =new Button("Set",buttonClickEvent -> {

            String[] ans=service.setLegueCalculator(sessionName.getValue(), Integer.parseInt(year.getValue()), policy[0]);
            if(ans!=null) {
                if (ans[0].equalsIgnoreCase("respond")) {
                    Notification.show("The action was successful");
                } else {
                    Notification.show(ans[1]);
                }
            }
            else
                Notification.show("could not add policy");

            add(mainIFA);
            calculateInlayPolicy.addClassName("hidden");
            mainIFA.removeClassName("hidden");
        });

        verticalLayout.add(sessionName,year,labelComboBox,btn_set);
        return verticalLayout;
    }

    private VerticalLayout calculatePointsAndPlaceLayout(GuestApplication service) {
        TextField leagueName=new TextField("League Name");
        TextField year=new TextField("League year");
        String[] policy= service.getGamesScedualsPolicy();

        //TODO: add combobox
        ComboBox<String> labelComboBox = new ComboBox<>();
        labelComboBox.setItems("Option one", "Option two");
        labelComboBox.setLabel("Label");
        labelComboBox.setVisible(true);

        Button btn_set =new Button("Set",buttonClickEvent -> {
            String[] ans=service.setGamesSceduals(leagueName.getValue(), Integer.parseInt(year.getValue()), policy[0]);
            if(ans!=null) {
                if (ans[0].equalsIgnoreCase("respond")) {
                    Notification.show("The action was successful");
                } else {
                    Notification.show(ans[1]);
                }
            }
            else
                Notification.show("could not add policy");

            add(mainIFA);
            calculatePointsAndPlace.addClassName("hidden");
            mainIFA.removeClassName("hidden");
        });
        return new VerticalLayout(leagueName,year,labelComboBox, btn_set);
    }

    private VerticalLayout createTeamExistUserLayout(GuestApplication service) {
        TextField ownerName=new TextField("Owner Name");
        TextField groupName=new TextField("Group Name");
        Button btn_set =new Button("Set",buttonClickEvent -> {

            String[] ans=service.addTeam(groupName.getValue(),ownerName.getValue());
            if(ans!=null) {
                if (ans[0].equalsIgnoreCase("respond")) {
                    Notification.show("group added");
                } else {
                    Notification.show(ans[1]);
                }
            }
            else
                Notification.show("could not add a group");

            add(mainIFA);
            createTeamExistUser.addClassName("hidden");
            mainIFA.removeClassName("hidden");
        });
        return new VerticalLayout(ownerName,groupName, btn_set);

    }

    private VerticalLayout createTeamAddOwnerLayout(GuestApplication service) {
        TextField ownerName=new TextField("Owner Name");
        TextField userName=new TextField("User Name");
        TextField password=new TextField("Password");
        Button btn_set =new Button("Set",buttonClickEvent -> {

            String[] ans=service.addOwner(ownerName.getValue(),userName.getValue(),password.getValue() );
            if(ans!=null) {
                if (ans[0].equalsIgnoreCase("respond")) {
                    Notification.show("Owner added");
                } else {
                    Notification.show(ans[1]);
                }
            }
            else
                Notification.show("could not add a Owner");

            add(mainIFA);
            createTeamAddOwner.addClassName("hidden");
            mainIFA.removeClassName("hidden");
        });
        return new VerticalLayout(ownerName,userName,password, btn_set);
    }
}
