package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.mySampleApplication.shared.Manager;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;
import java.util.logging.Logger;

public class Main implements EntryPoint {

    Logger logger = java.util.logging.Logger.getLogger("Logger");

    //private final MySampleApplicationServiceAsync service = GWT.create(MySampleApplicationService.class);

    public void onModuleLoad() {
       MySampleApplicationService.Util.getService().list(new MethodCallback<List<Manager>>() {
           @Override
           public void onFailure(Method method, Throwable exception) {
               Label label = new Label("ERROR");
               RootPanel.get("label").add(label);
           }

           @Override
           public void onSuccess(Method method, List<Manager> response) {
               CellTable<Manager> table = new CellTable<Manager>();
               TextColumn<Manager> idColumn = new TextColumn<Manager>() {
                   @Override
                   public String getValue(Manager manager) {
                       return String.valueOf(manager.getIdManager());
                   }
               };
               TextColumn<Manager> full_nameColumn = new TextColumn<Manager>() {
                   @Override
                   public String getValue(Manager manager) {
                       return manager.getFullName();
                   }
               };
               table.addColumn(idColumn, "ID");
               table.addColumn(full_nameColumn, "Full name");
               ListDataProvider<Manager> dataProvider = new ListDataProvider<Manager>();
               List<Manager> list = dataProvider.getList();
               for(Manager m:response){
                   list.add(m);
               }
               dataProvider.addDataDisplay(table);
               RootPanel.get("table").add(table);
           }
       });
       /* MySampleApplicationService.Util.getService().getInfo(new MethodCallback<Manager>() {
            @Override
            public void onSuccess(Method method, Manager manager) {
                CellTable<Manager> table = new CellTable<Manager>();
                TextColumn<Manager> idColumn = new TextColumn<Manager>() {
                    @Override
                    public String getValue(Manager manager) {
                        return String.valueOf(manager.getIdManager());
                    }
                };
                TextColumn<Manager> full_nameColumn = new TextColumn<Manager>() {
                    @Override
                    public String getValue(Manager manager) {
                        return manager.getFullName();
                    }
                };
                table.addColumn(idColumn, "ID");
                table.addColumn(full_nameColumn, "Full name");
                ListDataProvider<Manager> dataProvider = new ListDataProvider<Manager>();
                List<Manager> list = dataProvider.getList();
                list.add(manager);
                dataProvider.addDataDisplay(table);
                RootPanel.get("table").add(table);
            }

            @Override
            public void onFailure(Method method, Throwable throwable) {
                Label label = new Label("ERROR");
                RootPanel.get("label").add(label);
            }
        });*/
        /*service.list(new AsyncCallback<List<Manager>>() {
            @Override
            public void onFailure(Throwable caught) {
                Label label = new Label("ERROR");
                RootPanel.get("label").add(label);
            }

            @Override
            public void onSuccess(List<Manager> result) {
                Label label = new Label("OK");
                RootPanel.get("label").add(label);
            }
        });*/
        /*CellTable<Manager> table = new CellTable<Manager>();
        TextColumn<Manager> idColumn = new TextColumn<Manager>() {
            @Override
            public String getValue(Manager manager) {
                return String.valueOf(manager.getIdManager());
            }
        };
        TextColumn<Manager> full_nameColumn = new TextColumn<Manager>() {
            @Override
            public String getValue(Manager manager) {
                return manager.getFullName();
            }
        };
        table.addColumn(idColumn, "ID");
        table.addColumn(full_nameColumn, "Full name");
        ListDataProvider<Manager> dataProvider = new ListDataProvider<Manager>();
        List<Manager> list = dataProvider.getList();
        list.add(ser.list(new AsyncCallback<List<Manager>>() {
                              @Override
                              public void onFailure(Throwable caught) {

                                  Label label = new Label("ERROR");
                                  RootPanel.get("label").add(label);
                              }

                              @Override
                              public void onSuccess(List<Manager> result) {
                                  Label label = new Label("OKOK");
                                  RootPanel.get("label").add(label);
                              }
                          }
        ));

        dataProvider.addDataDisplay(table);
        RootPanel.get("table").add(table);*/
    }


    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}
