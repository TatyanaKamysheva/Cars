package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.shared.models.Manager;
import com.cars.shared.models.UserLoginInfo;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class ManagerView extends Composite {

    Button addButton = new Button("Add");
    //Text fields
    TextBox id = new TextBox();
    TextBox name = new TextBox();
    TextBox salary = new TextBox();
    TextBox phone = new TextBox();
    //Table
    CellTable<Manager> table = new CellTable<>();
    ListDataProvider<Manager> dataProvider = new ListDataProvider<>();
    List<Manager> list = dataProvider.getList();
    //Table columns
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
    TextColumn<Manager> salaryColumn = new TextColumn<Manager>() {
        @Override
        public String getValue(Manager object) {
            return object.getSalary().toString();
        }
    };
    TextColumn<Manager> phoneColumn = new TextColumn<Manager>() {
        @Override
        public String getValue(Manager object) {
            return object.getPhone();
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
    //Labels
    Label idLabel = new Label("ID");
    Label nameLabel = new Label("Full name");
    Label salaryLabel = new Label("Salary");
    Label phoneLabel = new Label("Phone");
    VerticalPanel panel = new VerticalPanel();
    private GWTService restService = (GWTService) GWT.create(GWTService.class);

    ManagerView(UserLoginInfo userLoginInfo) {
        RootPanel.get().add(panel);
        table.addColumn(idColumn, "ID");
        table.addColumn(nameColumn, "Name");
        table.addColumn(salaryColumn, "Salary");
        table.addColumn(phoneColumn, "Phone");
        switch (userLoginInfo.getRole()) {
            case Admin:
                table.addColumn(bColumn, "Edit");
                table.addColumn(deleteColumn, "Delete");
        }


    }

    void refreshTable() {
        restService.listManager(new MethodCallback<List<Manager>>() {
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

    void init() {
        panel.add(idLabel);
        id.setEnabled(false);
        panel.add(id);

        panel.add(nameLabel);
        panel.add(name);

        panel.add(salaryLabel);
        panel.add(salary);

        panel.add(phoneLabel);
        panel.add(phone);

        panel.add(addButton);

        panel.add(table);
        refreshTable();
        dataProvider.addDataDisplay(table);
        addButton.addClickHandler(event -> {
            Manager manager = new Manager();
            if (!addButton.getText().equals("Add")) {
                manager.setIdManager(Long.valueOf(id.getText()));
            }
            manager.setFullName(name.getText());
            manager.setSalary(Long.valueOf(salary.getText()));
            manager.setPhone(phone.getText());
            if (addButton.getText().equals("Add")) {
                restService.saveManager(manager, new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {

                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        refreshTable();
                    }
                });
            } else {
                restService.updateManager(manager, new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {

                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        refreshTable();
                    }
                });
            }
            id.setText("");
            name.setText("");
            salary.setText("");
            phone.setText("");
            addButton.setText("Add");
        });
        bColumn.setFieldUpdater((index, object, value) ->
        {
            id.setText(String.valueOf(object.getIdManager()));
            id.setEnabled(false);
            name.setText(object.getFullName());
            salary.setText(String.valueOf(object.getSalary()));
            phone.setText(object.getPhone());
            addButton.setText("Submit");
        });
        deleteColumn.setFieldUpdater(((index, object, value) -> {
            restService.deleteManager(object.getIdManager(), new MethodCallback<Void>() {
                @Override
                public void onFailure(Method method, Throwable exception) {
                    Window.alert("Fail");
                }

                @Override
                public void onSuccess(Method method, Void response) {
                    refreshTable();
                }
            });
        }));
    }
}
