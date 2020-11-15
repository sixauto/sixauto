package org.engcia.model;

import org.engcia.CarTrouble.CarTrouble;

import java.util.Objects;

public class Conclusion extends Fact{
    public static final String BATTERY_PROBLEM = "Your battery has a problem! Charge or Replace your car battery.";
    public static final String SENSOR_PROBLEM_REPLACE = "Your sensor has a problem! Replace your CMP Sensor.";
    public static final String SENSOR_PROBLEM_CLEAN = "Your sensor has a problem! Clean the sensor with an eletric cleaner.";
    public static final String FAULTY_WIRES = "Faulty wires between the camshaft position sensor and the ECU! Replace wires between sensor and ECU.";
    public static final String FAULTY_ECU = "Faulty engine control unit! This is rare. Replace your ECU.";
    public static final String SENSOR_DAMAGED = "Your camshaft position sensor is damaged! Substitute the camshaft position sensor with a new one.";
    public static final String INCOMPLETE_WIRING = "Incomplete wiring to camshaft position sensor! Replace or add wires between the position sensor to the ECU.";
    public static final String ECU_ERROR = "ECU error! Replace the ECU or flash the system again.";
    public static final String ECU_NOT_PLUGGED = "ECU not plugged! Plug the ECU.";
    public static final String UNKNOWN_PROBLEM = "Unfortunately our system doesn't know how to help you. Please contact your mechanic!";

    private String description;

    public Conclusion(String description) {
        this.description = description;
        CarTrouble.agendaEventListener.addRhs(this);
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return ("Conclusion: " + description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conclusion)) return false;
        Conclusion that = (Conclusion) o;
        return getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription());
    }
}
