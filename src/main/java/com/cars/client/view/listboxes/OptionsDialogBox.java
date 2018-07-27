package com.cars.client.view.listboxes;

import com.cars.client.rest.GWTService;
import com.cars.shared.models.UserLoginInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class OptionsDialogBox extends DialogBox {
    private GWTService restService = (GWTService) GWT.create(GWTService.class);

    public OptionsDialogBox(UserLoginInfo userLoginInfo) {
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSpacing(10);
        PushButton changePswrdButton = new PushButton("Change password");

        changePswrdButton.addClickHandler(clickEvent -> {
            DialogBox dialogBox = new ChangePasswordDialogBox(userLoginInfo);
            int x = changePswrdButton.getElement().getAbsoluteRight();
            int y = changePswrdButton.getElement().getAbsoluteTop();
            dialogBox.setPopupPosition(x + 10, y - 20);
            dialogBox.show();
            dialogBox.addCloseHandler(closeEvent -> hide());
        });

        verticalPanel.add(changePswrdButton);

        //добавляю на нижнюю горизонтальную панель кнопку cancel
        HorizontalPanel buttonsHorizontalPanel = new HorizontalPanel();

        PushButton cancelButton = new PushButton("Cancel");
        cancelButton.addClickHandler(clickEvent -> hide());
        buttonsHorizontalPanel.add(cancelButton);

        verticalPanel.add(buttonsHorizontalPanel);

        setWidget(verticalPanel);
    }


}

