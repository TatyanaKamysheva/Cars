package com.cars.shared.models;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PURCHASE")
public class Purchase {

    @Id
    @Column(name = "ID_PURCHASE")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PUR")
    @SequenceGenerator(name = "PUR", sequenceName = "PUR")
    Long idPurchase;

    @ManyToOne
    @JoinColumn(name = "ID_AUTOMOBILE")
    @JsonIgnore
    Automobile idAutomobile;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    @JsonIgnore
    Customer idCustomer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_PURCHASE")
    Date date;

    @Column(name = "PAYMENT")
    String payment;

    public Purchase() {
    }

    public Purchase(Automobile idAutomobile, Customer idCustomer, Date date, String payment) {
        this.idAutomobile = idAutomobile;
        this.idCustomer = idCustomer;
        this.date = date;
        this.payment = payment;
    }

    public Long getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(Long idPurchase) {
        this.idPurchase = idPurchase;
    }

    public Automobile getIdAutomobile() {
        return idAutomobile;
    }

    public void setIdAutomobile(Automobile idAutomobile) {
        this.idAutomobile = idAutomobile;
    }

    public Customer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Customer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "idPurchase=" + idPurchase +
                ", idAutomobile=" + idAutomobile +
                ", idCustomer=" + idCustomer +
                ", date=" + date +
                ", payment='" + payment + '\'' +
                '}';
    }
}
