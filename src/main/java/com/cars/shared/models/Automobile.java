package com.cars.shared.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Embeddable
@Table(name = "AUTOMOBILE")
public class Automobile {
    private long idAutomobile;
    private String model;
    private String name;
    private int gears;
    private String drive_type;
    private int tank_capacity;


    public Automobile() {
    }

    public Automobile(long idAutomobile, String model, String name, int gears, String drive_type, int tank_capacity) {
        this.idAutomobile = idAutomobile;
        this.model = model;
        this.name = name;
        this.gears = gears;
        this.drive_type = drive_type;
        this.tank_capacity = tank_capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Automobile that = (Automobile) o;
        return idAutomobile == that.idAutomobile &&
                gears == that.gears &&
                tank_capacity == that.tank_capacity &&
                Objects.equals(model, that.model) &&
                Objects.equals(name, that.name) &&
                Objects.equals(drive_type, that.drive_type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAutomobile, model, name, gears, drive_type, tank_capacity);
    }

    @Id
    @Column(name = "ID_AUTOMOBILE")
    public long getIdAutomobile() {
        return idAutomobile;
    }

    public void setIdAutomobile(long idAutomobile) {
        this.idAutomobile = idAutomobile;
    }

    @Basic
    @Column(name = "MODEL")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "GEARS")
    public int getGears() {
        return gears;
    }

    public void setGears(int gears) {
        this.gears = gears;
    }

    @Basic
    @Column(name = "DRIVE_TYPE")
    public String getDrive_type() {
        return drive_type;
    }

    public void setDrive_type(String drive_type) {
        this.drive_type = drive_type;
    }

    @Basic
    @Column(name = "TANK_CAPACITY")
    public int getTank_capacity() {
        return tank_capacity;
    }

    public void setTank_capacity(int tank_capacity) {
        this.tank_capacity = tank_capacity;
    }
}
