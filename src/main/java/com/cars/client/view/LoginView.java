package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.shared.MD5Util;
import com.cars.shared.models.UserLoginInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.Random;

public class LoginView extends Composite {

    GWTService restService = (GWTService) GWT.create(GWTService.class);

    public LoginView() {

        Grid grid = new Grid(2, 2);
        grid.setCellSpacing(20);
        TextBox userLogin = new TextBox();
        PasswordTextBox userPassword = new PasswordTextBox();
        Button loginButton = new Button("Login");
        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.setSpacing(20);
        userLogin.setWidth("150px");
        userPassword.setWidth("150px");

        grid.setWidget(0, 0, new Label("Username"));
        grid.setWidget(0, 1, userLogin);
        grid.setWidget(1, 0, new Label("Password"));
        grid.setWidget(1, 1, userPassword);

        Label infoLabel = new Label();
        infoLabel.setStyleName("infoLabel");

        mainPanel.add(grid);
        loginButton.addClickHandler(clickEvent -> {
            loginButton.setEnabled(false);

            Timer timer = new Timer() {
                @Override
                public void run() {
                    loginButton.setEnabled(true);
                }
            };

            timer.schedule(7000);

            String login = userLogin.getText();
            String passwordHelper = userPassword.getText();

            String password = MD5Util.md5Hex(passwordHelper);

            Cookies.setCookie("login", login);
            Cookies.setCookie("password", password);

            restService.loginUser(login, password, new MethodCallback<UserLoginInfo>() {
                @Override
                public void onFailure(Method method, Throwable throwable) {
                }

                @Override
                public void onSuccess(Method method, UserLoginInfo userLoginInfo) {
                    if (userLoginInfo != null) {
                        Cookies.setCookie("sessionID", String.valueOf(new Random().nextInt(999999)));
                        RootPanel.get("options").remove(mainPanel);
                        RootPanel.get().add(new MainView(userLoginInfo));
                    } else {
                        infoLabel.setText("Wrong login or password!");
                    }
                }
            });
        });
        mainPanel.add(infoLabel);
        mainPanel.add(loginButton);
        RootPanel.get("options").add(mainPanel);
    }
}
