package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.shared.models.UserLoginInfo;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.RootPanel;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class Main implements EntryPoint {
    private GWTService restService = (GWTService) GWT.create(GWTService.class);

    public void onModuleLoad() {
        if (Cookies.getCookieNames().contains("sessionID")) {
            restService.loginUser(Cookies.getCookie("login"), Cookies.getCookie("password"), new MethodCallback<UserLoginInfo>() {
                @Override
                public void onFailure(Method method, Throwable throwable) {

                }

                @Override
                public void onSuccess(Method method, UserLoginInfo userLoginInfo) {
                    if (userLoginInfo != null) {
                        RootPanel.get().add(new MainView(userLoginInfo));
                    }
                }
            });
        } else {
            RootPanel.get().add(new LoginView());
        }
        /*Cookies.removeCookie("sessionID");
        Cookies.removeCookie("login");
        Cookies.removeCookie("password");
        RootPanel.get().add(new LoginView());*/
    }
}
