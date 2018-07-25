package com.cars.client.view.listboxes;

import com.cars.shared.models.Automobile;
import com.google.gwt.user.client.ui.ListBox;

import java.util.List;

public class AutomobileListBox extends ListBox {

    private List<Automobile> automobiles;

    public AutomobileListBox() {
    }

    public AutomobileListBox(List<Automobile> automobiles) {
        setAutomobiles(automobiles);
    }

    public void setAutomobiles(List<Automobile> automobiles) {
        this.automobiles = automobiles;
        automobiles.stream()
                .map(automobile -> automobile.getModel() + " " + automobile.getName())
                .forEach(this::addItem);
    }

    public Automobile getSelected() {

        String[] selectedValue = getSelectedValue().split(" ");
        String selectedModel = selectedValue[0];
        String selectedName = selectedValue[1];

        return automobiles.stream()
                .filter(automobile -> selectedModel.equals(automobile.getModel())
                        && selectedName.equals(automobile.getName()))
                .findFirst()
                .orElse(null);
    }
}
