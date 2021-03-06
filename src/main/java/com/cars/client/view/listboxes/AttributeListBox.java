package com.cars.client.view.listboxes;

import com.cars.shared.models.entities.Attribute;
import com.google.gwt.user.client.ui.ListBox;

import java.util.List;

public class AttributeListBox extends ListBox {

    private List<Attribute> attributes;

    public AttributeListBox() {
    }

    public AttributeListBox(List<Attribute> attributes) {
        setAttributes(attributes);
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
        attributes.stream()
                .map(Attribute::getName)
                .forEach(this::addItem);
    }

    public Attribute getSelected() {
        String[] selectedValue = getSelectedValue().split(" ");
        String selectedName = selectedValue[0];

        return attributes.stream()
                .filter(attribute -> selectedName.equals(attribute.getName()))
                .findFirst()
                .orElse(null);
    }
}

