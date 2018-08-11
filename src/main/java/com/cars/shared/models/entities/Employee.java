package com.cars.shared.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {

    @Id
    @Column(name = "EMPLOYEE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "MANAG")
    @SequenceGenerator(name = "MANAG", sequenceName = "MANAG")
    private Long employeeId;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "ROLE_EMPLOYEE")
    private String role;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private User user;

    public Employee() {
    }

    public Employee(String surname, String phone, String firstName, String role, User user) {
        this.surname = surname;
        this.phone = phone;
        this.firstName = firstName;
        this.role = role;
        this.user = user;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getEmployeeId(), employee.getEmployeeId()) &&
                Objects.equals(getSurname(), employee.getSurname()) &&
                Objects.equals(getPhone(), employee.getPhone()) &&
                Objects.equals(getFirstName(), employee.getFirstName()) &&
                Objects.equals(getRole(), employee.getRole()) &&
                Objects.equals(getUser(), employee.getUser());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getEmployeeId(), getSurname(), getPhone(), getFirstName(), getRole(), getUser());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
