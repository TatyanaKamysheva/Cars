package com.cars.shared.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Embeddable
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

    private Long idCustomer;

    private String fullName;

    private Long passport;

    private String phone;

    private String email;

    public Customer() {
    }

    public Customer(long idCustomer, String fullName, Long passport, String phone, String email) {
        this.idCustomer = idCustomer;
        this.fullName = fullName;
        this.passport = passport;
        this.phone = phone;
        this.email = email;
    }


    public Customer(Long idCustomer, String fullName, Long passport, String phone, String email) {
        this.idCustomer = idCustomer;
        this.fullName = fullName;
        this.passport = passport;
        this.phone = phone;
        this.email = email;
    }

    @Id
    @Column(name = "ID_CLIENT")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUSTOM")
    @SequenceGenerator(name = "CUSTOM", sequenceName = "CUSTOM")
    public Long getIdCustomer() {
        return idCustomer;
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
    @Column(name = "PASSPORT")
    public Long getPassport() {
        return passport;
    }

    public void setPassport(Long passport) {
        this.passport = passport;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return idCustomer == customer.idCustomer &&
                Objects.equals(fullName, customer.fullName) &&
                Objects.equals(passport, customer.passport) &&
                Objects.equals(phone, customer.phone) &&
                Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCustomer, fullName, passport, phone, email);
    }

    public String toString() {
        return "{\"idCustomer\":" + idCustomer + ", \"fullName\":\"" + fullName +
                "\", \"passport\":" + passport + ", \"phone\":\"" + phone + "\", \"email\":\"" + email + "\"}";
    }
}
