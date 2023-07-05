package com.example.course_project;

import javafx.beans.property.SimpleStringProperty;

public abstract class Human {
    private SimpleStringProperty last_name;
    private SimpleStringProperty first_name;
    private SimpleStringProperty second_name;
    private SimpleStringProperty address;

    public Human() {
        last_name = new SimpleStringProperty();
        first_name = new SimpleStringProperty();
        second_name = new SimpleStringProperty();
        address = new SimpleStringProperty();
    }

    public String getFirst_name() {
        return first_name.get();
    }

    public String getLast_name() {
        return last_name.get();
    }

    public String getSecond_name() {
        return second_name.get();
    }

    public String getAddress() {
        return address.get();
    }

    public void setFirst_name(String first_name) {
        this.first_name.set(first_name);
    }

    public void setLast_name(String last_name) {
        this.last_name.set(last_name);
    }

    public void setSecond_name(String second_name) {
        this.second_name.set(second_name);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }




}
