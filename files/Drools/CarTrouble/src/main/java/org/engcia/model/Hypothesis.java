package org.engcia.model;

import org.engcia.CarTrouble.CarTrouble;

public class Hypothesis extends Fact{
    private String description;
    private String value;
    public Hypothesis(String description, String value) {
        this.description = description;
        this.value = value;
        CarTrouble.agendaEventListener.addRhs(this);
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return (description + " = " + value);
    }
}