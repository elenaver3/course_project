package com.example.course_project;

import java.util.HashMap;
import java.util.TreeMap;

public class Post extends HashMap<String, Object> {

    public Post() {
    }

    public void addValue(String columnName, Object objectValue) {
        this.put(columnName, objectValue);
    }

    public HashMap<String, Object> getResult() {
        return this;
    }

    public Object getValue(String key) {
        return this.get(key);
    }
}
