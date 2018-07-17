package com.cars.shared.models;

import org.codehaus.jackson.annotate.JsonProperty;
import org.fusesource.restygwt.client.Json;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Embeddable
@Table(name = "CUSTOMER")
public class Customer implements Serializable {
    @Json(name = "idCustomer")
    @JsonProperty("idCustomer")
    private long idCustomer;

    @Json(name = "fullName")
    @JsonProperty("fullName")
    private String fullName;

    @Json(name = "passport")
    @JsonProperty("passport")
    private Long passport;

    @Json(name = "phone")
    @JsonProperty("phone")
    private String phone;

    @Json(name = "email")
    @JsonProperty("email")
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


    @Id
    @Column(name = "ID_CLIENT")
    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
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


    public String toJsonString() {
        return "{\"idCustomer\":" + idCustomer + ", \"fullName\":\"" + fullName +
                "\", \"passport\":" + passport + ", \"phone\":\"" + phone + "\"}";
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
}
