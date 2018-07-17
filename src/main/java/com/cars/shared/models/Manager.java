package com.cars.shared.models;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Embeddable
@Table(name = "MANAGER")
public class Manager implements Serializable {

    private Long idManager;
    private String fullName;
    private Long salary;
    private String phone;

    public Manager() {
    }

    public Manager(Long idManager, String fullName, Long salary, String phone) {
        this.idManager = idManager;
        this.fullName = fullName;
        this.salary = salary;
        this.phone = phone;
    }


    @Id
    @Column(name = "ID_MANAGER")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "MANAG")
    @SequenceGenerator(name = "MANAG", sequenceName = "MANAG")
    public Long getIdManager() {
        return idManager;
    }

    public void setIdManager(Long idManager) {
        this.idManager = idManager;
    }

    @Basic
    @Column(name = "FULL_NAME")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "SALARY")
    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString() {
        return "{\"idManager\":" + idManager + ", \"fullName\":\"" + fullName +
                "\", \"salary\":" + salary + ", \"phone\":\"" + phone + "\"}";
    }
}
