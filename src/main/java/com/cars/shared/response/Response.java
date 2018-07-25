package com.cars.shared.response;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONValue;
import org.fusesource.restygwt.client.JsonEncoderDecoder;

import java.io.Serializable;

public class Response<T extends Serializable> implements Serializable {
    private RequestStatus status;
    private T info;

    private Response(RequestStatus status) {
        this.status = status;
        this.info = null;
    }

    public Response() {
    }

    public static Response success() {
        return new Response(RequestStatus.SUCCESS);
    }

    public RequestStatus getStatus() {
        return status;
    }

    public static Response setStatus(RequestStatus status) {
        return new Response(status);
    }

    public T getInfo() {
        return info;
    }

    public Response setInfo(T info) {
        this.info = info;
        return this;
    }

    public boolean isPresent() {
        return info != null;
    }

}
