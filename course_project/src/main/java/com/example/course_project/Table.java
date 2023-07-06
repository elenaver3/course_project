package com.example.course_project;

import javafx.beans.property.SimpleStringProperty;

public class Table {
    private SimpleStringProperty id;
    private SimpleStringProperty table_number;
    private SimpleStringProperty max_people;

    public Table() {

    }

    public Table(int id, String table_number, int max_people) {
        this.id = new SimpleStringProperty();
        this.table_number = new SimpleStringProperty();
        this.max_people = new SimpleStringProperty();
        this.setId(id);
        this.setTableNumber(table_number);
        this.setMaxPeople(max_people);
    }

    public int getId() {
        return Integer.parseInt(id.get());
    }

    public void setId(int id) {
        this.id.set(String.valueOf(id));
    }


    public String getTableNumber() {
        return table_number.get();
    }

    public void setTableNumber(String name) {
        this.table_number.set(name);
    }

    public int getMaxPeople() {
        return Integer.parseInt(max_people.get());
    }

    public void setMaxPeople(int max_people) {
        this.max_people.set(String.valueOf(max_people));
    }
}
