package com.cars.shared.models;


import com.cars.shared.Roles;

import java.io.Serializable;
import java.util.Objects;

public class UserLoginInfo implements Serializable {
    private Long userId;
    private String firstName;
    private String surname;
    private Roles role;

    public UserLoginInfo() {
    }

    public UserLoginInfo(Long userId, String firstName, String surname, Roles role) {
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserLoginInfo)) return false;
        UserLoginInfo that = (UserLoginInfo) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getSurname(), that.getSurname()) &&
                getRole() == that.getRole();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUserId(), getFirstName(), getSurname(), getRole());
    }

    @Override
    public String toString() {
        return "UserLoginInfo{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", role=" + role +
                '}';
    }
}