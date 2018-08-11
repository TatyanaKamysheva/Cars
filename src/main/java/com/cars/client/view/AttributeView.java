package com.cars.client.view;

import com.cars.client.rest.GWTService;
import com.cars.shared.ConstantProvider;
import com.cars.shared.models.Response;
import com.cars.shared.models.entities.Attribute;
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

public class AttributeView extends Composite {
    //Panels and grids
    VerticalPanel panel = new VerticalPanel();
    //Rest service
    private GWTService restService = (GWTService) GWT.create(GWTService.class);
    //Popup
    private DialogBox messageBox = new DialogBox();
    private Grid grid = new Grid(3, 2);

    //Buttons
    private Button addButton = new Button("Add");

    //Text fields
    private TextBox idTextBox = new TextBox();
    private TextBox nameTextBox = new TextBox();

    //Table
    private CellTable<Attribute> table = new CellTable<>();
    private ListDataProvider<Attribute> dataProvider = new ListDataProvider<>();
    private List<Attribute> list = dataProvider.getList();

    //Table columns
    private TextColumn<Attribute> idColumn = new TextColumn<Attribute>() {
        @Override
        public String getValue(Attribute object) {
            return String.valueOf(object.getAttributeId());
        }
    };
    private TextColumn<Attribute> nameColumn = new TextColumn<Attribute>() {
        @Override
        public String getValue(Attribute object) {
            return object.getName();
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
    private Column<Attribute, String> editColumn = new Column<Attribute, String>(buttonCell) {
        @Override
        public String getValue(Attribute object) {
            return "Edit";
        }

    };
    private Column<Attribute, String> deleteColumn = new Column<Attribute, String>(buttonCell) {
        @Override
        public String getValue(Attribute object) {
            return "Delete";
        }

    };

    //Labels
    private Label idLabel = new Label("ID");
    private Label nameLabel = new Label("Name");
    private Label infoLabel = new Label("");

    AttributeView() {
        panel.setSpacing(10);
        grid.setCellSpacing(10);
        panel.setStyleName("myPanel");
        grid.setStyleName("input");
        infoLabel.setStyleName("infoLabel");
        RootPanel.get().add(panel);
        table.addColumn(idColumn, "Id");
        table.addColumn(nameColumn, "Name");
        table.addColumn(editColumn, "Edit");
        table.addColumn(deleteColumn, "Delete");
    }

    void init() {
        idTextBox.setEnabled(false);
        grid.setWidget(0, 0, idLabel);
        grid.setWidget(0, 1, idTextBox);
        grid.setWidget(1, 0, nameLabel);
        grid.setWidget(1, 1, nameTextBox);
        grid.setWidget(2, 0, addButton);
        panel.add(grid);
        panel.add(infoLabel);
        panel.add(table);
        refreshTable();
        dataProvider.addDataDisplay(table);
        addButton.addClickHandler(event -> {
            Attribute attribute = new Attribute();
            if (!addButton.getText().equals("Add")) {
                attribute.setAttributeId(Long.valueOf(idTextBox.getText()));
            }
            if (!nameTextBox.getText().matches(ConstantProvider.STRING_PATTERN)) {
                infoLabel.setText("Invalid name: 1-20 english letters or digits");
            } else {
                attribute.setName(nameTextBox.getText());
                if (addButton.getText().equals("Add")) {
                    restService.saveAttribute(attribute, new MethodCallback<Response>() {
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
                    restService.updateAttribute(attribute, new MethodCallback<Response>() {
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
                nameTextBox.setText("");
                addButton.setText("Add");
            }
        });
        editColumn.setFieldUpdater((index, object, value) ->
        {
            idTextBox.setText(String.valueOf(object.getAttributeId()));
            idTextBox.setEnabled(false);
            nameTextBox.setText(object.getName());
            addButton.setText("Submit");
            infoLabel.setText("");
        });
        deleteColumn.setFieldUpdater(((index, object, value) ->
                restService.deleteAttribute(object.getAttributeId(), new MethodCallback<Response>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert("Fail to delete: " + exception.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, Response response) {
                        messageBox.setText(response.getMessage());
                        Timer timer = new Timer() {
                            @Override
                            public void run() {
                                messageBox.hide();
                            }
                        };
                        timer.schedule(2000);
                        messageBox.center();
                        refreshTable();
                    }
                })));
    }

    private void refreshTable() {
        restService.listAttributes(new MethodCallback<List<Attribute>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Fail to get list: " + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<Attribute> response) {
                list.clear();
                list.addAll(response);
            }
        });
    }
}