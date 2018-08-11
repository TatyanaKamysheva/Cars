package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.client.view.popup.UserPopup;
import com.cars.shared.ConstantProvider;
import com.cars.shared.models.Response;
import com.cars.shared.models.UserLoginInfo;
import com.cars.shared.models.entities.Employee;
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
import java.util.Objects;

public class ManagerView extends Composite {

    private Button addButton = new Button("Add");

    //Text fields
    private TextBox id = new TextBox();
    private TextBox firstName = new TextBox();
    private TextBox surname = new TextBox();
    private TextBox phone = new TextBox();
    private ListBox role = new ListBox();
    //Table
    private CellTable<Employee> table = new CellTable<>();
    private ListDataProvider<Employee> dataProvider = new ListDataProvider<>();
    private List<Employee> list = dataProvider.getList();
    //Table columns
    private TextColumn<Employee> idColumn = new TextColumn<Employee>() {
        @Override
        public String getValue(Employee object) {
            return String.valueOf(object.getEmployeeId());
        }
    };
    private TextColumn<Employee> firstNameColumn = new TextColumn<Employee>() {
        @Override
        public String getValue(Employee object) {
            return object.getFirstName();
        }
    };
    private TextColumn<Employee> surnameColumn = new TextColumn<Employee>() {
        @Override
        public String getValue(Employee object) {
            return object.getSurname();
        }
    };
    private TextColumn<Employee> phoneColumn = new TextColumn<Employee>() {
        @Override
        public String getValue(Employee object) {
            return object.getPhone();
        }
    };
    private TextColumn<Employee> roleColumn = new TextColumn<Employee>() {
        @Override
        public String getValue(Employee object) {
            return object.getRole();
        }
    };
    private ButtonCell buttonCell = new ButtonCell() {
        @Override
        public void render(Context context, SafeHtml data, SafeHtmlBuilder sb) {
            sb.appendHtmlConstant("<button type=\"button\" class=\"gwt-Button\" tabindex=\"-1\">");
            if (data != null) {
                sb.append(data);
            }
            sb.appendHtmlConstant("</button>");
        }
    };
    private Column<Employee, String> editColumn = new Column<Employee, String>(buttonCell) {
        @Override
        public String getValue(Employee object) {
            return "Edit";
        }

    };
    private Column<Employee, String> deleteColumn = new Column<Employee, String>(buttonCell) {
        @Override
        public String getValue(Employee object) {
            return "Delete";
        }

    };
    //Labels
    private Label idLabel = new Label("ID");
    private Label firstnameLabel = new Label("First name");
    private Label surnameLabel = new Label("Surname");
    private Label phoneLabel = new Label("Phone");
    private Label roleLabel = new Label("Role");
    private Label infoLabel = new Label();
    VerticalPanel panel = new VerticalPanel();
    private Grid grid = new Grid(7, 2);
    private UserLoginInfo userLoginInfo;


    private GWTService restService = (GWTService) GWT.create(GWTService.class);

    ManagerView(UserLoginInfo userLoginInfo) {
        this.userLoginInfo = userLoginInfo;
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
        table.addColumn(phoneColumn, "Phone");
        table.addColumn(roleColumn, "Role");
        table.addColumn(editColumn, "Edit");
        table.addColumn(deleteColumn, "Delete");
    }

    private void refreshTable() {
        restService.listManager(new MethodCallback<List<Employee>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Fail to get list");
            }

            @Override
            public void onSuccess(Method method, List<Employee> response) {
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
        grid.setWidget(4, 0, roleLabel);
        grid.setWidget(4, 1, role);
        grid.setWidget(6, 0, addButton);


        panel.setSpacing(10);
        panel.add(grid);
        panel.add(infoLabel);
        panel.add(table);
        refreshTable();
        dataProvider.addDataDisplay(table);
        addButton.addClickHandler(event -> {
            Employee manager = new Employee();
            if (!addButton.getText().equals("Add")) {
                manager.setEmployeeId(Long.valueOf(id.getText()));
            }
            if (!firstName.getText().matches(ConstantProvider.FIRST_NAME_PATTERN)) {
                infoLabel.setText("Incorrect first name: (3-15 english letters)");
            } else if (!surname.getText().matches(ConstantProvider.LAST_NAME_PATTERN)) {
                infoLabel.setText("Incorrect surname: (3-15 english letters)");
            } else if (!phone.getText().matches(ConstantProvider.PHONE_PATTERN)) {
                infoLabel.setText("Incorrect phone: (3-15 digits and symbols)");
            } else {
                manager.setFirstName(firstName.getText());
                manager.setSurname(surname.getText());
                manager.setPhone(phone.getText());
                manager.setRole(role.getSelectedValue());
                if (addButton.getText().equals("Add")) {
                    restService.saveManager(manager, new MethodCallback<Response>() {
                        @Override
                        public void onFailure(Method method, Throwable exception) {
                            Window.alert("Fail to add manager: " + exception.getMessage());
                        }

                        @Override
                        public void onSuccess(Method method, Response response) {
                            if (response.getCode() == 1) {
                                UserPopup userPopup = new UserPopup(manager.getFirstName() + manager.getSurname());
                                userPopup.setPopupPosition(418, 354);
                                userPopup.show();
                                infoLabel.setText("");
                                refreshTable();
                            } else Window.alert("Fail to add " + response.getMessage());

                        }
                    });
                } else {
                    restService.updateManager(manager, new MethodCallback<Response>() {
                        @Override
                        public void onFailure(Method method, Throwable exception) {
                            Window.alert("Fail to update: " + exception.getMessage());
                        }

                        @Override
                        public void onSuccess(Method method, Response response) {
                            if (response.getCode() == 1) {
                                infoLabel.setText("");
                                refreshTable();
                            } else Window.alert("Fail to update " + response.getMessage());
                        }
                    });
                }
                id.setText("");
                firstName.setText("");
                surname.setText("");
                phone.setText("");
                addButton.setText("Add");
                infoLabel.setText("");
            }
        });
        editColumn.setFieldUpdater((index, object, value) ->
        {
            id.setText(String.valueOf(object.getEmployeeId()));
            id.setEnabled(false);
            firstName.setText(object.getFirstName());
            surname.setText(object.getSurname());
            phone.setText(object.getPhone());
            role.setValue(0, object.getRole());
            addButton.setText("Submit");
        });
        deleteColumn.setFieldUpdater(((index, object, value) -> {
            Long idSelected = object.getEmployeeId();
            if (!Objects.equals(idSelected, userLoginInfo.getUserId())) {
                restService.deleteManager(object.getEmployeeId(), new MethodCallback<Response>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {

                    }

                    @Override
                    public void onSuccess(Method method, Response response) {
                        restService.deleteUser(idSelected, new MethodCallback<Response>() {
                            @Override
                            public void onFailure(Method method, Throwable exception) {
                                Window.alert("Fail to delete " + exception.getMessage());
                            }

                            @Override
                            public void onSuccess(Method method, Response response) {
                                Window.alert(response.getMessage());
                                refreshTable();
                            }
                        });
                    }
                });
            }
        }));

    }
}
