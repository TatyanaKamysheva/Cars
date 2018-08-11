package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.client.view.listboxes.AttributeListBox;
import com.cars.client.view.listboxes.AutomobileListBox;
import com.cars.shared.ConstantProvider;
import com.cars.shared.models.Response;
import com.cars.shared.models.UserLoginInfo;
import com.cars.shared.models.entities.Attribute;
import com.cars.shared.models.entities.Automobile;
import com.cars.shared.models.entities.Equipment;
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

public class EquipmentView extends Composite {
    VerticalPanel panel = new VerticalPanel();

    //ListBoxes
    private AutomobileListBox automobileListBox = new AutomobileListBox();
    private AttributeListBox attributeListBox = new AttributeListBox();
    private AutomobileListBox filterBox = new AutomobileListBox();

    private Grid grid = new Grid(6, 2);
    private Grid filterGrid = new Grid(1, 4);
    private DialogBox messageBox = new DialogBox();

    private GWTService restService = (GWTService) GWT.create(GWTService.class);
    private Button addButton = new Button("Add");
    //Text fields
    private TextBox idTextBox = new TextBox();
    private TextBox valueTextBox = new TextBox();
    private TextBox modificationTextBox = new TextBox();
    //Table
    private CellTable<Equipment> table = new CellTable<>();
    private ListDataProvider<Equipment> dataProvider = new ListDataProvider<>();
    private List<Equipment> list = dataProvider.getList();
    //Table columns
    private TextColumn<Equipment> idColumn = new TextColumn<Equipment>() {
        @Override
        public String getValue(Equipment object) {
            return String.valueOf(object.getIdEquipment());
        }
    };
    private TextColumn<Equipment> autoColumn = new TextColumn<Equipment>() {
        @Override
        public String getValue(Equipment object) {
            return String.valueOf(object.getAutomobile().getManufacturer() + " " + object.getAutomobile().getModel());
        }
    };
    private TextColumn<Equipment> attrColumn = new TextColumn<Equipment>() {
        @Override
        public String getValue(Equipment object) {
            return String.valueOf(object.getAttribute().getName());
        }
    };
    private TextColumn<Equipment> valueColumn = new TextColumn<Equipment>() {
        @Override
        public String getValue(Equipment object) {
            return object.getValue();
        }
    };
    private TextColumn<Equipment> modificationColumn = new TextColumn<Equipment>() {
        @Override
        public String getValue(Equipment object) {
            return object.getModificationName();
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
    private Column<Equipment, String> editColumn = new Column<Equipment, String>(buttonCell) {
        @Override
        public String getValue(Equipment object) {
            return "Edit";
        }

    };
    private Column<Equipment, String> deleteColumn = new Column<Equipment, String>(buttonCell) {
        @Override
        public String getValue(Equipment object) {
            return "Delete";
        }

    };
    private Label idLabel = new Label("Id");
    private Label autoLabel = new Label("Automobile");
    private Label attributeLabel = new Label("Attribute");
    private Label valueLabel = new Label("Value");
    private Label infoLabel = new Label("");

    private Button filter = new Button("Filter");
    private Button close = new Button("Reset");

    EquipmentView(UserLoginInfo userLoginInfo) {
        panel.setStyleName("myPanel");
        grid.setStyleName("input");
        infoLabel.setStyleName("infoLabel");
        RootPanel.get().add(panel);
        table.addColumn(idColumn, "ID");
        table.addColumn(autoColumn, "Automobile");
        table.addColumn(attrColumn, "Attribute");
        table.addColumn(valueColumn, "Value");
        table.addColumn(modificationColumn, "Modification");
        switch (userLoginInfo.getRole()) {
            case Admin:
                table.addColumn(editColumn, "Edit");
                table.addColumn(deleteColumn, "Delete");
                break;
            case Supervisor:
                table.addColumn(editColumn, "Edit");
                table.addColumn(deleteColumn, "Delete");
                break;
        }

    }

    void init() {
        setAuto();
        setAttributes();
        grid.setCellSpacing(10);
        idTextBox.setEnabled(false);
        grid.setWidget(0, 0, idLabel);
        grid.setWidget(0, 1, idTextBox);
        grid.setWidget(1, 0, autoLabel);
        grid.setWidget(1, 1, automobileListBox);
        grid.setWidget(2, 0, attributeLabel);
        grid.setWidget(2, 1, attributeListBox);
        grid.setWidget(3, 0, valueLabel);
        grid.setWidget(3, 1, valueTextBox);
        grid.setWidget(4, 0, new Label("Modification"));
        grid.setWidget(4, 1, modificationTextBox);
        grid.setWidget(5, 0, addButton);

        filterGrid.setWidget(0, 0, filterBox);
        filterGrid.setWidget(0, 1, filter);
        filterGrid.setWidget(0, 2, close);

        panel.add(grid);
        panel.add(infoLabel);
        panel.add(filterGrid);
        panel.add(table);
        refreshTable();
        dataProvider.addDataDisplay(table);
        close.addClickHandler(event -> refreshTable());
        addButton.addClickHandler(event -> {
            Equipment equipment = new Equipment();
            if (!addButton.getText().equals("Add")) {
                equipment.setIdEquipment(Long.valueOf(idTextBox.getText()));
            }
            if (valueTextBox.getText().isEmpty()) {
                infoLabel.setText("Value should not be empty");
            } else if (!modificationTextBox.getText().matches(ConstantProvider.STRING_PATTERN)) {
                infoLabel.setText("Invalid modification: 1-20 english letters");
            } else {
                equipment.setValue(valueTextBox.getText());
                equipment.setAttribute(attributeListBox.getSelected());
                equipment.setAutomobile(automobileListBox.getSelected());
                equipment.setModificationName(modificationTextBox.getText());
                if (addButton.getText().equals("Add")) {
                    restService.saveEquip(equipment, new MethodCallback<Response>() {
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
                    restService.updateEquip(equipment, new MethodCallback<Response>() {
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
                valueTextBox.setText("");
                modificationTextBox.setText("");
                addButton.setText("Add");
                infoLabel.setText("");
            }
        });

        editColumn.setFieldUpdater((index, object, values) ->
        {
            idTextBox.setText(String.valueOf(object.getIdEquipment()));
            idTextBox.setEnabled(false);
            valueTextBox.setText(object.getValue());
            modificationTextBox.setText(object.getModificationName());
            addButton.setText("Submit");
        });
        deleteColumn.setFieldUpdater(((index, object, value) -> restService.deleteEquip(object.getIdEquipment(), new MethodCallback<Response>() {
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
        filter.addClickHandler(event -> restService.filterEquipment(filterBox.getSelected().getAutomobileId(), new MethodCallback<List<Equipment>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Fail to filter: " + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<Equipment> response) {
                list.clear();
                list.addAll(response);
            }
        }));
    }

    private void setAuto() {
        restService.listAuto(new MethodCallback<List<Automobile>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {

            }

            @Override
            public void onSuccess(Method method, List<Automobile> automobiles) {
                automobileListBox.clear();
                automobileListBox.setAutomobiles(automobiles);
                filterBox.clear();
                filterBox.setAutomobiles(automobiles);
            }
        });
    }

    private void setAttributes() {
        restService.listAttributes(new MethodCallback<List<Attribute>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {

            }

            @Override
            public void onSuccess(Method method, List<Attribute> response) {
                attributeListBox.clear();
                attributeListBox.setAttributes(response);
            }
        });
    }

    private void refreshTable() {
        restService.listEquip(new MethodCallback<List<Equipment>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
            }

            @Override
            public void onSuccess(Method method, List<Equipment> response) {
                list.clear();
                list.addAll(response);
            }
        });
    }
}
