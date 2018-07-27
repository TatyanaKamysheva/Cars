package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.client.view.listboxes.OptionsDialogBox;
import com.cars.shared.models.UserLoginInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;

public class MainView extends Composite {

    static {
        Defaults.setDateFormat(null);
    }

    UserLoginInfo userLoginInfo;

    Grid grid = new Grid(1, 9);
    private GWTService restService = (GWTService) GWT.create(GWTService.class);
    private String roleEmployee = null;
    private Button managerButton = new Button("Managers");
    private Button customerButton = new Button("Customers");
    private Button purchasesButton = new Button("Purchases");
    private Button automobilesButton = new Button("Automobiles");
    private Button attributesButton = new Button("Attributes");
    private Button equipmentsButton = new Button("Equipments");
    private Button optionsButton = new Button("Options");
    private Button logoutButton = new Button("Log out");

    public MainView(UserLoginInfo userLoginInfo) {
        switch (userLoginInfo.getRole()) {
            case Seller:
                grid.setWidget(0, 0, customerButton);
                grid.setWidget(0, 1, purchasesButton);
                grid.setWidget(0, 2, automobilesButton);
                grid.setWidget(0, 3, logoutButton);
                grid.setWidget(0, 4, optionsButton);
                grid.setCellSpacing(30);
                RootPanel.get().add(grid);
                break;
            case Admin:
                grid.setWidget(0, 0, managerButton);
                grid.setWidget(0, 1, customerButton);
                grid.setWidget(0, 2, attributesButton);
                grid.setWidget(0, 3, automobilesButton);
                grid.setWidget(0, 4, equipmentsButton);
                grid.setWidget(0, 5, purchasesButton);
                grid.setWidget(0, 6, logoutButton);
                grid.setWidget(0, 7, optionsButton);
                grid.setCellSpacing(30);
                RootPanel.get().add(grid);
                break;

            case Supervisor:
                grid.setWidget(0, 1, attributesButton);
                grid.setWidget(0, 2, automobilesButton);
                grid.setWidget(0, 3, equipmentsButton);
                grid.setWidget(0, 6, logoutButton);
                grid.setWidget(0, 7, optionsButton);
                RootPanel.get().add(grid);
                break;
        }

        CustomerView customerView = new CustomerView();
        AutomobileView automobileView = new AutomobileView(userLoginInfo);
        PurchaseView purchaseView = new PurchaseView(userLoginInfo);
        AttributeView attributeView = new AttributeView();
        ManagerView managerView = new ManagerView(userLoginInfo);
        EquipmentView equipmentView = new EquipmentView();
        logoutButton.addClickHandler(event -> {
            //Cookies.removeCookie("sessionID");
            Cookies.removeCookie("sessionID");
            Cookies.removeCookie("login");
            Cookies.removeCookie("password");
            RootPanel.get().remove(grid);
            RootPanel.get().remove(automobileView.panel);
            RootPanel.get().remove(purchaseView.panel);
            RootPanel.get().remove(customerView.panel);
            RootPanel.get().remove(equipmentView.panel);
            RootPanel.get().remove(attributeView.panel);
            RootPanel.get().remove(managerView.panel);
            RootPanel.get().add(new LoginView());
        });
        customerButton.addClickHandler(event -> {
            managerView.panel.setVisible(false);
            automobileView.panel.setVisible(false);
            purchaseView.panel.setVisible(false);
            equipmentView.panel.setVisible(false);
            customerView.panel.setVisible(true);
            attributeView.panel.setVisible(false);
            customerView.init();
            RootPanel.get().add(customerView);
        });
        managerButton.addClickHandler(event -> {
            managerView.panel.setVisible(true);
            automobileView.panel.setVisible(false);
            purchaseView.panel.setVisible(false);
            equipmentView.panel.setVisible(false);
            customerView.panel.setVisible(false);
            attributeView.panel.setVisible(false);
            managerView.init();
            RootPanel.get().add(managerView);
        });

        automobilesButton.addClickHandler(event -> {
            managerView.panel.setVisible(false);
            purchaseView.panel.setVisible(false);
            equipmentView.panel.setVisible(false);
            customerView.panel.setVisible(false);
            attributeView.panel.setVisible(false);
            automobileView.init();
            automobileView.panel.setVisible(true);
            RootPanel.get().add(automobileView);
        });

        purchasesButton.addClickHandler(event -> {
            managerView.panel.setVisible(false);
            automobileView.panel.setVisible(false);
            purchaseView.panel.setVisible(true);
            equipmentView.panel.setVisible(false);
            customerView.panel.setVisible(false);
            attributeView.panel.setVisible(false);
            purchaseView.init();
            RootPanel.get().add(purchaseView);
        });

        attributesButton.addClickHandler(event -> {
            managerView.panel.setVisible(false);
            automobileView.panel.setVisible(false);
            purchaseView.panel.setVisible(false);
            equipmentView.panel.setVisible(false);
            customerView.panel.setVisible(false);
            attributeView.panel.setVisible(true);
            attributeView.init();
            RootPanel.get().add(attributeView);
        });

        equipmentsButton.addClickHandler(event -> {
            managerView.panel.setVisible(false);
            automobileView.panel.setVisible(false);
            purchaseView.panel.setVisible(false);
            equipmentView.panel.setVisible(true);
            customerView.panel.setVisible(false);
            attributeView.panel.setVisible(false);
            equipmentView.init();
            RootPanel.get().add(equipmentView);
        });
        optionsButton.addClickHandler(event -> {
            DialogBox dialogBox = new DialogBox();
            dialogBox = new OptionsDialogBox(userLoginInfo);
            dialogBox.show();
        });
    }
}

