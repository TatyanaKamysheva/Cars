package com.cars.client.view.listboxes;

import com.cars.shared.models.Customer;
import com.google.gwt.user.client.ui.ListBox;

import java.util.List;

public class CustomerListBox extends ListBox {

    private List<Customer> customers;

    public CustomerListBox() {
    }

    public CustomerListBox(List<Customer> customers) {
        setCustomers(customers);
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
        customers.stream()
                .map(customer -> customer.getFirstName() + " " + customer.getSurname())
                .forEach(this::addItem);
    }

    public Customer getSelected() {
        String[] selectedValue = getSelectedValue().split(" ");
        String selectedName = selectedValue[0];
        String selectedSurname = selectedValue[1];

        return customers.stream()
                .filter(customer -> selectedName.equals(customer.getFirstName())
                        && selectedSurname.equals(customer.getSurname()))
                .findFirst()
                .orElse(null);
    }

}
