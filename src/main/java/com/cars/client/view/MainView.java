package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.shared.models.UserLoginInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import org.fusesource.restygwt.client.Defaults;

public class MainView extends Composite {

    static {
        Defaults.setDateFormat(null);
    }

    UserLoginInfo userLoginInfo;

    //добавляю кнопку опций, где будет возможность выбрать изменение своего пароля
    private PushButton optionsButton = new PushButton("Options");
    private GWTService restService = (GWTService) GWT.create(GWTService.class);
    private String roleEmployee = null;
    private Button managerButton = new Button("Managers");
    private Button customerButton = new Button("Customers");
    private Button purchasesButton = new Button("Purchases");
    private Button automobilesButton = new Button("Automobiles");
    private Button attributesButton = new Button("Attributes");
    private Button equipmentsButton = new Button("Equipments");


    public MainView(UserLoginInfo userLoginInfo) {
        switch (userLoginInfo.getRole()) {
            case Seller:
                RootPanel.get().add(managerButton);
                RootPanel.get().add(customerButton);
                RootPanel.get().add(purchasesButton);
                RootPanel.get().add(automobilesButton);
                break;
        }

        CustomerView customerView = new CustomerView();
        ManagerView managerView = new ManagerView(userLoginInfo);
        AutomobileView automobileView = new AutomobileView(userLoginInfo);
        //PurchaseView purchaseView=new PurchaseView();
        //AttributeView attributeView=new AttributeView();

        customerButton.addClickHandler(event -> {
            customerView.panel.setVisible(true);
            managerView.panel.setVisible(false);
            customerView.init();
            RootPanel.get().add(customerView);
        });
        managerButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                customerView.panel.setVisible(false);
                managerView.panel.setVisible(true);
                managerView.init();
                RootPanel.get().add(managerView);
            }
        });

        automobilesButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                customerView.panel.setVisible(false);
                managerView.panel.setVisible(false);
                automobileView.init();
                RootPanel.get().add(automobileView);
            }
        });
    }
}

