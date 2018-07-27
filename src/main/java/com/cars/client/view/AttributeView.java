package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.shared.models.Attribute;
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

public class AttributeView extends Composite {
    Button addButton = new Button("Add");
    //Text fields
    TextBox id = new TextBox();
    TextBox name = new TextBox();
    //Table
    CellTable<Attribute> table = new CellTable<>();
    ListDataProvider<Attribute> dataProvider = new ListDataProvider<>();
    List<Attribute> list = dataProvider.getList();
    //Table columns
    TextColumn<Attribute> idColumn = new TextColumn<Attribute>() {
        @Override
        public String getValue(Attribute object) {
            return String.valueOf(object.getIdAttribute());
        }
    };
    TextColumn<Attribute> nameColumn = new TextColumn<Attribute>() {
        @Override
        public String getValue(Attribute object) {
            return object.getName();
        }
    };
    ButtonCell buttonCell = new ButtonCell();
    Column<Attribute, String> bColumn = new Column<Attribute, String>(buttonCell) {
        @Override
        public String getValue(Attribute object) {
            return "EDIT";
        }

    };
    Column<Attribute, String> deleteColumn = new Column<Attribute, String>(buttonCell) {
        @Override
        public String getValue(Attribute object) {
            return "DELETE";
        }

    };
    //Labels
    Label idLabel = new Label("ID");
    Label nameLabel = new Label("Name");
    private GWTService restService = (GWTService) GWT.create(GWTService.class);
    VerticalPanel panel = new VerticalPanel();
    Grid grid = new Grid(3, 2);
    Label infoLabel = new Label("");

    AttributeView() {
        RootPanel.get().add(panel);
        table.addColumn(idColumn, "ID");
        table.addColumn(nameColumn, "Name");
        table.addColumn(bColumn, "Edit");
        table.addColumn(deleteColumn, "Delete");
    }

    void refreshTable() {
        restService.listAttributes(new MethodCallback<List<Attribute>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
            }

            @Override
            public void onSuccess(Method method, List<Attribute> response) {
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
        grid.setWidget(2, 0, addButton);
        grid.setWidget(2, 1, infoLabel);

        panel.add(grid);
        panel.add(table);
        refreshTable();
        dataProvider.addDataDisplay(table);
        addButton.addClickHandler(event -> {
            Attribute attribute = new Attribute();
            if (!addButton.getText().equals("Add")) {
                attribute.setIdAttribute(Long.valueOf(id.getText()));
            }
            attribute.setName(name.getText());
            if (addButton.getText().equals("Add")) {
                restService.saveAttribute(attribute, new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {

                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        refreshTable();
                    }
                });
            } else {
                restService.updateAttribute(attribute, new MethodCallback<Void>() {
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
            addButton.setText("Add");
        });
        bColumn.setFieldUpdater((index, object, value) ->
        {
            id.setText(String.valueOf(object.getIdAttribute()));
            id.setEnabled(false);
            name.setText(object.getName());
            addButton.setText("Submit");
        });
        deleteColumn.setFieldUpdater(((index, object, value) -> {
            restService.deleteAttribute(object.getIdAttribute(), new MethodCallback<Void>() {
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