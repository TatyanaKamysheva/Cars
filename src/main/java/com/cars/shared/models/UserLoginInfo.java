package com.cars.shared.models;


import java.io.Serializable;

public class UserLoginInfo implements Serializable {
    boolean isTeamlead;
    private long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private Roles role;

    public UserLoginInfo() {
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public boolean getTeamlead() {
        return isTeamlead;
    }

    public void setTeamlead(boolean teamlead) {
        isTeamlead = teamlead;
    }
}