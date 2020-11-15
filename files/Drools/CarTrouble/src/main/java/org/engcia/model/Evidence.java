package org.engcia.model;

import org.engcia.CarTrouble.CarTrouble;

import java.util.Objects;

public class Evidence extends Fact{

    public static final String P0340 = "Error code P0340 present";
    public static final String P0341 = "Error code P0341 present";
    public static final String P0342 = "Error code P0342 present";
    public static final String P0343 = "Error code P0343 present";
    public static final String P0344 = "Error code P0344 present";
    public static final String FLOW_1 = "Flow nr. 1 (calculated either by the error codes present or by symptom analysis)";
    public static final String FLOW_2 = "Flow nr. 2 (calculated either by the error codes present or by symptom analysis)";
    public static final String SENSOR_NOT_AT_5V = "Sensor electric potential is not at 5 volts";
    public static final String SENSOR_AT_5V = "Sensor electric potential is at 5 volts";
    public static final String BATTERY_VOLTAGE_NOT_BELOW_12V_14V = "Battery electric potential not below 12 volts with the engine off and not below 14 volts with engine on";
    public static final String SENSOR_WITHOUT_CORROSION_OIL = "Sensor connection does not have corrosion, damage neither is covered by oil";
    public static final String NO_SHORT_CIRCUIT = "A short circuit is not present";
    public static final String NO_CABLE_MISSING_DISCONNECTED = "No cable(s) is missing/disconnected";
    public static final String SENSOR_WITHOUT_CORROSION = "Sensor without corrosion";
    public static final String SENSOR_NOT_AT_5V_IGNITION_ON_CONNECTOR_UNPLUGGED = "Electric potential is not at 5-volt on the signal wire with the ignition on and the connector unplugged (No 5V Ignition ON Connector Off 5V-IO-CO)";
    public static final String GROUND_WIRE_GROUNDED = "Ground wire grounded";

    private String value;
    public Evidence(String value) {
        this.value = value;
        CarTrouble.agendaEventListener.addRhs(this);
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return (value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evidence)) return false;
        Evidence evidence = (Evidence) o;
        return getValue().equals(evidence.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}