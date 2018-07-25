package com.cars.client.view.popup;

import com.cars.client.rest.GWTService;
import com.cars.shared.models.AutoPopup;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.builder.shared.TableSectionBuilder;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.view.client.ListDataProvider;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class AutomobilePopup extends PopupPanel {

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

    public AutomobilePopup(Long id) {
        this.id = id;
        table.addColumn(nameAttrColumn, "Attribute");
        table.addColumn(valueAttrColumn, "Value");
        refreshTable();
        dataProvider.addDataDisplay(table);
        setWidget(table);
    }

    private void refreshTable() {
        restService.listAutoPopup(id, new MethodCallback<List<AutoPopup>>() {
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
