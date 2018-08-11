package com.cars.shared.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "AUTOMOBILE")
public class Automobile implements Serializable {

    @Id
    @Column(name = "AUTOMOBILE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "AUTOM")
    @SequenceGenerator(name = "AUTOM", sequenceName = "AUTOM")
    private Long automobileId;

    @Column(name = "MANUFACTURER")
    private String manufacturer;

    @Column(name = "MODEL_AUTOMOBILE")
    private String model;

    public Automobile() {
    }

    public Automobile(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public Long getAutomobileId() {
        return automobileId;
    }

    public void setAutomobileId(Long automobileId) {
        this.automobileId = automobileId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Automobile)) return false;
        Automobile that = (Automobile) o;
        return Objects.equals(getAutomobileId(), that.getAutomobileId()) &&
                Objects.equals(getManufacturer(), that.getManufacturer()) &&
                Objects.equals(getModel(), that.getModel());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getAutomobileId(), getManufacturer(), getModel());
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "automobileId=" + automobileId +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}