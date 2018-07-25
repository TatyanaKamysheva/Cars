package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.client.view.listboxes.AttributeListBox;
import com.cars.client.view.listboxes.AutomobileListBox;
import com.cars.shared.models.Attribute;
import com.cars.shared.models.Automobile;
import com.cars.shared.models.Equipment;
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

public class EquipmentView extends Composite {
    AutomobileListBox automobileListBox = new AutomobileListBox();
    AttributeListBox attributeListBox = new AttributeListBox();
    private GWTService restService = (GWTService) GWT.create(GWTService.class);
    private Button addButton = new Button("Add");
    //Text fields
    private TextBox id = new TextBox();
    private TextBox value = new TextBox();
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
            return String.valueOf(object.getIdAutomobile().getModel() + " " + object.getIdAutomobile().getName());
        }
    };
    private TextColumn<Equipment> attrColumn = new TextColumn<Equipment>() {
        @Override
        public String getValue(Equipment object) {
            return String.valueOf(object.getIdAttribute().getName());
        }
    };
    private TextColumn<Equipment> valueColumn = new TextColumn<Equipment>() {
        @Override
        public String getValue(Equipment object) {
            return object.getValue();
        }
    };

    private ButtonCell buttonCell = new ButtonCell();
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

    private Label idLabel = new Label("ID");
    private Label autoLabel = new Label("Automobile");
    private Label attributeLabel = new Label("Attribute");
    private Label valueLabel = new Label("Value");


    EquipmentView(Panel panel) {
        table.addColumn(idColumn, "ID");
        table.addColumn(autoColumn, "Automobile");
        table.addColumn(attrColumn, "Attribute");
        table.addColumn(valueColumn, "Value");
        table.addColumn(editColumn, "Edit");
        table.addColumn(deleteColumn, "Delete");

        panel.add(idLabel);
        id.setEnabled(false);
        panel.add(id);

        panel.add(autoLabel);
        setAuto();
        panel.add(automobileListBox);

        panel.add(attributeLabel);
        setAttributes();
        panel.add(attributeListBox);

        panel.add(valueLabel);
        panel.add(value);
        panel.add(addButton);
        refreshTable();
        dataProvider.addDataDisplay(table);
        panel.add(table);
        addButton.addClickHandler(event -> {
            Equipment equipment = new Equipment();
            if (!addButton.getText().equals("Add")) {
                equipment.setIdEquipment(Long.valueOf(id.getText()));
            }
            equipment.setValue(value.getText());
            equipment.setIdAttribute(attributeListBox.getSelected());
            equipment.setIdAutomobile(automobileListBox.getSelected());
            if (addButton.getText().equals("Add")) {
                restService.saveEquip(equipment, new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        refreshTable();
                    }
                });
            } else {
                restService.updateEquip(equipment, new MethodCallback<Void>() {
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
            value.setText("");
            addButton.setText("Add");
        });
        editColumn.setFieldUpdater((index, object, valuez) ->
        {
            id.setText(String.valueOf(object.getIdEquipment()));
            id.setEnabled(false);
            value.setText(object.getValue());
            attributeListBox.setAttribute(object.getIdAttribute());
            addButton.setText("Submit");
        });
        deleteColumn.setFieldUpdater(((index, object, value) -> {
            restService.deleteEquip(object.getIdEquipment(), new MethodCallback<Void>() {
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

    private void setAuto() {
        restService.listAuto(new MethodCallback<List<Automobile>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {

            }

            @Override
            public void onSuccess(Method method, List<Automobile> automobiles) {
                automobileListBox.setAutomobiles(automobiles);
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
                attributeListBox.setAttributes(response);
            }
        });
    }
}
