package com.cars.client.view.popup;

import com.cars.client.rest.GWTService;
import com.cars.shared.models.AutoPopup;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class AutomobilePopup extends PopupPanel {
    VerticalPanel panel = new VerticalPanel();
    TextColumn<AutoPopup> nameAttrColumn = new TextColumn<AutoPopup>() {
        @Override
        public String getValue(AutoPopup object) {
            return object.getAttribute();
        }
    };
    TextColumn<AutoPopup> valueAttrColumn = new TextColumn<AutoPopup>() {
        @Override
        public String getValue(AutoPopup object) {
            return object.getValue();
        }
    };
    Long id;
    private CellTable<AutoPopup> table = new CellTable<>();
    private ListDataProvider<AutoPopup> dataProvider = new ListDataProvider<>();
    private List<AutoPopup> list = dataProvider.getList();
    private GWTService restService = (GWTService) GWT.create(GWTService.class);
    Button button = new Button("Close");
    String modification;

    public AutomobilePopup(Long id, String modification) {
        this.modification = modification;
        this.id = id;
        table.addColumn(nameAttrColumn, "Attribute");
        table.addColumn(valueAttrColumn, "Value");
        refreshTable(modification);
        dataProvider.addDataDisplay(table);
        panel.add(table);
        panel.add(button);
        button.addClickHandler(event -> {
            hide();
        });
        setWidget(panel);
    }

    private void refreshTable(String modification) {
        restService.listAutoPopup(id, modification, new MethodCallback<List<AutoPopup>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
            }

            @Override
            public void onSuccess(Method method, List<AutoPopup> response) {
                list.clear();
                list.addAll(response);
            }
        });
    }


}
