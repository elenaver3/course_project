package com.example.course_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionTry {

    private String dbURL = "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2277_restaurant";
    private String dbName = "std_2277_restaurant";
    private String dbPasswd = "restaurant2052";

    private Connection connection;

    public Connection getConnection() throws Exception {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(dbURL, dbName, dbPasswd);

            /*
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM measurementUnits";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                int id = result.getInt("id");
                String unit = result.getString("unit");

                System.out.println(id +  " " + unit);

            }

            //connection.close();

             */


        }
        catch (Exception e) {
            System.out.println(e);
        }

        return connection;
    }
}
