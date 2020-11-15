package org.engcia.model;

public class Symptom {

    // Takes to long to start the engine
    public static final String LONG_ENGINE_CRANKING_TIME = "Long engine cranking time";
    // Unstable acceleration
    public static final String SHARP_ACCELERATION = "Sharp acceleration";
    public static final String ENGINE_STARTS_WITHOUT_PROBLEMS = "Engine starts without problems";
    public static final String ENGINE_DIES_WHILE_DRIVING = "Engine dies while driving";
    public static final String HARD_SHIFTING = "Hard shifting";
    public static final String ENGINE_LIGHT_ON = "Engine light on";
    // Car stopped but with engine running has unstable vibrations
    public static final String ENGINE_ROUGH_IDLE = "Engine rough idle";
    public static final String HARD_ENGINE_STARTING_CONDITION = "Hard engine starting condition";
    public static final String ENGINE_DIES_SUDDENLY = "Engine dies suddenly";
    public static final String LOSS_POWER_ENGINE = "Loss of power in the engine";

    private String value;
    private String flow;

    public Symptom(String value, String flow) {
        this.value = value;
        this.flow = flow;
    }

    public String getValue() {
        return value;
    }

    public String getFlow() {
        return flow;
    }

    public String toString() {
        return (value);
    }
}