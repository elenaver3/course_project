package com.example.course_project;

import javafx.beans.property.SimpleStringProperty;

public class Ingredient {
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty measurementUnit;
    private SimpleStringProperty amount;

    public Ingredient() {

    }

    public Ingredient(int id, String name, String measurementUnit) {
        this.id = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.measurementUnit = new SimpleStringProperty();
        this.amount = new SimpleStringProperty();
        this.setId(id);
        this.setName(name);
        this.setMeasurementUnit(measurementUnit);
    }

    public Ingredient(int id, String name, String measurementUnit, int amount) {
        this.id = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.measurementUnit = new SimpleStringProperty();
        this.amount = new SimpleStringProperty();
        this.setId(id);
        this.setName(name);
        this.setMeasurementUnit(measurementUnit);
        this.setAmount(amount);
    }

    public int getId() {
        return Integer.parseInt(id.get());
    }

    public void setId(int id) {
        this.id.set(String.valueOf(id));
    }

    public float getAmount() {
        return Float.valueOf(amount.getValue());
    }

    public void setAmount(int amount) {
        this.amount.set(String.valueOf(amount));
    }


    public String getName() {
        return name.getValue();
    }


    public void setName(String name) {
        this.name.set(name);
    }

    public String getMeasurementUnit() {
        return measurementUnit.getValue();
    }

    public void setMeasurementUnit(String mUnit) {
        this.measurementUnit.set(mUnit);
    }
}
