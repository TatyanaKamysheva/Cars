package com.cars.shared.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "MANAGER")
public class Manager implements Serializable {

    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonIgnore
    User user;
    @Id
    @Column(name = "ID_MANAGER")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "MANAG")
    @SequenceGenerator(name = "MANAG", sequenceName = "MANAG")
    private Long idManager;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "SALARY")
    private Long salary;
    @Column(name = "PHONE")
    private String phone;

    public Long getIdManager() {
        return idManager;
    }

    public void setIdManager(Long idManager) {
        this.idManager = idManager;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

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

    @Column(name = "ROLE")
    private String role;

    public Manager() {
    }

    public Manager(String fullName, String role) {
        this.fullName = fullName;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
