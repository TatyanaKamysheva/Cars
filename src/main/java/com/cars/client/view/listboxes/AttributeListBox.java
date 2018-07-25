package com.cars.client.view.listboxes;

import com.cars.shared.models.Attribute;
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
                .map(attribute -> attribute.getName())
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

    public Attribute setAttribute(Attribute attribute1) {
        //super.getSelectElement().getOptions().getItem(index).setSelected(selected);
        return this.attributes.stream().filter(attribute -> attribute.equals(attribute1.getName())).findFirst().orElse(null);
    }
}

