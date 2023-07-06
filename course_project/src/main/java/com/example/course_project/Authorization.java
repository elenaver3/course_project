package com.example.course_project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Base64;


public class Authorization {

    private Connection connection;

    private int role;

    private Posts posts;

    public Authorization(Connection connection, String userLogin, String userPassword) throws Exception {
        role = -1;

        this.connection = connection;

        try {
            Statement statement = connection.createStatement();
            String[] temp1 = userLogin.split(";");

            String query = "SELECT user_role FROM users WHERE user_name='" + temp1[0] + "' AND user_password='"
                    + this.getPassword(userPassword) + "'";
            ResultSet resultSet = statement.executeQuery(query);

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

    private String getPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashedPassword);
    }
}
