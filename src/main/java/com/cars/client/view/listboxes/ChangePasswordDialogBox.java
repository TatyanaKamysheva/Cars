package com.cars.client.view.listboxes;

import com.cars.client.rest.GWTService;
import com.cars.shared.ConstantProvider;
import com.cars.shared.MD5Util;
import com.cars.shared.models.entities.User;
import com.cars.shared.models.UserLoginInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

class ChangePasswordDialogBox extends DialogBox {
    private GWTService restService = (GWTService) GWT.create(GWTService.class);

    ChangePasswordDialogBox(UserLoginInfo userLoginInfo) {

        DialogBox boxForLogging = new DialogBox();
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSpacing(10);

        Label messageLabel = new Label();
        verticalPanel.add(messageLabel);
        messageLabel.setStyleName("infoLabel");

        Grid grid = new Grid(3, 2);
        grid.setCellSpacing(10);

        PasswordTextBox oldUserPassword = new PasswordTextBox();
        PasswordTextBox newUserPassword1 = new PasswordTextBox();
        PasswordTextBox newUserPassword2 = new PasswordTextBox();

        PushButton changePswrdButton = new PushButton("Change");

        grid.setWidget(0, 0, new Label("Old password: "));
        grid.setWidget(0, 1, oldUserPassword);
        grid.setWidget(1, 0, new Label("New password: "));
        grid.setWidget(1, 1, newUserPassword1);
        grid.setWidget(2, 0, new Label("Repeat new password: "));
        grid.setWidget(2, 1, newUserPassword2);
        grid.setStyleName("boxForLogging");
        verticalPanel.add(grid);

        changePswrdButton.addClickHandler((ClickEvent clickEvent) -> {
            String oldPassword = oldUserPassword.getText();
            String newPassword1 = newUserPassword1.getText();
            String newPassword2 = newUserPassword2.getText();

            if (oldPassword.isEmpty() || newPassword1.isEmpty() || newPassword2.isEmpty()) {
                messageLabel.setText("Please enter all information about password!");
            } else if (!oldPassword.matches(ConstantProvider.PASSWORD_PATTERN)) {
                messageLabel.setText("Please enter correct old password (3-20 english letters and digits)!");
            } else if (!newPassword1.matches(ConstantProvider.PASSWORD_PATTERN)) {
                messageLabel.setText("Please enter correct new first password (3-20 english letters and digits)!");
            } else if (!newPassword2.matches(ConstantProvider.PASSWORD_PATTERN)) {
                messageLabel.setText("Please enter correct new second password (3-20 english letters and digits)!");
            } else if (newPassword1.compareTo(newPassword2) != 0) {
                messageLabel.setText("New passwords do not match!");
            } else if (oldPassword.compareTo(newPassword1) == 0) {
                messageLabel.setText("Old and new passwords must not match!");
            } else {
                restService.getUserByLogin(Cookies.getCookie("login"), new MethodCallback<User>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(Method method, User user) {
                        if (user != null) {
                            if (MD5Util.md5Hex(oldPassword).compareTo(user.getPassword()) == 0) {
                                user.setPassword(MD5Util.md5Hex(newPassword1));
                                restService.updateUser(user, new MethodCallback<Void>() {
                                    @Override
                                    public void onFailure(Method method, Throwable throwable) {
                                        messageLabel.setText("Fail update user.");
                                    }

                                    @Override
                                    public void onSuccess(Method method, Void status) {
                                        Cookies.setCookie("password", user.getPassword());
                                        boxForLogging.setGlassEnabled(true);
                                        boxForLogging.setText("Password successfully changed!");
                                        Timer timer = new Timer() {
                                            @Override
                                            public void run() {
                                                boxForLogging.hide();
                                            }
                                        };
                                        timer.schedule(2000);
                                        boxForLogging.center();
                                        hide();
                                    }
                                });

                            } else {
                                messageLabel.setText("Old and current passwords are incorrect!");
                            }
                        } else

                        {
                            messageLabel.setText("User is not found!");
                        }
                    }
                });
            }
        });

        HorizontalPanel buttonsHorizontalPanel = new HorizontalPanel();

        PushButton cancelButton = new PushButton("Cancel");
        cancelButton.addClickHandler(clickEvent -> hide());

        buttonsHorizontalPanel.add(changePswrdButton);
        buttonsHorizontalPanel.add(cancelButton);
        verticalPanel.add(buttonsHorizontalPanel);
        setWidget(verticalPanel);
    }
}

