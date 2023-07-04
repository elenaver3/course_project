package com.example.course_project;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class Posts {

    private ArrayList<Post> result;


    public Posts() throws Exception {
        result = new ArrayList<>();

        //loadResult(resultSet);
    }

    public ArrayList<Post> getResult() {
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
