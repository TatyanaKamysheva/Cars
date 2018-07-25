package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.client.view.popup.AutomobilePopup;
import com.cars.shared.models.Automobile;
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

public class AutomobileView extends Composite {
    VerticalPanel panel = new VerticalPanel();
    private GWTService restService = (GWTService) GWT.create(GWTService.class);
    private Button addButton = new Button("Add");
    //Text fields
    private TextBox id = new TextBox();
    private TextBox model = new TextBox();
    private TextBox name = new TextBox();
    private TextBox gears = new TextBox();
    private TextBox driveType = new TextBox();
    //Table
    private CellTable<Automobile> table = new CellTable<>();
    private ListDataProvider<Automobile> dataProvider = new ListDataProvider<>();
    private List<Automobile> list = dataProvider.getList();
    //Table columns
    private TextColumn<Automobile> idColumn = new TextColumn<Automobile>() {
        @Override
        public String getValue(Automobile object) {
            return String.valueOf(object.getIdAutomobile());
        }
    };
    private TextColumn<Automobile> modelColumn = new TextColumn<Automobile>() {
        @Override
        public String getValue(Automobile object) {
            return object.getModel();
        }
    };
    private TextColumn<Automobile> nameColumn = new TextColumn<Automobile>() {
        @Override
        public String getValue(Automobile object) {
            return object.getName();
        }
    };
    private TextColumn<Automobile> gearsColumn = new TextColumn<Automobile>() {
        @Override
        public String getValue(Automobile object) {
            return String.valueOf(object.getGears());
        }
    };
    private TextColumn<Automobile> driveColumn = new TextColumn<Automobile>() {
        @Override
        public String getValue(Automobile object) {
            return object.getDrive_type();
        }
    };
    private ButtonCell buttonCell = new ButtonCell();
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
    private Column<Automobile, String> comfortColumn = new Column<Automobile, String>(buttonCell) {
        @Override
        public String getValue(Automobile object) {
            return "Comfort";
        }

    };
    private Column<Automobile, String> standartColumn = new Column<Automobile, String>(buttonCell) {
        @Override
        public String getValue(Automobile object) {
            return "Standart";
        }

    };
    //Labels
    private Label idLabel = new Label("ID");
    private Label modelLabel = new Label("Model");
    private Label nameLabel = new Label("Name");
    private Label gearLabel = new Label("Gears");
    private Label driveLabel = new Label("Drive type");

    AutomobileView(UserLoginInfo userLoginInfo) {
        RootPanel.get().add(panel);
        switch (userLoginInfo.getRole()) {
            case Admin:
                table.addColumn(idColumn, "ID");
                table.addColumn(editColumn, "Edit");
                table.addColumn(deleteColumn, "Delete");
            case Seller:
                table.addColumn(comfortColumn, "Comfort");
                table.addColumn(standartColumn, "Standart");
        }

        table.addColumn(modelColumn, "Model");
        table.addColumn(nameColumn, "Name");
        table.addColumn(gearsColumn, "Gears");
        table.addColumn(driveColumn, "Drive type");
    }

    private void refreshTable() {
        restService.listAuto(new MethodCallback<List<Automobile>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
            }

            @Override
            public void onSuccess(Method method, List<Automobile> response) {
                list.clear();
                list.addAll(response);
            }
        });
    }

    public void init() {
        panel.add(idLabel);
        id.setEnabled(false);
        panel.add(id);

        panel.add(modelLabel);
        panel.add(model);

        panel.add(nameLabel);
        panel.add(name);

        panel.add(gearLabel);
        panel.add(gears);

        panel.add(driveLabel);
        panel.add(driveType);

        panel.add(addButton);
        panel.add(table);
        refreshTable();
        dataProvider.addDataDisplay(table);
        addButton.addClickHandler(event -> {
            Automobile automobile = new Automobile();
            if (!addButton.getText().equals("Add")) {
                automobile.setIdAutomobile(Long.valueOf(id.getText()));
            }
            automobile.setDrive_type(driveType.getText());
            automobile.setGears(Long.valueOf(gears.getText()));
            automobile.setModel(model.getText());
            automobile.setName(name.getText());
            if (addButton.getText().equals("Add")) {

                restService.saveAuto(automobile, new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {

                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        refreshTable();
                    }
                });
            } else {
                restService.updateAuto(automobile, new MethodCallback<Void>() {
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
            gears.setText("");
            driveType.setText("");
            model.setText("");
            addButton.setText("Add");
        });
        editColumn.setFieldUpdater((index, object, value) ->
        {
            id.setText(String.valueOf(object.getIdAutomobile()));
            id.setEnabled(false);
            name.setText(object.getName());
            gears.setText(String.valueOf(object.getGears()));
            driveType.setText(object.getDrive_type());
            model.setText(object.getModel());
            addButton.setText("Submit");
        });
        deleteColumn.setFieldUpdater(((index, object, value) -> {
            restService.deleteAuto(object.getIdAutomobile(), new MethodCallback<Void>() {
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

        comfortColumn.setFieldUpdater(((index, object, value) -> {
            new AutomobilePopup(object.getIdAutomobile()).show();
        }));

    }
}