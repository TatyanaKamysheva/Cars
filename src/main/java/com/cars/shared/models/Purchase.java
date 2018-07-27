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

    @ManyToOne
    @JoinColumn(name = "ID_MANAGER")
    @JsonIgnore
    Manager idManager;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_PURCHASE")
    Date date;

    @Column(name = "MODIFICATION")
    String modification;

    public Purchase() {
    }

    public Purchase(Automobile idAutomobile, Customer idCustomer, Manager idManager, Date date, String modification) {
        this.idAutomobile = idAutomobile;
        this.idCustomer = idCustomer;
        this.idManager = idManager;
        this.date = date;
        this.modification = modification;
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

    public Manager getIdManager() {
        return idManager;
    }

    public void setIdManager(Manager idManager) {
        this.idManager = idManager;
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
    public String toString() {
        return "Purchase{" +
                "idPurchase=" + idPurchase +
                ", idAutomobile=" + idAutomobile +
                ", idCustomer=" + idCustomer +
                ", idManager=" + idManager +
                ", date=" + date +
                ", modification='" + modification + '\'' +
                '}';
    }
}
