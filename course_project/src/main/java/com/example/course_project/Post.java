package com.example.course_project;

import java.util.HashMap;
import java.util.TreeMap;

public class Post {

    private TreeMap<String, Object> post;

    public Post() {
        post = new TreeMap<>();
    }

    public void addPost(String columnName, Object objectValue) {
        post.put(columnName, objectValue);
    }

    public TreeMap<String, Object> getResult() {
        return post;
    }

    public Object getValue(String key) {
        return post.get(key);
    }
}
