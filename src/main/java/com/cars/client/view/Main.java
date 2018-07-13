package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.cars.shared.models.Manager;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class Main implements EntryPoint {
    private Button button = new Button("Manager");
    private Label label = new Label();
    private CellTable<Manager> table = new CellTable<>();
    private TextColumn<Manager> nameColumn = new TextColumn<Manager>() {
        @Override
        public String getValue(Manager object) {
            return object.getFullName();
        }
    };

    public void onModuleLoad() {
        RootPanel.get("button").add(button);
        RootPanel.get("label").add(label);
        RootPanel.get("table").add(table);

        GWTService.Util.getService().list(new MethodCallback<List<Manager>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                label.setText("ERROR LIST");
            }

            @Override
            public void onSuccess(Method method, List<Manager> response) {
                table.addColumn(nameColumn);
                ListDataProvider<Manager> dataProvider = new ListDataProvider<>();
                List<Manager> list = dataProvider.getList();
                list.addAll(response);
                dataProvider.addDataDisplay(table);
            }
        });

    }
}
