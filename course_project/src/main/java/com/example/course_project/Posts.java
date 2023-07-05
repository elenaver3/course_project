package com.example.course_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class Posts {

    private ObservableList<Post> result;


    public Posts() throws Exception {
        //result = new ArrayList<>();
        result = FXCollections.observableArrayList();

        //loadResult(resultSet);
    }

    public ObservableList<Post> getResult() {
        return result;
    }

    public void addPost(Post post) {
        result.add(post);
    }

    /*
    private void loadResult(ResultSet resultSet) throws Exception
    {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while(resultSet.next()) {
            Post post = new Post();
            for (int i = 0; i < columnCount - 1 ; i++) {
                String columnName = metaData.getColumnName(i);
                Object objectValue = resultSet.getObject(i);
                post.addPost(columnName, objectValue);
            }
            result.add(post);
        }
    }

     */
}
