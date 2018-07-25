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
    private TextBox passport = new TextBox();
    private TextBox phone = new TextBox();
    private TextBox email = new TextBox();
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
    private TextColumn<Customer> nameColumn = new TextColumn<Customer>() {
        @Override
        public String getValue(Customer object) {
            return object.getFullName();
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
    private TextColumn<Customer> emailColumn = new TextColumn<Customer>() {
        @Override
        public String getValue(Customer object) {
            return object.getEmail();
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
    private Label nameLabel = new Label("Full name");
    private Label passportLabel = new Label("Passport");
    private Label phoneLabel = new Label("Phone");
    private Label emailLabel = new Label("Email");

    CustomerView() {
        RootPanel.get().add(panel);
        table.addColumn(idColumn, "ID");
        table.addColumn(nameColumn, "Name");
        table.addColumn(passportColumn, "Passport");
        table.addColumn(phoneColumn, "Phone");
        table.addColumn(emailColumn, "Email");
        table.addColumn(editColumn, "Edit");
        table.addColumn(deleteColumn, "Delete");
    }

    private void refreshTable() {
        restService.listCustomer(new MethodCallback<List<Customer>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
            }

            @Override
            public void onSuccess(Method method, List<Customer> response) {
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

        panel.add(passportLabel);
        panel.add(passport);

        panel.add(phoneLabel);
        panel.add(phone);

        panel.add(emailLabel);
        panel.add(email);

        panel.add(addButton);
        panel.add(table);
        refreshTable();
        dataProvider.addDataDisplay(table);
        addButton.addClickHandler(event -> {
            Customer customer = new Customer();
            if (!addButton.getText().equals("Add")) {
                customer.setIdCustomer(Long.valueOf(id.getText()));
            }
            customer.setFullName(name.getText());
            customer.setPassport(Long.valueOf(passport.getText()));
            customer.setPhone(phone.getText());
            customer.setEmail(email.getText());
            if (addButton.getText().equals("Add")) {

                restService.saveCustomer(customer, new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        refreshTable();
                    }
                });
            } else {
                restService.updateCustomer(customer, new MethodCallback<Void>() {
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
            passport.setText("");
            phone.setText("");
            email.setText("");
            addButton.setText("Add");
        });
        editColumn.setFieldUpdater((index, object, value) ->
        {
            id.setText(String.valueOf(object.getIdCustomer()));
            id.setEnabled(false);
            name.setText(object.getFullName());
            passport.setText(String.valueOf(object.getPassport()));
            phone.setText(object.getPhone());
            email.setText(object.getEmail());
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

