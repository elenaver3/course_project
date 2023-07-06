package com.example.course_project;

import javafx.beans.property.SimpleStringProperty;

public class Client extends Human  {

    private SimpleStringProperty inn;
    private SimpleStringProperty id;

    public Client() {

    }
    public Client(int id, String last_name, String first_name, String second_name, String address, String inn) {
        this.inn = new SimpleStringProperty();
        this.id = new SimpleStringProperty();
        this.setFirst_name(first_name);
        this.setLast_name(last_name);
        this.setSecond_name(second_name);
        this.setAddress(address);
        this.setInn(inn);
        this.setId(id);
    }


    public String getInn() {
        return inn.getValue();
    }

    public int getId() {
        return Integer.parseInt(id.getValue());
    }

    public void setId(int id) {
        this.id.set(String.valueOf(id));
    }

    public void setInn(String inn) {
        this.inn.set(inn);
    }
}
