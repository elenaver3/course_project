package com.example.course_project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Authorization {

    private Connection connection;

    private int role;

    private Posts posts;

    public Authorization(Connection connection, String userLogin, String userPassword) throws Exception {
        role = -1;

        this.connection = connection;

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT user_role FROM users WHERE user_name='" + userLogin + "' AND user_password='" + userPassword + "'";
            ResultSet resultSet = statement.executeQuery(query);

            //posts = new Posts(resultSet);
            posts = new Posts();

            if (resultSet.next()) {
                Post post = new Post();
                post.addValue("user_role", resultSet.getInt("user_role"));
                posts.addPost(post);
            }

            role = (int)posts.getResult().get(0).get("user_role");

            /*
            if (posts.getResult().size() == 1) {
                Object temp = posts.getResult().get(0).getValue("user_role");
                role = (int)temp;
            }


            if (resultSet.next()) {
                role = resultSet.getInt("user_role");
            } */



        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getRole() {
        return role;
    }

}
