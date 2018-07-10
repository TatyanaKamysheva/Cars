package com.mySampleApplication.shared;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.JsonEncoderDecoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
//import java.sql.Date;

@Embeddable
@Table(name = "MANAGER")
public class Manager implements Serializable {

    @Id
    private int idManager;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "SALARY")
    private int salary;
   /* @Column(name = "EMP_DATE")
    private Date emp_date;*/
    @Column(name = "PHONE")
    private String phone;

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    /*public Date getEmp_date() {
        return emp_date;
    }

    public void setEmp_date(Date emp_date) {
        this.emp_date = emp_date;
    }*/

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public interface ManagerI extends JsonEncoderDecoder<Manager> {

    }

    @Override
    public String toString() {
        if (GWT.isClient()) {
            ManagerI jed = GWT.create(ManagerI.class);
            return jed.encode(this).toString();
        }
        return super.toString();
    }
}
