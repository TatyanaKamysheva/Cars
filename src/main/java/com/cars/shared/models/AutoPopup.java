package com.cars.shared.models;

public class AutoPopup {
    String attribute;
    String value;

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AutoPopup(String attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

    public AutoPopup() {
    }

    @Override
    public String toString() {
        return "AutoPopup{" +
                "attribute='" + attribute + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
