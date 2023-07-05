package com.example.course_project;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Dish {
    private SimpleStringProperty id;
    private SimpleStringProperty name;

    private ArrayList<Integer> ingredients;

    public Dish() {

    }

    public Dish(int id, String name) {
        this.id = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.setId(id);
        this.setName(name);
        ingredients = new ArrayList<Integer>();
    }

    public void addIngredients(int ingId) {
        ingredients.add(ingId);
    }

    public int getId() {
        return Integer.parseInt(id.get());
    }


    public String getName() {
        return name.get();
    }

    public void setId(int id) {
        this.id.set(String.valueOf(id));
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
