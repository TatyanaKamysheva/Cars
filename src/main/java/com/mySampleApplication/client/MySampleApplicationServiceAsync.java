package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mySampleApplication.shared.Manager;

import java.util.List;

public interface MySampleApplicationServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
    void list(AsyncCallback<List<Manager>> async)throws IllegalArgumentException;
}
