package com.cars.shared.models;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EQUIPMENT")
public class Equipment implements Serializable {
    @Id
    @Column(name = "ID_EQUIPMENT")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EQUIP")
    @SequenceGenerator(name = "EQUIP", sequenceName = "EQUIP")
    private Long idEquipment;

    @ManyToOne
    @JoinColumn(name = "ID_AUTOMOBILE")
    @JsonIgnore
    private Automobile idAutomobile;

    @ManyToOne
    @JoinColumn(name = "ID_ATTRIBUTE")
    @JsonIgnore
    private Attribute idAttribute;

    @Column(name = "VALUE_ATTR")
    private String value;

    @Column(name = "NAME_MOD")
    private String nameMod;

    public Equipment(Automobile idAutomobile, Attribute idAttribute, String value) {
        this.idAutomobile = idAutomobile;
        this.idAttribute = idAttribute;
        this.value = value;
    }

    public Equipment() {
    }

    public String getNameMod() {
        return nameMod;
    }

    public void setNameMod(String nameMod) {
        this.nameMod = nameMod;
    }

    public Long getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(Long idEquipment) {
        this.idEquipment = idEquipment;
    }

    public Automobile getIdAutomobile() {
        return idAutomobile;
    }

    public void setIdAutomobile(Automobile idAutomobile) {
        this.idAutomobile = idAutomobile;
    }

    public Attribute getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(Attribute idAttribute) {
        this.idAttribute = idAttribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "idEquipment=" + idEquipment +
                ", idAutomobile=" + idAutomobile +
                ", idAttribute=" + idAttribute +
                ", value='" + value + '\'' +
                '}';
    }
}
