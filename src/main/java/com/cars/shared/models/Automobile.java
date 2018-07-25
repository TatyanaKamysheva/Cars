package com.cars.shared.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AUTOMOBILE")
public class Automobile implements Serializable {

    @Id
    @Column(name = "ID_AUTOMOBILE")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "AUTOM")
    @SequenceGenerator(name = "AUTOM", sequenceName = "AUTOM")
    private Long idAutomobile;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "NAME")
    private String name;

    @Column(name = "GEARS")
    private Long gears;

    @Column(name = "DRIVE_TYPE")
    private String drive_type;


    public Automobile(String model, String name, Long gears, String drive_type) {
        this.model = model;
        this.name = name;
        this.gears = gears;
        this.drive_type = drive_type;
    }

    public Automobile() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdAutomobile() {
        return idAutomobile;
    }

    public void setIdAutomobile(Long idAutomobile) {
        this.idAutomobile = idAutomobile;
    }

    public String getDrive_type() {
        return drive_type;
    }

    public void setDrive_type(String drive_type) {
        this.drive_type = drive_type;
    }

    public Long getGears() {
        return gears;
    }

    public void setGears(Long gears) {
        this.gears = gears;
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "idAutomobile=" + idAutomobile +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                ", gears=" + gears +
                ", drive_type='" + drive_type +
                '}';

    }
}