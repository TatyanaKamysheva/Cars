package com.cars.client.view.popup;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UserPopup extends PopupPanel {
    private VerticalPanel panel = new VerticalPanel();
    private Button button = new Button("Close");
    private Label loginLable = new Label("Login: ");
    private Label passwordLabel = new Label("Password: 2222");
    private Label message = new Label("To change your password sing in and choose the Option Button");

    public UserPopup(String login) {
        loginLable.setText(loginLable.getText() + login);
        panel.add(loginLable);
        panel.add(passwordLabel);
        panel.add(message);
        panel.add(button);
        button.addClickHandler(event -> hide());
        panel.setStyleName("boxForLogging");
        setWidget(panel);
    }
}
