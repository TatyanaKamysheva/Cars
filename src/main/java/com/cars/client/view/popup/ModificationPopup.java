package com.cars.client.view.popup;

import com.cars.client.rest.GWTService;
import com.cars.shared.models.Modification;
import com.cars.shared.models.entities.Equipment;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class ModificationPopup extends PopupPanel {
    private VerticalPanel panel = new VerticalPanel();
    private CellTable<Equipment> table = new CellTable<>();
    private ListDataProvider<Equipment> dataProvider = new ListDataProvider<>();
    private List<Equipment> list = dataProvider.getList();
    private GWTService restService = (GWTService) GWT.create(GWTService.class);
    private Button closeButton = new Button("Close");
    private TextColumn<Equipment> nameColumn = new TextColumn<Equipment>() {
        @Override
        public String getValue(Equipment object) {
            return object.getAttribute().getName();
        }
    };
    private TextColumn<Equipment> valueColumn = new TextColumn<Equipment>() {
        @Override
        public String getValue(Equipment object) {
            return object.getValue();
        }
    };
    private HorizontalPanel horizontalPanel = new HorizontalPanel();

    public ModificationPopup(Long id) {
        table.addColumn(nameColumn);
        table.addColumn(valueColumn);
        horizontalPanel.setSpacing(10);
        restService.listModifications(id, new MethodCallback<List<Modification>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Fail to get modifications: " + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<Modification> response) {
                for (Modification m : response) {
                    horizontalPanel.add(new Button(m.getModification(), (ClickHandler) event -> {
                        refreshTable(id, m.getModification());
                        dataProvider.addDataDisplay(table);
                        panel.add(table);
                    }));
                }
            }
        });
        panel.add(closeButton);
        panel.add(horizontalPanel);
        closeButton.addClickHandler(event -> hide());
        panel.setStyleName("boxForLogging");
        setWidget(panel);
    }

    public ModificationPopup(Long id, String modification) {
        table.addColumn(nameColumn, "Attribute");
        table.addColumn(valueColumn, "Value");
        refreshTable(id, modification);
        panel.setSpacing(10);
        dataProvider.addDataDisplay(table);
        panel.add(table);
        panel.add(closeButton);
        closeButton.addClickHandler(event -> hide());
        panel.setStyleName("boxForLogging");
        setWidget(panel);
    }

    private void refreshTable(Long id, String modification) {
        restService.listAutoPopup(id, modification, new MethodCallback<List<Equipment>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Fail to get list: " + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<Equipment> response) {
                list.clear();
                list.addAll(response);
            }
        });
    }
}
