package com.cars.shared.models.entities;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "EQUIPMENT")
public class Equipment implements Serializable {
    @Id
    @Column(name = "EQUIPMENT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EQUIP")
    @SequenceGenerator(name = "EQUIP", sequenceName = "EQUIP")
    private Long idEquipment;

    @ManyToOne
    @JoinColumn(name = "AUTOMOBILE_ID")
    @JsonIgnore
    private Automobile automobile;

    @ManyToOne
    @JoinColumn(name = "ATTRIBUTE_ID")
    @JsonIgnore
    private Attribute attribute;

    @Column(name = "VALUE_ATTRIBUTE")
    private String value;

    @Column(name = "MODIFICATION_NAME")
    private String modificationName;

    public Equipment() {
    }

    public Equipment(Automobile automobile, Attribute attribute, String value, String modificationName) {
        this.automobile = automobile;
        this.attribute = attribute;
        this.value = value;
        this.modificationName = modificationName;
    }

    public Long getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(Long idEquipment) {
        this.idEquipment = idEquipment;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getModificationName() {
        return modificationName;
    }

    public void setModificationName(String modificationName) {
        this.modificationName = modificationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipment)) return false;
        Equipment equipment = (Equipment) o;
        return Objects.equals(getIdEquipment(), equipment.getIdEquipment()) &&
                Objects.equals(getAutomobile(), equipment.getAutomobile()) &&
                Objects.equals(getAttribute(), equipment.getAttribute()) &&
                Objects.equals(getValue(), equipment.getValue()) &&
                Objects.equals(getModificationName(), equipment.getModificationName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdEquipment(), getAutomobile(), getAttribute(), getValue(), getModificationName());
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "idEquipment=" + idEquipment +
                ", automobile=" + automobile +
                ", attribute=" + attribute +
                ", value='" + value + '\'' +
                ", modificationName='" + modificationName + '\'' +
                '}';
    }
}
