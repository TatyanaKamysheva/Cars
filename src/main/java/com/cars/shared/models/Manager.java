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
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "SALARY")
    private Long salary;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "ROLE_M")
    private String role;

    public Manager() {
    }

    public Manager(User user, String surname, Long salary, String phone, String firstName, String role) {
        this.user = user;
        this.surname = surname;
        this.salary = salary;
        this.phone = phone;
        this.firstName = firstName;
        this.role = role;
    }

    public Long getIdManager() {
        return idManager;
    }

    public void setIdManager(Long idManager) {
        this.idManager = idManager;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "user=" + user +
                ", idManager=" + idManager +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                ", phone='" + phone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
