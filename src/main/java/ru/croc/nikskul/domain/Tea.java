package ru.croc.nikskul.domain;

import java.util.Objects;

public class Tea {

    private Integer id;

    private String name;

    private TeaType teaType;

    @Override
    public String toString() {
        return "Tea{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", teaType=" + teaType +
            '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Tea tea = (Tea) object;
        return Objects.equals(id, tea.id) && Objects.equals(name, tea.name) && Objects.equals(teaType, tea.teaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, teaType);
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

    public TeaType getTeaType() {
        return teaType;
    }

    public void setTeaType(TeaType teaType) {
        this.teaType = teaType;
    }
}