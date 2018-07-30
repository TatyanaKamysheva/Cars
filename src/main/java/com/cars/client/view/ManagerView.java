package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.client.view.popup.UserPopup;
import com.cars.shared.ConstantProvider;
import com.cars.shared.models.Manager;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
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
    TextBox firstName = new TextBox();
    TextBox surname = new TextBox();
    TextBox salary = new TextBox();
    TextBox phone = new TextBox();
    ListBox role = new ListBox();
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
    TextColumn<Manager> firstNameColumn = new TextColumn<Manager>() {
        @Override
        public String getValue(Manager object) {
            return object.getFirstName();
        }
    };
    TextColumn<Manager> surnameColumn = new TextColumn<Manager>() {
        @Override
        public String getValue(Manager object) {
            return object.getSurname();
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
    TextColumn<Manager> roleColumn = new TextColumn<Manager>() {
        @Override
        public String getValue(Manager object) {
            return object.getRole();
        }
    };
    ButtonCell buttonCell = new ButtonCell() {
        @Override
        public void render(Context context, SafeHtml data, SafeHtmlBuilder sb) {
            sb.appendHtmlConstant("<button type=\"button\" class=\"gwt-Button\" tabindex=\"-1\">");
            if (data != null) {
                sb.append(data);
            }
            sb.appendHtmlConstant("</button>");
        }
    };
    Column<Manager, String> editColumn = new Column<Manager, String>(buttonCell) {
        @Override
        public String getValue(Manager object) {
            return "Edit";
        }

    };
    Column<Manager, String> deleteColumn = new Column<Manager, String>(buttonCell) {
        @Override
        public String getValue(Manager object) {
            return "Delete";
        }

    };
    //Labels
    Label idLabel = new Label("ID");
    Label firstnameLabel = new Label("First name");
    Label surnameLabel = new Label("Surname");
    Label salaryLabel = new Label("Salary");
    Label phoneLabel = new Label("Phone");
    Label roleLabel = new Label("Role");
    private Label infoLabel = new Label();
    VerticalPanel panel = new VerticalPanel();
    Grid grid = new Grid(7, 2);


    private GWTService restService = (GWTService) GWT.create(GWTService.class);

    ManagerView() {
        panel.setStyleName("myPanel");
        grid.setStyleName("input");
        infoLabel.setStyleName("infoLabel");
        role.addItem("Admin");
        role.addItem("Supervisor");
        role.addItem("Seller");
        RootPanel.get().add(panel);
        table.addColumn(idColumn, "ID");
        table.addColumn(firstNameColumn, "First name");
        table.addColumn(surnameColumn, "Surname");
        table.addColumn(salaryColumn, "Salary");
        table.addColumn(phoneColumn, "Phone");
        table.addColumn(roleColumn, "Role");
        table.addColumn(editColumn, "Edit");
        table.addColumn(deleteColumn, "Delete");
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
        grid.setCellSpacing(10);
        id.setEnabled(false);
        grid.setWidget(0, 0, idLabel);
        grid.setWidget(0, 1, id);
        grid.setWidget(1, 0, firstnameLabel);
        grid.setWidget(1, 1, firstName);
        grid.setWidget(2, 0, surnameLabel);
        grid.setWidget(2, 1, surname);
        grid.setWidget(3, 0, phoneLabel);
        grid.setWidget(3, 1, phone);
        grid.setWidget(4, 0, salaryLabel);
        grid.setWidget(4, 1, salary);
        grid.setWidget(5, 0, roleLabel);
        grid.setWidget(5, 1, role);
        grid.setWidget(6, 0, addButton);
        grid.setWidget(6, 1, infoLabel);

        panel.add(grid);
        panel.add(table);
        refreshTable();
        dataProvider.addDataDisplay(table);
        addButton.addClickHandler(event -> {
            Manager manager = new Manager();
            if (!addButton.getText().equals("Add")) {
                manager.setIdManager(Long.valueOf(id.getText()));
            }
            if (!firstName.getText().matches(ConstantProvider.FIRST_NAME_PATTERN)) {
                infoLabel.setText("Incorrect first name: (3-15 english letters and digits)");
            } else if (!surname.getText().matches(ConstantProvider.LAST_NAME_PATTERN)) {
                infoLabel.setText("Incorrect surname: (3-15 english letters and digits)");
            } else if (!salary.getText().matches(ConstantProvider.NUMBER_PATTERN)) {
                infoLabel.setText("Incorrect salary: (3-15 digits)");
            } else if (!phone.getText().matches(ConstantProvider.PHONE_PATTERN)) {
                infoLabel.setText("Incorrect phone: (3-15 digits)");
            } else {
                manager.setFirstName(firstName.getText());
                manager.setSurname(surname.getText());
                manager.setSalary(Long.valueOf(salary.getText()));
                manager.setPhone(phone.getText());
                manager.setRole(role.getSelectedValue());
                if (addButton.getText().equals("Add")) {
                    restService.saveManager(manager, new MethodCallback<Void>() {
                        @Override
                        public void onFailure(Method method, Throwable exception) {
                            Window.alert("Fail to add manager: " + exception.getMessage());
                        }

                        @Override
                        public void onSuccess(Method method, Void response) {
                            UserPopup userPopup = new UserPopup(manager.getFirstName() + manager.getSurname());
                            userPopup.setPopupPosition(418, 354);
                            userPopup.show();
                            infoLabel.setText("");
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
                            infoLabel.setText("");
                            refreshTable();
                        }
                    });
                }
                id.setText("");
                firstName.setText("");
                surname.setText("");
                salary.setText("");
                phone.setText("");
                addButton.setText("Add");
                infoLabel.setText(" ");
            }
        });
        editColumn.setFieldUpdater((index, object, value) ->
        {
            id.setText(String.valueOf(object.getIdManager()));
            id.setEnabled(false);
            firstName.setText(object.getFirstName());
            surname.setText(object.getSurname());
            salary.setText(String.valueOf(object.getSalary()));
            phone.setText(object.getPhone());
            role.setValue(0, object.getRole());
            addButton.setText("Submit");
        });
        deleteColumn.setFieldUpdater(((index, object, value) -> {
            Long idSelected = object.getIdManager();
            restService.deleteManager(object.getIdManager(), new MethodCallback<Void>() {
                @Override
                public void onFailure(Method method, Throwable exception) {

                }

                @Override
                public void onSuccess(Method method, Void response) {
                    restService.deleteUser(idSelected, new MethodCallback<Void>() {
                        @Override
                        public void onFailure(Method method, Throwable exception) {
                        }

                        @Override
                        public void onSuccess(Method method, Void response) {
                            refreshTable();
                        }
                    });
                }
            });
        }));
    }
}
