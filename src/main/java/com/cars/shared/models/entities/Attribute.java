package com.cars.shared.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ATTRIBUTE")
public class Attribute implements Serializable {

    @Id
    @Column(name = "ATTRIBUTE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ATTRS")
    @SequenceGenerator(name = "ATTRS", sequenceName = "ATTRS")
    private Long attributeId;

    @Column(name = "NAME")
    private String name;

    public Attribute(String name) {
        this.name = name;
    }

    public Attribute() {
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
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
        if (!(o instanceof Attribute)) return false;
        Attribute attribute = (Attribute) o;
        return Objects.equals(getAttributeId(), attribute.getAttributeId()) &&
                Objects.equals(getName(), attribute.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getAttributeId(), getName());
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "attributeId=" + attributeId +
                ", name='" + name + '\'' +
                '}';
    }
}
