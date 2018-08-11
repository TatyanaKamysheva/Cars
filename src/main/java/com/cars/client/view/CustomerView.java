package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.shared.ConstantProvider;
import com.cars.shared.models.Response;
import com.cars.shared.models.entities.Customer;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class CustomerView extends Composite {
    //Panels and grids
    VerticalPanel panel = new VerticalPanel();
    private Grid inputGrid = new Grid(6, 2);
    private DialogBox messageBox = new DialogBox();

    //Rest service
    private GWTService restService = (GWTService) GWT.create(GWTService.class);

    //Buttons
    private Button addButton = new Button("Add");

    //Text fields
    private TextBox idTextBox = new TextBox();
    private TextBox firstNameTextBox = new TextBox();
    private TextBox surnameTextBox = new TextBox();
    private TextBox passportTextBox = new TextBox();
    private TextBox phoneTextBox = new TextBox();

    //Table
    private CellTable<Customer> table = new CellTable<>();
    private ListDataProvider<Customer> dataProvider = new ListDataProvider<>();
    private List<Customer> list = dataProvider.getList();

    //Table columns
    private TextColumn<Customer> idColumn = new TextColumn<Customer>() {
        @Override
        public String getValue(Customer object) {
            return String.valueOf(object.getCustomerId());
        }
    };
    private TextColumn<Customer> surnameColumn = new TextColumn<Customer>() {
        @Override
        public String getValue(Customer object) {
            return object.getSurname();

        }
    };
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
    private Column<Customer, String> editColumn = new Column<Customer, String>(buttonCell) {
        @Override
        public String getValue(Customer object) {
            return "Edit";
        }

    };
    private Column<Customer, String> deleteColumn = new Column<Customer, String>(buttonCell) {
        @Override
        public String getValue(Customer object) {
            return "Delete";
        }

    };

    //Labels
    private Label idLabel = new Label("Id");
    private Label firstNameLabel = new Label("First name");
    private Label passportLabel = new Label("Passport");
    private Label phoneLabel = new Label("Phone");
    private Label surnameLabel = new Label("Surname");
    private Label infoLabel = new Label("");

    CustomerView() {
        panel.setStyleName("myPanel");
        inputGrid.setStyleName("input");
        infoLabel.setStyleName("infoLabel");
        panel.setSpacing(10);
        inputGrid.setCellSpacing(10);
        RootPanel.get().add(panel);
        table.addColumn(idColumn, "Id");
        table.addColumn(firstnameColumn, "First name");
        table.addColumn(surnameColumn, "Surname");
        table.addColumn(passportColumn, "Passport");
        table.addColumn(phoneColumn, "Phone");
        table.addColumn(editColumn, "Edit");
        table.addColumn(deleteColumn, "Delete");
    }

    void init() {
        idTextBox.setEnabled(false);
        inputGrid.setWidget(0, 0, idLabel);
        inputGrid.setWidget(0, 1, idTextBox);
        inputGrid.setWidget(1, 0, firstNameLabel);
        inputGrid.setWidget(1, 1, firstNameTextBox);
        inputGrid.setWidget(2, 0, surnameLabel);
        inputGrid.setWidget(2, 1, surnameTextBox);
        inputGrid.setWidget(3, 0, passportLabel);
        inputGrid.setWidget(3, 1, passportTextBox);
        inputGrid.setWidget(4, 0, phoneLabel);
        inputGrid.setWidget(4, 1, phoneTextBox);
        inputGrid.setWidget(5, 0, addButton);

        panel.add(inputGrid);
        panel.add(infoLabel);
        panel.add(table);
        refreshTable();
        dataProvider.addDataDisplay(table);
        addButton.addClickHandler(event -> {
            Customer customer = new Customer();
            if (!addButton.getText().equals("Add")) {
                customer.setCustomerId(Long.valueOf(idTextBox.getText()));
            }
            if (!firstNameTextBox.getText().matches(ConstantProvider.FIRST_NAME_PATTERN)) {
                infoLabel.setText("Incorrect first name: (3-15 english letters)");
            } else if (!surnameTextBox.getText().matches(ConstantProvider.LAST_NAME_PATTERN)) {
                infoLabel.setText("Incorrect surname: (3-15 english letters)");
            } else if (!passportTextBox.getText().matches(ConstantProvider.PASSPORT_PATTERN)) {
                infoLabel.setText("Incorrect passport: (10 digits)");
            } else if (!phoneTextBox.getText().matches(ConstantProvider.PHONE_PATTERN)) {
                infoLabel.setText("Incorrect phone: (3-20 digits and symbols)");
            } else {
                customer.setFirstName(firstNameTextBox.getText());
                customer.setSurname(surnameTextBox.getText());
                customer.setPhone(phoneTextBox.getText());
                customer.setPassport(Long.valueOf(passportTextBox.getText()));

                if (addButton.getText().equals("Add")) {
                    restService.saveCustomer(customer, new MethodCallback<Response>() {
                        @Override
                        public void onFailure(Method method, Throwable exception) {
                            Window.alert("Fail to save customer: " + exception.getMessage());
                        }

                        @Override
                        public void onSuccess(Method method, Response response) {
                            messageBox.setGlassEnabled(true);
                            messageBox.setText(response.getMessage());
                            Timer timer = new Timer() {
                                @Override
                                public void run() {
                                    messageBox.hide();
                                }
                            };
                            timer.schedule(2000);
                            messageBox.center();
                            infoLabel.setText("");
                            refreshTable();
                        }
                    });
                } else {
                    restService.updateCustomer(customer, new MethodCallback<Response>() {
                        @Override
                        public void onFailure(Method method, Throwable exception) {
                            Window.alert("Fail to update customer: " + exception.getMessage());
                        }

                        @Override
                        public void onSuccess(Method method, Response response) {
                            messageBox.setGlassEnabled(true);
                            messageBox.setText(response.getMessage());
                            Timer timer = new Timer() {
                                @Override
                                public void run() {
                                    messageBox.hide();
                                }
                            };
                            timer.schedule(2000);
                            messageBox.center();
                            infoLabel.setText("");
                            refreshTable();
                        }
                    });
                }
                idTextBox.setText("");
                firstNameTextBox.setText("");
                surnameTextBox.setText("");
                passportTextBox.setText("");
                phoneTextBox.setText("");
                infoLabel.setText("");
                addButton.setText("Add");
            }
        });
        editColumn.setFieldUpdater((index, object, value) ->
        {
            idTextBox.setText(String.valueOf(object.getCustomerId()));
            idTextBox.setEnabled(false);
            firstNameTextBox.setText(object.getFirstName());
            surnameTextBox.setText(object.getSurname());
            passportTextBox.setText(String.valueOf(object.getPassport()));
            phoneTextBox.setText(object.getPhone());
            addButton.setText("Submit");
        });
        deleteColumn.setFieldUpdater(((index, object, value) -> restService.deleteCustomer(object.getCustomerId(), new MethodCallback<Response>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Fail to delete: " + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, Response response) {
                messageBox.setGlassEnabled(true);
                messageBox.setText(response.getMessage());
                Timer timer = new Timer() {
                    @Override
                    public void run() {
                        messageBox.hide();
                    }
                };
                timer.schedule(2000);
                messageBox.center();
                infoLabel.setText("");
                refreshTable();
            }
        })));
    }

    private void refreshTable() {
        restService.listCustomer(new MethodCallback<List<Customer>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Fail to get list: " + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<Customer> response) {
                list.clear();
                list.addAll(response);
            }
        });
    }

}

