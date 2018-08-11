package com.cars.shared.models;

import java.io.Serializable;
import java.util.Objects;

public class AttributePopup implements Serializable {

    private String attribute;
    private String value;
    private String name;

    public AttributePopup() {
    }

    public AttributePopup(String attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttributePopup)) return false;
        AttributePopup that = (AttributePopup) o;
        return Objects.equals(getAttribute(), that.getAttribute()) &&
                Objects.equals(getValue(), that.getValue()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getAttribute(), getValue(), getName());
    }

    @Override
    public String toString() {
        return "AttributePopup{" +
                "attribute='" + attribute + '\'' +
                ", value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
