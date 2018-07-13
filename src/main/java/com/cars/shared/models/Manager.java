package com.cars.shared.models;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Embeddable
@Table(name = "MANAGER")
public class Manager implements Serializable {
    private long idManager;
    private String fullName;
    Date empDate;
    private Long salary;
    private String phone;

    @Id
    @Column(name = "ID_MANAGER")
    public long getIdManager() {
        return idManager;
    }

    public void setIdManager(long idManager) {
        this.idManager = idManager;
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
    @Column(name = "SALARY")
    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @Basic
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy")
    @Column(name = "EMP_DATE")
    public Date getEmp_date() {
       /* SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru", "RU"));
        Date date = format1.parse(string);
        sale.setDate(date);
        Market market = this.marketService.getMarketById(Integer.parseInt(idMarket));
        Automobile automobile = this.automobileService.getAutomobileById(Integer.parseInt(idAuto));*/
        return empDate;
    }

    public void setEmp_date(Date emp_date) {
        this.empDate = emp_date;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manager manager = (Manager) o;

        return idManager == manager.idManager && (fullName != null ? fullName.equals(manager.fullName) : manager.fullName == null) && (salary != null ? salary.equals(manager.salary) : manager.salary == null) && (empDate != null ? empDate.equals(manager.empDate) : manager.empDate == null) && (phone != null ? phone.equals(manager.phone) : manager.phone == null);
    }

    @Override
    public int hashCode() {
        int result = (int) (idManager ^ (idManager >>> 32));
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (empDate != null ? empDate.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

  /*interface ManagerI extends JsonEncoderDecoder<Manager> {

    }

    @Override
    public String toString() {
        if (GWT.isClient()) {
            ManagerI jed = GWT.create(ManagerI.class);
            return jed.encode(this).toString();
        }
        return super.toString();
    }*/

}
