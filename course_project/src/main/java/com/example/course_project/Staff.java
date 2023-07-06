package com.example.course_project;

import javafx.beans.property.SimpleStringProperty;

public class Staff extends Human {
    private SimpleStringProperty id;
    private SimpleStringProperty phone_number;

    public Staff() {

    }

    public Staff(int id, String last_name, String first_name, String second_name, String address, String phone) {
        this.setFirst_name(first_name);
        this.setLast_name(last_name);
        this.setSecond_name(second_name);
        this.setAddress(address);
        this.phone_number = new SimpleStringProperty();
        this.id = new SimpleStringProperty();
        this.setPhone_number(phone);
        this.setId(id);
    }

    public int getId() {
        return Integer.parseInt(id.getValue());
    }

    public void setId(int id) {
        this.id.set(String.valueOf(id));
    }


    public String getPhone_number() {
        return phone_number.getValue();
    }

    public void setPhone_number(String phone) {
        this.phone_number.set(phone);
    }
}
