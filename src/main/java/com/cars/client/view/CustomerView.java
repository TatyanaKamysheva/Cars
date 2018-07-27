package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.shared.models.Customer;
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

public class CustomerView extends Composite {
    VerticalPanel panel = new VerticalPanel();
    private GWTService restService = (GWTService) GWT.create(GWTService.class);
    private Button addButton = new Button("Add");
    //Text fields
    private TextBox id = new TextBox();
    private TextBox name = new TextBox();
    Grid grid = new Grid(6, 2);
    private TextBox passport = new TextBox();
    private TextBox phone = new TextBox();

    //Table
    private CellTable<Customer> table = new CellTable<>();
    private ListDataProvider<Customer> dataProvider = new ListDataProvider<>();
    private List<Customer> list = dataProvider.getList();
    //Table columns
    private TextColumn<Customer> idColumn = new TextColumn<Customer>() {
        @Override
        public String getValue(Customer object) {
            return String.valueOf(object.getIdCustomer());
        }
    };
    private TextBox surname = new TextBox();
    private TextColumn<Customer> firstnameColumn = new TextColumn<Customer>() {
        @Override
        public String getValue(Customer object) {
            return object.getFirstName();

        }
    };
    private TextColumn<Customer> passportColumn = new TextColumn<Customer>() {
        @Override
        public String getValue(Customer object) {
            return object.getPassport().toString();
        }
    };
    private TextColumn<Customer> phoneColumn = new TextColumn<Customer>() {
        @Override
        public String getValue(Customer object) {
            return object.getPhone();
        }
    };

    private ButtonCell buttonCell = new ButtonCell();
    private Column<Customer, String> editColumn = new Column<Customer, String>(buttonCell) {
        @Override
        public String getValue(Customer object) {
            return "EDIT";
        }

    };
    private Column<Customer, String> deleteColumn = new Column<Customer, String>(buttonCell) {
        @Override
        public String getValue(Customer object) {
            return "DELETE";
        }

    };
    //Labels
    private Label idLabel = new Label("ID");
    private TextColumn<Customer> surnameColumn = new TextColumn<Customer>() {
        @Override
        public String getValue(Customer object) {
            return object.getSurname();

        }
    };
    private Label nameLabel = new Label("First name");
    private Label passportLabel = new Label("Passport");
    private Label phoneLabel = new Label("Phone");
    private Label surnameLabel = new Label("Surname");
    private Label infoLabel = new Label("");

    CustomerView() {
        RootPanel.get().add(panel);
        table.addColumn(idColumn, "ID");
        table.addColumn(firstnameColumn, "First name");
        table.addColumn(surnameColumn, "Surname");
        table.addColumn(passportColumn, "Passport");
        table.addColumn(phoneColumn, "Phone");
        table.addColumn(editColumn, "Edit");
        table.addColumn(deleteColumn, "Delete");
    }

    private void refreshTable() {
        restService.listCustomer(new MethodCallback<List<Customer>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Fail to get list");
            }

            @Override
            public void onSuccess(Method method, List<Customer> response) {
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
        grid.setWidget(1, 0, nameLabel);
        grid.setWidget(1, 1, name);
        grid.setWidget(2, 0, surnameLabel);
        grid.setWidget(2, 1, surname);
        grid.setWidget(3, 0, passportLabel);
        grid.setWidget(3, 1, passport);
        grid.setWidget(4, 0, phoneLabel);
        grid.setWidget(4, 1, phone);
        grid.setWidget(5, 0, addButton);
        grid.setWidget(5, 1, infoLabel);

        panel.add(grid);
        panel.add(table);
        refreshTable();
        dataProvider.addDataDisplay(table);
        addButton.addClickHandler(event -> {
            Customer customer = new Customer();
            if (!addButton.getText().equals("Add")) {
                customer.setIdCustomer(Long.valueOf(id.getText()));
            }
            if (name.getText().equals("")) {
                infoLabel.setText("Enter the name!");
                name.setText("");
                return;
            } else customer.setFirstName(name.getText());

            if (surname.getText().equals("")) {
                infoLabel.setText("Enter the name!");
                surname.setText("");
                return;
            } else customer.setSurname(surname.getText());

            try {
                Long.valueOf(passport.getText());
                customer.setPassport(Long.valueOf(passport.getText()));

            } catch (NumberFormatException e) {
                infoLabel.setText("Enter the number!");
                return;
            }

            if (phone.getText().equals("")) {
                infoLabel.setText("Enter the phone!");
                phone.setText("");
                return;
            } else customer.setPhone(phone.getText());

            if (addButton.getText().equals("Add")) {

                restService.saveCustomer(customer, new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert("Fail to save customer");
                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        infoLabel.setText("");
                        refreshTable();
                    }
                });
            } else {
                restService.updateCustomer(customer, new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert("Fail to update customer");
                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        infoLabel.setText("");
                        refreshTable();
                    }
                });
            }
            id.setText("");
            name.setText("");
            surname.setText("");
            passport.setText("");
            phone.setText("");
            addButton.setText("Add");
        });
        editColumn.setFieldUpdater((index, object, value) ->
        {
            id.setText(String.valueOf(object.getIdCustomer()));
            id.setEnabled(false);
            name.setText(object.getFirstName());
            surname.setText(object.getSurname());
            passport.setText(String.valueOf(object.getPassport()));
            phone.setText(object.getPhone());
            addButton.setText("Submit");
        });
        deleteColumn.setFieldUpdater(((index, object, value) -> {
            restService.deleteCustomer(object.getIdCustomer(), new MethodCallback<Void>() {
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

