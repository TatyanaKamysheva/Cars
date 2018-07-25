package com.cars.shared.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ATTRIBUTE")
public class Attribute implements Serializable {

    @Id
    @Column(name = "ID_ATTRIBUTE")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ATTRS")
    @SequenceGenerator(name = "ATTRS", sequenceName = "ATTRS")
    private Long idAttribute;

    @Column(name = "NAME")
    private String name;


    public Attribute() {
    }

    public Attribute(String name) {
        this.name = name;
    }

    public Long getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(Long idAttribute) {
        this.idAttribute = idAttribute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "idAttribute=" + idAttribute +
                ", name='" + name + '\'' +
                '}';
    }
}
