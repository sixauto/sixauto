package org.engcia.model;

public class Evidence extends Fact{
    public static final String P0340 = "Is the error code P0340 (CMP Sensor circuit) present?";
    public static final String P0341 = "Is the error code P0341 (CMP Sensor circuit performance) present?";
    public static final String P0342 = "Is the error code P0342 (CMP Sensor circuit low input) present?";
    public static final String P0343 = "Is the error code P0343 (CMP Sensor circuit intermittent) present?";
    public static final String P0344 = "Is the error code P0344 (CMP Sensor circuit high input) present?";


    //Common to both flows
    public static final String SENSOR_5V = "Using a digital multimeter, is the sensor electric potential at 5 volts?";

    //FLOW 1
    public static final String BATTERY_VOLTAGE_BELOW_12V_14V = "Is the battery electric potential below 12 volts with the engine off and below 14 volts with engine on?";
    public static final String SENSOR_CORROSION_OIL = "Does the sensor connection has any corrosion, damage or is covered by oil?";
    public static final String SHORT_CIRCUIT = "Verify with a multimeter if you have a short circuit. Do you?";
    public static final String CABLE_MISSING_DISCONNECTED = "Is there any cable missing or disconnected?";

    //FLOW 2
    public static final String SENSOR_CORROSION = "Can you see any signs of corrosion on the sensor?";
    public static final String SENSOR_5V_IGNITION_ON_CONNECTOR_UNPLUGGED = "Can you get 5-volt on the signal wire with the ignition on and the connector unplugged?";
    public static final String GROUND_WIRE_GROUNDED = "Is the ground wire grounded?";


    private String evidence;
    private String value;

    public Evidence(String ev, String v) {
        evidence = ev;
        value = v;
    }

    public String getEvidence() {
        return evidence;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return (evidence + " = " + value);
    }

}

