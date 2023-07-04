package com.example.course_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MeasurementUnitDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public MeasurementUnitDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                //Class.forName("com.mysql.jdbc.Driver");
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertBook(MeasurementUnit mUnit) throws SQLException {
        String sql = "INSERT INTO measurementUnits (unit) VALUES (?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, mUnit.getUnit());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<MeasurementUnit> listAllUnits() throws SQLException {
        List<MeasurementUnit> listUnit = new ArrayList<>();

        String sql = "SELECT * FROM measurementUnits";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String unit = resultSet.getString("unit");

            MeasurementUnit mUnit = new MeasurementUnit(id, unit);
            listUnit.add(mUnit);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listUnit;
    }

    public boolean deleteBook(MeasurementUnit mUnit) throws SQLException {
        String sql = "DELETE FROM measurementUnits where id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, mUnit.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateBook(MeasurementUnit mUnit) throws SQLException {
        String sql = "UPDATE measurementUnits SET unit = ?";
        sql += " WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, mUnit.getUnit());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public MeasurementUnit getBook(int id) throws SQLException {
        MeasurementUnit mUnit = null;
        String sql = "SELECT * FROM measurementUnits WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String unit = resultSet.getString("unit");

            mUnit = new MeasurementUnit(id, unit);
        }

        resultSet.close();
        statement.close();

        return mUnit;
    }
}
