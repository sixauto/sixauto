package org.engcia.model;

public class TroubleCode {
    private String code;
    private String system;
    private String manufacturer;
    private String description;

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "TroubleCode{" +
                "code='" + code + '\'' +
                ", system='" + system + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
