package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.shared.models.Purchase;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.view.client.ListDataProvider;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class PurchaseView extends Composite {
    Label label = new Label();
    private GWTService restService = (GWTService) GWT.create(GWTService.class);
    private Button addButton = new Button("Add");
    //Text fields
    private TextBox id = new TextBox();
    private DatePicker name = new DatePicker();
    private TextBox payment = new TextBox();
    //Table
    private CellTable<Purchase> table = new CellTable<>();
    private ListDataProvider<Purchase> dataProvider = new ListDataProvider<>();
    private List<Purchase> list = dataProvider.getList();
    //Table columns
    private TextColumn<Purchase> idColumn = new TextColumn<Purchase>() {
        @Override
        public String getValue(Purchase object) {
            return String.valueOf(object.getIdCustomer());
        }
    };
    private TextColumn<Purchase> dateColumn = new TextColumn<Purchase>() {
        @Override
        public String getValue(Purchase object) {
            return object.getDate().toString();
        }
    };
    private TextColumn<Purchase> paymentColumn = new TextColumn<Purchase>() {
        @Override
        public String getValue(Purchase object) {
            return object.getPayment();
        }
    };
    private TextColumn<Purchase> autoColumn = new TextColumn<Purchase>() {
        @Override
        public String getValue(Purchase object) {
            return object.getIdAutomobile().getModel() + object.getIdAutomobile().getName();
        }
    };
    private TextColumn<Purchase> customerColumn = new TextColumn<Purchase>() {
        @Override
        public String getValue(Purchase object) {
            return object.getIdCustomer().getFullName();
        }
    };
    private ButtonCell buttonCell = new ButtonCell();
    private Column<Purchase, String> editColumn = new Column<Purchase, String>(buttonCell) {
        @Override
        public String getValue(Purchase object) {
            return "EDIT";
        }

    };
    private Column<Purchase, String> deleteColumn = new Column<Purchase, String>(buttonCell) {
        @Override
        public String getValue(Purchase object) {
            return "DELETE";
        }

    };
    //Labels
    private Label idLabel = new Label("ID");
    private Label autoLabel = new Label("Automobile");
    private Label customerLabel = new Label("Customer");
    private Label dateLabel = new Label("Date");
    private Label payLabel = new Label("Payment");


    PurchaseView(Panel panel) {
        table.addColumn(idColumn, "ID");
        table.addColumn(autoColumn, "Automobile");
        table.addColumn(customerColumn, "Customer");
        table.addColumn(dateColumn, "Date");
        table.addColumn(paymentColumn, "Payment");
        table.addColumn(editColumn, "Edit");
        table.addColumn(deleteColumn, "Delete");
        panel.add(idLabel);
        id.setEnabled(false);
        panel.add(id);

        panel.add(addButton);

        refreshTable();
        dataProvider.addDataDisplay(table);
        panel.add(table);
        panel.add(label);

       /* addButton.addClickHandler(event -> {
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
        }));*/
    }


    private void refreshTable() {
        restService.listPurchase(new MethodCallback<List<Purchase>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Fail" + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<Purchase> response) {
                Window.alert("Ok");
            }
        });
    }
}
