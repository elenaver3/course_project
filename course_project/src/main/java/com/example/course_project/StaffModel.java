package com.example.course_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StaffModel {

    private Connection connection;

    private Posts staff;

    public StaffModel(Connection connection) throws Exception {
        this.connection = connection;

        Statement statement = connection.createStatement();
        String query = "SELECT * FROM staff";
        ResultSet resultSet = statement.executeQuery(query);

        staff = new Posts();

        while (resultSet.next()) {
            Post post = new Post();
            post.addValue("id", resultSet.getInt("id"));
            post.addValue("last_name", resultSet.getString("last_name"));
            post.addValue("first_name", resultSet.getString("first_name"));
            post.addValue("second_name", resultSet.getString("second_name"));
            post.addValue("address", resultSet.getString("address"));
            post.addValue("phone_number", resultSet.getString("phone_number"));
            staff.addPost(post);
        }
    }

    public Posts getStaff() {
        return staff;
    }

    public void updateStaff(Staff staff, String new_last, String new_first, String new_second, String new_address, String new_phone) throws SQLException {
        String sql = "UPDATE staff SET last_name = ?, first_name = ?, second_name = ?, address = ?, phone_number = ?";
        sql += " WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        if (new_last.isBlank())
            statement.setString(1, staff.getLast_name());
        else
            statement.setString(1, new_last);
        if (new_first.isBlank())
            statement.setString(2, staff.getFirst_name());
        else
            statement.setString(2, new_first);
        if (new_second.isBlank())
            statement.setString(3, staff.getSecond_name());
        else
            statement.setString(3, new_second);
        if (new_address.isBlank())
            statement.setString(4, staff.getAddress());
        else
            statement.setString(4, new_address);
        if (new_phone.isBlank())
            statement.setString(5, staff.getPhone_number());
        else
            statement.setString(5, new_phone);


        statement.setInt(6, staff.getId());

        statement.executeUpdate();
        statement.close();
    }

    public void deleteStaff(Staff staff) throws SQLException {
        String sql = "DELETE FROM staff where id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, staff.getId());

        statement.executeUpdate();
        statement.close();

    }

    public void insertStaff(String first_name, String last_name, String second_name, String address, String phone_number) throws SQLException {

        String sql = "INSERT INTO staff (last_name, first_name, second_name, address, phone_number) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, last_name);
        statement.setString(2, first_name);
        statement.setString(3, second_name);
        statement.setString(4, address);
        statement.setString(5, phone_number);

        statement.executeUpdate();
        statement.close();
    }


    protected void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
