package com.cars.client.view;

import com.cars.client.view.listboxes.OptionsDialogBox;
import com.cars.shared.models.UserLoginInfo;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;

class MainView extends Composite {

    static {
        Defaults.setDateFormat(null);
    }

    private Button managerButton = new Button("Managers");
    private Button customerButton = new Button("Customers");
    private Button purchasesButton = new Button("Purchases");
    private Button automobilesButton = new Button("Automobiles");
    private Button attributesButton = new Button("Attributes");
    private Button equipmentsButton = new Button("Equipments");
    private Button optionsButton = new Button("Options");
    private Button logoutButton = new Button("Log out");
    private Grid gridOption = new Grid(2, 1);

    public MainView(UserLoginInfo userLoginInfo) {
        Grid grid = new Grid(1, 6);
        grid.setStyleName("myGrid");
        gridOption.setStyleName("myNewGrid");
        gridOption.setCellSpacing(20);
        managerButton.setStyleName("panelButton");
        customerButton.setStyleName("panelButton");
        automobilesButton.setStyleName("panelButton");
        attributesButton.setStyleName("panelButton");
        purchasesButton.setStyleName("panelButton");
        equipmentsButton.setStyleName("panelButton");
        gridOption.setWidget(0, 0, logoutButton);
        gridOption.setWidget(1, 0, optionsButton);
        RootPanel.get().add(gridOption);

        switch (userLoginInfo.getRole()) {
            case Seller:
                grid.setWidget(0, 1, customerButton);
                grid.setWidget(0, 2, purchasesButton);
                grid.setWidget(0, 3, automobilesButton);
                grid.setWidget(0, 4, equipmentsButton);
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
                grid.setCellSpacing(30);
                RootPanel.get().add(grid);
                break;
            case Supervisor:
                grid.setWidget(0, 2, attributesButton);
                grid.setWidget(0, 3, automobilesButton);
                grid.setWidget(0, 4, equipmentsButton);
                grid.setCellSpacing(30);
                RootPanel.get().add(grid);
                break;
        }

        CustomerView customerView = new CustomerView();
        AutomobileView automobileView = new AutomobileView(userLoginInfo);
        PurchaseView purchaseView = new PurchaseView(userLoginInfo);
        AttributeView attributeView = new AttributeView();
        ManagerView managerView = new ManagerView(userLoginInfo);
        EquipmentView equipmentView = new EquipmentView(userLoginInfo);
        logoutButton.addClickHandler(event -> {
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
            RootPanel.get().remove(gridOption);
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
            automobileView.panel.setVisible(true);
            automobileView.init();
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
            DialogBox dialogBox = new OptionsDialogBox(userLoginInfo);
            int x = optionsButton.getElement().getAbsoluteLeft();
            int y = optionsButton.getElement().getAbsoluteTop();
            dialogBox.setPopupPosition(x - 300, y);
            dialogBox.show();
        });
    }
}
