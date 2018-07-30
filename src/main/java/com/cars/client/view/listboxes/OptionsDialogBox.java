package com.cars.client.view.listboxes;

import com.cars.shared.models.UserLoginInfo;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class OptionsDialogBox extends DialogBox {
    public OptionsDialogBox(UserLoginInfo userLoginInfo) {
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSpacing(20);
        PushButton changePswrdButton = new PushButton("Change password");
        Button cancelButton = new Button("Cancel");

        changePswrdButton.addClickHandler(clickEvent -> {
            DialogBox dialogBox = new ChangePasswordDialogBox(userLoginInfo);
            dialogBox.setStyleName("boxForLogging");
            int x = changePswrdButton.getElement().getAbsoluteLeft();
            int y = changePswrdButton.getElement().getAbsoluteTop();
            dialogBox.setPopupPosition(x - 300, y);
            dialogBox.show();
            dialogBox.addCloseHandler(closeEvent -> hide());
        });
        cancelButton.addClickHandler(clickEvent -> hide());
        verticalPanel.add(changePswrdButton);
        verticalPanel.add(cancelButton);
        verticalPanel.setStyleName("boxForLogging");
        setWidget(verticalPanel);
    }
}

