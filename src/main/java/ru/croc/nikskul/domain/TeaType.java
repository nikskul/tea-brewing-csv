package ru.croc.nikskul.domain;

import java.util.Objects;

public class TeaType {

    private Integer id;

    private String name;

    private Integer brewingTimeFrom;

    private Integer brewingTimeTo;

    private Integer tempFrom;

    private Integer tempTo;

    @Override
    public String toString() {
        return "TeaType{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", brewingTimeFrom=" + brewingTimeFrom +
            ", brewingTimeTo=" + brewingTimeTo +
            ", tempFrom=" + tempFrom +
            ", tempTo=" + tempTo +
            '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TeaType teaType = (TeaType) object;
        return Objects.equals(id, teaType.id) && Objects.equals(name, teaType.name) && Objects.equals(brewingTimeFrom, teaType.brewingTimeFrom) && Objects.equals(brewingTimeTo, teaType.brewingTimeTo) && Objects.equals(tempFrom, teaType.tempFrom) && Objects.equals(tempTo, teaType.tempTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brewingTimeFrom, brewingTimeTo, tempFrom, tempTo);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBrewingTimeFrom() {
        return brewingTimeFrom;
    }

    public void setBrewingTimeFrom(Integer brewingTimeFrom) {
        this.brewingTimeFrom = brewingTimeFrom;
    }

    public Integer getBrewingTimeTo() {
        return brewingTimeTo;
    }

    public void setBrewingTimeTo(Integer brewingTimeTo) {
        this.brewingTimeTo = brewingTimeTo;
    }

    public Integer getTempFrom() {
        return tempFrom;
    }

    public void setTempFrom(Integer tempFrom) {
        this.tempFrom = tempFrom;
    }

    public Integer getTempTo() {
        return tempTo;
    }

    public void setTempTo(Integer tempTo) {
        this.tempTo = tempTo;
    }
}