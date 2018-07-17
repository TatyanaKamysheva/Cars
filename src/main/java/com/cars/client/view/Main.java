package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.shared.models.Manager;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.ListDataProvider;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class Main implements EntryPoint {
    private Button add = new Button("Add");
    private TextBox name = new TextBox();
    private TextBox salary = new TextBox();
    private TextBox phone = new TextBox();
    private TextBox id = new TextBox();
    private CellTable<Manager> table = new CellTable<>();
    private ListDataProvider<Manager> dataProvider = new ListDataProvider<>();
    private List<Manager> list = dataProvider.getList();

    public void onModuleLoad() {

        id.setEnabled(false);
        RootPanel.get("id").add(id);
        RootPanel.get("name").add(name);
        RootPanel.get("salary").add(salary);
        RootPanel.get("phone").add(phone);
        RootPanel.get().add(add);
        RootPanel.get().add(table);

        TextColumn<Manager> idColumn = new TextColumn<Manager>() {
            @Override
            public String getValue(Manager object) {
                return String.valueOf(object.getIdManager());
            }
        };
        TextColumn<Manager> nameColumn = new TextColumn<Manager>() {
            @Override
            public String getValue(Manager object) {
                return object.getFullName();
            }
        };
        ButtonCell buttonCell = new ButtonCell();
        Column<Manager, String> bColumn = new Column<Manager, String>(buttonCell) {
            @Override
            public String getValue(Manager object) {
                return "EDIT";
            }

        };
        Column<Manager, String> deleteColumn = new Column<Manager, String>(buttonCell) {
            @Override
            public String getValue(Manager object) {
                return "DELETE";
            }

        };
        table.addColumn(idColumn, "ID");
        table.addColumn(nameColumn, "Name");
        table.addColumn(bColumn, "Edit");
        table.addColumn(deleteColumn, "Delete");

       /* GWTService.Util.getService().listManager(new MethodCallback<List<Manager>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Fail to get list");
            }

            @Override
            public void onSuccess(Method method, List<Manager> response) {
                list.addAll(response);
            }
        });*/
        refresh();
        dataProvider.addDataDisplay(table);

        add.addClickHandler(event -> {
            Manager manager = new Manager();
            if (!add.getText().equals("Add")) {
                manager.setIdManager(Long.valueOf(id.getText()));
            }
            manager.setFullName(name.getText());
            manager.setSalary(Long.valueOf(salary.getText()));
            manager.setPhone(phone.getText());

            GWTService.Util.getService().saveManager(manager, new MethodCallback<Void>() {
                @Override
                public void onFailure(Method method, Throwable exception) {
                    Window.alert("Fail save or update");
                }

                @Override
                public void onSuccess(Method method, Void response) {
                    //Window.alert("Success save or update");
                    refresh();
                }
            });
            id.setText("");
            name.setText("");
            salary.setText("");
            phone.setText("");
            add.setText("Add");
        });
        bColumn.setFieldUpdater((index, object, value) -> {
            id.setText(String.valueOf(object.getIdManager()));
            id.setEnabled(false);
            name.setText(object.getFullName());
            salary.setText(String.valueOf(object.getSalary()));
            phone.setText(object.getPhone());
            add.setText("Submit");
        });
        deleteColumn.setFieldUpdater((index, object, value) -> GWTService.Util.getService().deleteManager(Math.toIntExact(object.getIdManager()), new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Fail delete");
            }

            @Override
            public void onSuccess(Method method, Void response) {
                Window.alert("Success delete");
                refresh();
            }
        }));

    }

    private void refresh() {
        GWTService.Util.getService().listManager(new MethodCallback<List<Manager>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {

            }

            @Override
            public void onSuccess(Method method, List<Manager> response) {
                list.clear();
                list.addAll(response);
            }
        });
    }
}