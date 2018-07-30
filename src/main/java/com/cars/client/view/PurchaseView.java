package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.client.view.listboxes.AutomobileListBox;
import com.cars.client.view.listboxes.CustomerListBox;
import com.cars.shared.models.*;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.view.client.ListDataProvider;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.Date;
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
    VerticalPanel panel = new VerticalPanel();
    private TextColumn<Purchase> dateColumn = new TextColumn<Purchase>() {
        @Override
        public String getValue(Purchase object) {
            return object.getDate().toString();
        }
    };
    Manager manager = new Manager();
    private TextColumn<Purchase> autoColumn = new TextColumn<Purchase>() {
        @Override
        public String getValue(Purchase object) {
            return object.getIdAutomobile().getModel() + object.getIdAutomobile().getName();
        }
    };
    Grid grid = new Grid(7, 2);
    AutomobileListBox automobile = new AutomobileListBox();
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
    private Column<Purchase, String> editColumn = new Column<Purchase, String>(buttonCell) {
        @Override
        public String getValue(Purchase object) {
            return "Edit";
        }

    };
    private Column<Purchase, String> deleteColumn = new Column<Purchase, String>(buttonCell) {
        @Override
        public String getValue(Purchase object) {
            return "Delete";
        }

    };
    //Labels
    private Label idLabel = new Label("ID");
    private Label autoLabel = new Label("Automobile");
    private Label customerLabel = new Label("Customer");
    CustomerListBox customer = new CustomerListBox();
    Label modificationLabel = new Label("Modification");
    Label infoLabel = new Label("");
    UserLoginInfo userLoginInfo = new UserLoginInfo();
    //Table columns
    private TextColumn<Purchase> idColumn = new TextColumn<Purchase>() {
        @Override
        public String getValue(Purchase object) {
            return String.valueOf(object.getIdPurchase());
        }
    };
    private TextColumn<Purchase> modificationColumn = new TextColumn<Purchase>() {
        @Override
        public String getValue(Purchase object) {
            return object.getModification();
        }
    };
    private TextColumn<Purchase> customerColumn = new TextColumn<Purchase>() {
        @Override
        public String getValue(Purchase object) {
            return object.getIdCustomer().getFirstName() + " " + object.getIdCustomer().getSurname();
        }
    };
    private TextColumn<Purchase> mangerColumn = new TextColumn<Purchase>() {
        @Override
        public String getValue(Purchase object) {
            return object.getIdManager().getSurname();
        }
    };
    // private Label dateLabel = new Label("Date");
    private Label payLabel = new Label("Modification");

    //private DateTimeFormat dateTimeFormat=new DateTimeFormat("yyyy");
    PurchaseView(UserLoginInfo userLoginInfo) {
        panel.setStyleName("myPanel");
        grid.setStyleName("input");
        infoLabel.setStyleName("infoLabel");
        setAuto();
        setCustomer();
        this.userLoginInfo = userLoginInfo;
        RootPanel.get().add(panel);
        table.addColumn(idColumn, "ID");
        table.addColumn(autoColumn, "Automobile");
        table.addColumn(customerColumn, "Customer");
        table.addColumn(mangerColumn, "Manager");
        table.addColumn(dateColumn, "Date");
        //table.addColumn(modificationColumn, "Modification");
        switch (userLoginInfo.getRole()) {
            case Admin:
                table.addColumn(editColumn, "Edit");
                table.addColumn(deleteColumn, "Delete");
                break;
        }
    }

    void init() {
        grid.setCellSpacing(10);
        id.setEnabled(false);
       /* setAuto();
        setCustomer();*/
        grid.setWidget(0, 0, idLabel);
        grid.setWidget(0, 1, id);
        grid.setWidget(1, 0, customerLabel);
        grid.setWidget(1, 1, customer);
        grid.setWidget(2, 0, autoLabel);
        grid.setWidget(2, 1, automobile);
        grid.setWidget(3, 0, addButton);
        grid.setWidget(3, 1, infoLabel);

        panel.add(grid);
        panel.add(table);
        refreshTable();
        dataProvider.addDataDisplay(table);
        getManager();
        addButton.addClickHandler(event -> {
            Purchase purchase = new Purchase();
            if (!addButton.getText().equals("Add")) {
                purchase.setIdPurchase(Long.valueOf(id.getText()));
            }

            purchase.setIdManager(this.manager);
            purchase.setIdAutomobile(automobile.getSelected());
            purchase.setIdCustomer(customer.getSelected());
            Date date = new Date();
            purchase.setDate(date);
            purchase.setModification("Comfort");
            if (addButton.getText().equals("Add")) {
                restService.savePurchase(purchase, new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert("Fail to save");
                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        refreshTable();
                    }
                });
            } else {
                restService.updatePurchase(purchase, new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert("Fail to update");
                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        refreshTable();
                    }
                });
            }
            id.setText("");
            payment.setText("");
            addButton.setText("Add");
        });

        editColumn.setFieldUpdater((index, object, value) ->
        {
            id.setText(String.valueOf(object.getIdPurchase()));
            id.setEnabled(false);
            addButton.setText("Submit");
        });
        deleteColumn.setFieldUpdater(((index, object, value) -> {
            restService.deletePurchase(object.getIdPurchase(), new MethodCallback<Void>() {
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

    private void refreshTable() {
        restService.listPurchase(new MethodCallback<List<Purchase>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {

                Window.alert("Fail to get list: " + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<Purchase> response) {

                list.clear();
                list.addAll(response);
            }
        });
    }

    private void setAuto() {
        restService.listAuto(new MethodCallback<List<Automobile>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {

            }

            @Override
            public void onSuccess(Method method, List<Automobile> automobiles) {
                automobile.setAutomobiles(automobiles);
            }
        });
    }

    private void setCustomer() {
        restService.listCustomer(new MethodCallback<List<Customer>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
            }

            @Override
            public void onSuccess(Method method, List<Customer> customers) {
                customer.setCustomers(customers);
            }
        });
    }

    void getManager() {
        restService.getManager(userLoginInfo.getEmployeeId(), new MethodCallback<Manager>() {
            @Override
            public void onFailure(Method method, Throwable exception) {

            }

            @Override
            public void onSuccess(Method method, Manager response) {
                manager = response;
            }
        });
    }


}
