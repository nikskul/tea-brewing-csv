package ru.croc.nikskul.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class BrewingLog {

    private String employeeName;

    private Tea tea;

    private LocalDateTime brewingStart;

    private LocalDateTime brewingEnd;

    private Integer brewingTemp;

    public Tea getTea() {
        return tea;
    }

    public void setTea(Tea tea) {
        this.tea = tea;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDateTime getBrewingStart() {
        return brewingStart;
    }

    public void setBrewingStart(LocalDateTime brewingStart) {
        this.brewingStart = brewingStart;
    }

    public LocalDateTime getBrewingEnd() {
        return brewingEnd;
    }

    public void setBrewingEnd(LocalDateTime brewingEnd) {
        this.brewingEnd = brewingEnd;
    }

    public Integer getBrewingTemp() {
        return brewingTemp;
    }

    public void setBrewingTemp(Integer brewingTemp) {
        this.brewingTemp = brewingTemp;
    }

    @Override
    public String toString() {
        return "BrewingLog{" +
            "employeeName='" + employeeName + '\'' +
            ", brewingStart=" + brewingStart +
            ", brewingEnd=" + brewingEnd +
            ", brewingTemp=" + brewingTemp +
            '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BrewingLog that = (BrewingLog) object;
        return Objects.equals(employeeName, that.employeeName) && Objects.equals(brewingStart, that.brewingStart) && Objects.equals(brewingEnd, that.brewingEnd) && Objects.equals(brewingTemp, that.brewingTemp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeName, brewingStart, brewingEnd, brewingTemp);
    }
}
