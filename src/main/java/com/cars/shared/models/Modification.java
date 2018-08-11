package com.cars.shared.models;

import java.io.Serializable;
import java.util.Objects;

public class Modification implements Serializable {
    private int code;
    private String modification;

    public Modification(int code, String modification) {
        this.code = code;
        this.modification = modification;
    }

    public Modification(String modification) {
        this.modification = modification;
    }

    public Modification() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Modification)) return false;
        Modification that = (Modification) o;
        return getCode() == that.getCode() &&
                Objects.equals(getModification(), that.getModification());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCode(), getModification());
    }

    @Override
    public String toString() {
        return "Modification{" +
                "code=" + code +
                ", modification='" + modification + '\'' +
                '}';
    }
}
