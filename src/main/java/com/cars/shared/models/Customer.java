package com.cars.shared.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

    @Id
    @Column(name = "ID_CLIENT")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUSTOM")
    @SequenceGenerator(name = "CUSTOM", sequenceName = "CUSTOM")
    private Long idCustomer;

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

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
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
    public String toString() {
        return "Customer{" +
                "idCustomer=" + idCustomer +
                ", surname='" + surname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", passport=" + passport +
                ", phone='" + phone + '\'' +
                '}';
    }
}
