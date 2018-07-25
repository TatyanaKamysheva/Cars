package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.shared.models.UserLoginInfo;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class Main implements EntryPoint {

    GWTService restService = (GWTService) GWT.create(GWTService.class);

    //Buttons


    //Views
    private VerticalPanel panel = new VerticalPanel();
    //private ManagerView managerView;
    private CustomerView customerView;
    /*private AttributeView attributeView;
    private AutomobileView automobileView;
    private EquipmentView equipmentView;
    private PurchaseView purchaseView;*/


    public void onModuleLoad() {
        Cookies.removeCookie("sessionID");
        Cookies.removeCookie("login");
        Cookies.removeCookie("password");
        if (Cookies.getCookieNames().contains("sessionID")) {
            restService.loginUser(Cookies.getCookie("login"),
                    Cookies.getCookie("password"), new MethodCallback<UserLoginInfo>() {
                        @Override
                        public void onFailure(Method method, Throwable throwable) {
                        }

                        @Override
                        public void onSuccess(Method method, UserLoginInfo userLoginInfo) {
                            if (userLoginInfo != null) {
                                //RootPanel.get().add(new MainView(userLoginInfo));
                            }
                        }
                    });
        } else {
            RootPanel.get().add(new LoginView());
        }
        //RootPanel.get().add(managerButton);
        //RootPanel.get().add(customerButton);
        /*RootPanel.get().add(attributesButton);
        RootPanel.get().add(automobilesButton);
        RootPanel.get().add(equipmentsButton);
        RootPanel.get().add(purchasesButton);*/
        //RootPanel.get().add(panel);
       /* managerButton.addClickHandler(event -> {
            panel.clear();
            managerView = new ManagerView(panel);
        });*/
       /* customerButton.addClickHandler(event -> {
            panel.clear();
            customerView = new CustomerView(panel);
        });*/
       /* attributesButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                panel.clear();
                attributeView = new AttributeView(panel);
            }
        });
        automobilesButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                panel.clear();
                automobileView = new AutomobileView(panel);
            }
        });
        equipmentsButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                panel.clear();
                equipmentView = new EquipmentView(panel);
            }
        });
        purchasesButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                panel.clear();
                purchaseView = new PurchaseView(panel);
            }
        });*/
    }
}