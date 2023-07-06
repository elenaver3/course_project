package com.example.course_project;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;


public class Registration {

    private Connection connection;


    public Registration(Connection connection, String user_fio, String user_name, String bdate, String user_password) throws Exception {
        String sql = "INSERT INTO users (user_name, user_fio, bdate, user_password) VALUES (?, ?, ?, ?)";

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user_name);
        statement.setString(2, user_fio);
        statement.setString(3, bdate);
        statement.setString(4, getPassword(user_password));

        statement.executeUpdate() ;
        statement.close();
    }

    private String getPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

}
