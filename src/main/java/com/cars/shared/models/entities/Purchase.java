package com.cars.shared.models.entities;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "PURCHASE")
public class Purchase {

    @Id
    @Column(name = "PURCHASE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PUR")
    @SequenceGenerator(name = "PUR", sequenceName = "PUR")
    private Long purchaseId;

    @ManyToOne
    @JoinColumn(name = "AUTOMOBILE_ID")
    @JsonIgnore
    private Automobile automobile;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    @JsonIgnore
    private Employee employee;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_PURCHASE")
    private Date date;

    @Column(name = "MODIFICATION_NAME")
    private String modification;

    public Purchase() {
    }

    public Purchase(Automobile automobile, Customer customer, Employee employee, Date date, String modification) {
        this.automobile = automobile;
        this.customer = customer;
        this.employee = employee;
        this.date = date;
        this.modification = modification;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(getPurchaseId(), purchase.getPurchaseId()) &&
                Objects.equals(getAutomobile(), purchase.getAutomobile()) &&
                Objects.equals(getCustomer(), purchase.getCustomer()) &&
                Objects.equals(getEmployee(), purchase.getEmployee()) &&
                Objects.equals(getDate(), purchase.getDate()) &&
                Objects.equals(getModification(), purchase.getModification());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPurchaseId(), getAutomobile(), getCustomer(), getEmployee(), getDate(), getModification());
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseId=" + purchaseId +
                ", automobile=" + automobile +
                ", customer=" + customer +
                ", employee=" + employee +
                ", date=" + date +
                ", modification='" + modification + '\'' +
                '}';
    }
}