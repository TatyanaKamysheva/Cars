package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.client.view.popup.ModificationPopup;
import com.cars.shared.ConstantProvider;
import com.cars.shared.Roles;
import com.cars.shared.models.Response;
import com.cars.shared.models.UserLoginInfo;
import com.cars.shared.models.entities.Automobile;
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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.ListDataProvider;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class AutomobileView extends Composite {

    //Panels and grids
    VerticalPanel panel = new VerticalPanel();
    private Grid grid = new Grid(4, 2);

    //REST service
    private GWTService restService = (GWTService) GWT.create(GWTService.class);

    //Buttons
    private Button addButton = new Button("Add");

    //Text fields
    private TextBox idTextBox = new TextBox();
    private TextBox modelTextBox = new TextBox();
    private TextBox manufacturerTextBox = new TextBox();
    //Table
    private CellTable<Automobile> table = new CellTable<>();
    private ListDataProvider<Automobile> dataProvider = new ListDataProvider<>();
    private List<Automobile> list = dataProvider.getList();

    //Table columns
    private TextColumn<Automobile> idColumn = new TextColumn<Automobile>() {
        @Override
        public String getValue(Automobile object) {
            return String.valueOf(object.getAutomobileId());
        }
    };
    private TextColumn<Automobile> modelColumn = new TextColumn<Automobile>() {
        @Override
        public String getValue(Automobile object) {
            return object.getModel();
        }
    };
    private TextColumn<Automobile> manufacturerColumn = new TextColumn<Automobile>() {
        @Override
        public String getValue(Automobile object) {
            return object.getManufacturer();
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
    private Column<Automobile, String> editColumn = new Column<Automobile, String>(buttonCell) {
        @Override
        public String getValue(Automobile object) {
            return "Edit";
        }

    };
    private Column<Automobile, String> deleteColumn = new Column<Automobile, String>(buttonCell) {
        @Override
        public String getValue(Automobile object) {
            return "Delete";
        }

    };
    private Column<Automobile, String> modificationColumn = new Column<Automobile, String>(buttonCell) {
        @Override
        public String getValue(Automobile object) {
            return "Modifications";
        }

    };

    //Labels
    private Label idLabel = new Label("Id");
    private Label modelLabel = new Label("Model");
    private Label manufacturerLabel = new Label("Manufacturer");
    private Label infoLabel = new Label();

    private UserLoginInfo userLoginInfo;
    private DialogBox messageBox = new DialogBox();


    AutomobileView(UserLoginInfo userLoginInfo) {
        panel.setStyleName("myPanel");
        grid.setStyleName("input");
        infoLabel.setStyleName("infoLabel");
        this.userLoginInfo = userLoginInfo;
        RootPanel.get().add(panel);
        table.addColumn(idColumn, "ID");
        table.addColumn(manufacturerColumn, "Manufacturer");
        table.addColumn(modelColumn, "Model");
        table.addColumn(modificationColumn, "Modifications");
        if (userLoginInfo.getRole().equals(Roles.Admin) || userLoginInfo.getRole().equals(Roles.Supervisor)) {
            table.addColumn(editColumn, "Edit");
            table.addColumn(deleteColumn, "Delete");
        }
    }

    private void refreshTable() {
        restService.listAuto(new MethodCallback<List<Automobile>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Fail to get list: " + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<Automobile> response) {
                list.clear();
                list.addAll(response);
            }
        });
    }

    public void init() {

        if (userLoginInfo.getRole().equals(Roles.Admin) || userLoginInfo.getRole().equals(Roles.Supervisor)) {
            grid.setCellSpacing(10);
            idTextBox.setEnabled(false);
            grid.setWidget(0, 0, idLabel);
            grid.setWidget(0, 1, idTextBox);
            grid.setWidget(1, 0, manufacturerLabel);
            grid.setWidget(1, 1, manufacturerTextBox);
            grid.setWidget(2, 0, modelLabel);
            grid.setWidget(2, 1, modelTextBox);
            grid.setWidget(3, 0, addButton);
            panel.add(grid);
        }
        panel.setSpacing(10);
        panel.add(infoLabel);
        panel.add(table);
        refreshTable();
        dataProvider.addDataDisplay(table);
        addButton.addClickHandler(event -> {
            Automobile automobile = new Automobile();
            if (!addButton.getText().equals("Add")) {
                automobile.setAutomobileId(Long.valueOf(idTextBox.getText()));
            }
            if (!manufacturerTextBox.getText().matches(ConstantProvider.STRING_PATTERN)) {
                infoLabel.setText("Incorrect manufacturer: (1-20 english letters and digits)");
            } else if (!modelTextBox.getText().matches(ConstantProvider.STRING_PATTERN)) {
                infoLabel.setText("Incorrect model: (1-20 english letters and digits)");
            } else {
                automobile.setModel(modelTextBox.getText());
                automobile.setManufacturer(manufacturerTextBox.getText());
                if (addButton.getText().equals("Add")) {
                    restService.saveAuto(automobile, new MethodCallback<Response>() {
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
                    restService.updateAuto(automobile, new MethodCallback<Response>() {
                        @Override
                        public void onFailure(Method method, Throwable exception) {
                            Window.alert("Fail to update: " + exception.getMessage());
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
                manufacturerTextBox.setText("");
                modelTextBox.setText("");
                addButton.setText("Add");
            }
        });
        editColumn.setFieldUpdater((index, object, value) ->
        {
            idTextBox.setText(String.valueOf(object.getAutomobileId()));
            idTextBox.setEnabled(false);
            manufacturerTextBox.setText(object.getManufacturer());
            modelTextBox.setText(object.getModel());
            addButton.setText("Submit");
        });
        deleteColumn.setFieldUpdater(((index, object, value) -> restService.deleteAuto(object.getAutomobileId(), new MethodCallback<Response>() {
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

        modificationColumn.setFieldUpdater(((index, object, value) -> {
            final ModificationPopup popup = new ModificationPopup(object.getAutomobileId());
            popup.center();
            popup.show();
        }));
    }
}