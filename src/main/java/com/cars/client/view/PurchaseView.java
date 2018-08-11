package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.client.view.listboxes.AutomobileListBox;
import com.cars.client.view.listboxes.CustomerListBox;
import com.cars.client.view.popup.ModificationPopup;
import com.cars.shared.models.Modification;
import com.cars.shared.models.Response;
import com.cars.shared.models.UserLoginInfo;
import com.cars.shared.models.entities.Automobile;
import com.cars.shared.models.entities.Customer;
import com.cars.shared.models.entities.Employee;
import com.cars.shared.models.entities.Purchase;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
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

import java.util.Date;
import java.util.List;

public class PurchaseView extends Composite {
    private GWTService restService = (GWTService) GWT.create(GWTService.class);
    private Button addButton = new Button("Add");
    private DialogBox messageBox = new DialogBox();
    //Text fields
    private TextBox id = new TextBox();
    //Table
    private CellTable<Purchase> table = new CellTable<>();
    private ListDataProvider<Purchase> dataProvider = new ListDataProvider<>();
    private List<Purchase> list = dataProvider.getList();
    VerticalPanel panel = new VerticalPanel();
    private TextColumn<Purchase> dateColumn = new TextColumn<Purchase>() {
        @Override
        public String getValue(Purchase object) {
            return DateTimeFormat.getLongDateTimeFormat().format(object.getDate());
        }
    };
    private Employee manager = new Employee();
    private TextColumn<Purchase> autoColumn = new TextColumn<Purchase>() {
        @Override
        public String getValue(Purchase object) {
            return object.getAutomobile().getModel() + " " + object.getAutomobile().getManufacturer();
        }
    };
    private Grid grid = new Grid(7, 2);
    private AutomobileListBox automobile = new AutomobileListBox();
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
    private Column<Purchase, String> detailsColumn = new Column<Purchase, String>(buttonCell) {
        @Override
        public String getValue(Purchase object) {
            return "Details";
        }

    };
    //Labels
    private Label idLabel = new Label("ID");
    private Label autoLabel = new Label("Automobile");
    private Label customerLabel = new Label("Customer");
    private CustomerListBox customer = new CustomerListBox();
    private ListBox modificationListBox = new ListBox();
    private Label infoLabel = new Label("");
    private UserLoginInfo userLoginInfo;
    //Table columns
    private TextColumn<Purchase> idColumn = new TextColumn<Purchase>() {
        @Override
        public String getValue(Purchase object) {
            return String.valueOf(object.getPurchaseId());
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
            return object.getCustomer().getFirstName() + " " + object.getCustomer().getSurname();
        }
    };
    private TextColumn<Purchase> mangerColumn = new TextColumn<Purchase>() {
        @Override
        public String getValue(Purchase object) {
            return object.getEmployee().getSurname();
        }
    };

    PurchaseView(UserLoginInfo userLoginInfo) {
        setAuto();
        setCustomer();
        panel.setStyleName("myPanel");
        grid.setStyleName("input");
        infoLabel.setStyleName("infoLabel");
        this.userLoginInfo = userLoginInfo;
        RootPanel.get().add(panel);
        table.addColumn(idColumn, "ID");
        table.addColumn(autoColumn, "Automobile");
        table.addColumn(customerColumn, "Customer");
        table.addColumn(mangerColumn, "Employee");
        table.addColumn(dateColumn, "Date");
        table.addColumn(modificationColumn, "Modification");
        table.addColumn(detailsColumn, "Details");
        switch (userLoginInfo.getRole()) {
            case Admin:
                table.addColumn(editColumn, "Edit");
                table.addColumn(deleteColumn, "Delete");
                break;
        }
    }

    void init() {
        setModificationListBox();
        grid.setCellSpacing(10);
        id.setEnabled(false);
        grid.setWidget(0, 0, idLabel);
        grid.setWidget(0, 1, id);
        grid.setWidget(1, 0, customerLabel);
        grid.setWidget(1, 1, customer);
        grid.setWidget(2, 0, autoLabel);
        grid.setWidget(2, 1, automobile);
        grid.setWidget(3, 0, new Label("Modification"));
        grid.setWidget(3, 1, modificationListBox);
        grid.setWidget(4, 0, addButton);
        grid.setWidget(4, 1, infoLabel);

        panel.add(grid);
        panel.add(table);
        refreshTable();
        dataProvider.addDataDisplay(table);
        getManager();
        addButton.addClickHandler(event -> {
            Purchase purchase = new Purchase();
            if (!addButton.getText().equals("Add")) {
                purchase.setPurchaseId(Long.valueOf(id.getText()));
            }

            purchase.setEmployee(this.manager);
            purchase.setAutomobile(automobile.getSelected());
            purchase.setCustomer(customer.getSelected());
            Date date = new Date();
            purchase.setDate(date);
            purchase.setModification(modificationListBox.getSelectedValue());
            if (addButton.getText().equals("Add")) {
                restService.savePurchase(purchase, new MethodCallback<Response>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert("Fail to save: " + exception.getMessage());
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
                restService.updatePurchase(purchase, new MethodCallback<Response>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {

                        Window.alert("Fail to update " + exception.getMessage());
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
            id.setText("");
            addButton.setText("Add");
        });

        editColumn.setFieldUpdater((index, object, value) ->
        {
            id.setText(String.valueOf(object.getPurchaseId()));
            id.setEnabled(false);
            addButton.setText("Submit");
        });
        deleteColumn.setFieldUpdater(((index, object, value) ->
                restService.deletePurchase(object.getPurchaseId(), new MethodCallback<Response>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {

                        Window.alert("Fail to delete " + exception.getMessage());
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
        detailsColumn.setFieldUpdater((index, object, value) ->
        {
            final ModificationPopup popup = new ModificationPopup(
                    object.getAutomobile().getAutomobileId(),
                    object.getModification());
            popup.center();
            popup.show();
        });
        automobile.addChangeHandler(event -> setModificationListBox());
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

    private void getManager() {
        restService.getManager(userLoginInfo.getUserId(), new MethodCallback<Employee>() {
            @Override
            public void onFailure(Method method, Throwable exception) {

            }

            @Override
            public void onSuccess(Method method, Employee response) {
                manager = response;
            }
        });
    }

    private void setModificationListBox() {
        modificationListBox.clear();
        restService.listModifications(automobile.getSelected().getAutomobileId(), new MethodCallback<List<Modification>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Fail " + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<Modification> response) {
                for (Modification m : response) {
                    modificationListBox.addItem(m.getModification());
                }
            }
        });
    }
}