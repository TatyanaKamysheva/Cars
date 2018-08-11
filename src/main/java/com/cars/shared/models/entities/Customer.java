package com.cars.shared.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

    @Id
    @Column(name = "CUSTOMER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUSTOM")
    @SequenceGenerator(name = "CUSTOM", sequenceName = "CUSTOM")
    private Long customerId;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "PASSPORT")
    private Long passport;

    @Column(name = "PHONE")
    private String phone;

    public Customer() {
    }

    public Customer(String surname, String firstName, Long passport, String phone) {
        this.surname = surname;
        this.firstName = firstName;
        this.passport = passport;
        this.phone = phone;
    }


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getPassport() {
        return passport;
    }

    public void setPassport(Long passport) {
        this.passport = passport;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getCustomerId(), customer.getCustomerId()) &&
                Objects.equals(getSurname(), customer.getSurname()) &&
                Objects.equals(getFirstName(), customer.getFirstName()) &&
                Objects.equals(getPassport(), customer.getPassport()) &&
                Objects.equals(getPhone(), customer.getPhone());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCustomerId(), getSurname(), getFirstName(), getPassport(), getPhone());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", surname='" + surname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", passport=" + passport +
                ", phone='" + phone + '\'' +
                '}';
    }
}
