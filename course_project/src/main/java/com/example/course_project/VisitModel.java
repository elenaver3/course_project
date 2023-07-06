package com.example.course_project;

import java.sql.*;

public class VisitModel {
    private Connection connection;

    private Posts visits;

    public VisitModel(Connection connection) throws Exception {
        this.connection = connection;

        Statement statement = connection.createStatement();
        String query = "SELECT visits.id, visits.time_start, visits.time_end, tables.table_number, CONCAT(staff.last_name, \" \", staff.first_name) AS waiter, tables.id AS table_id, staff.id AS staff_id\n" +
                "FROM visits JOIN tables ON visits.id_table = tables.id JOIN staff ON staff.id = visits.id_staff;s";
        ResultSet resultSet = statement.executeQuery(query);

        visits = new Posts();

        while (resultSet.next()) {
            Post post = new Post();
            post.addValue("id", resultSet.getInt("id"));
            post.addValue("time_start", resultSet.getString("time_start"));
            post.addValue("time_end", resultSet.getString("time_end"));
            post.addValue("table_number", resultSet.getString("table_number"));
            post.addValue("waiter", resultSet.getString("waiter"));
            post.addValue("staff_id", resultSet.getInt("staff_id"));
            post.addValue("table_id", resultSet.getInt("table_id"));
            visits.addPost(post);
        }
    }

    public Posts getVisits() {
        return visits;
    }

    public Visit getVisit(int id_visit) throws SQLException {
        Visit visit = new Visit();
        String sql = "SELECT * FROM visits WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_visit);
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            visit = new Visit(resultSet.getInt("id"),
                    resultSet.getString("time_start"),
                    resultSet.getString("time_end"),
                    resultSet.getString("table_number"),
                    resultSet.getString("waiter"),
                    resultSet.getInt("staff_id"),
                    resultSet.getInt("table_id"));
        }

        statement.close();
        return visit;
    }

    public void updateVisit(int visit_id, String new_time_start, String new_time_end, String id_table, String id_staff) throws SQLException {
        String sql = "UPDATE visit SET new_time_start = ?, new_time_end = ?, id_table = ?, id_staff = ?";
        sql += " WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        if (new_time_start.isBlank())
            //statement.setString(1, staff.getLast_name());
            statement.setString(1, " ");
        else
            statement.setString(1, new_time_start);
        if (new_time_end.isBlank())
            //statement.setString(2, staff.getFirst_name());
            statement.setString(2, " ");
        else
            statement.setString(2, new_time_end);

        statement.setInt(3, Integer.parseInt(id_table));
        statement.setInt(4, Integer.parseInt(id_staff));


        statement.setInt(6, visit_id);

        statement.executeUpdate();
        statement.close();
    }

    public void deleteVisit(int visit_id) throws SQLException {
        String sql = "DELETE FROM visits where id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, visit_id);

        statement.executeUpdate();
        statement.close();

    }

    public void insertVisit(String new_time_start, String new_time_end, String id_table, String id_staff) throws SQLException {

        String sql = "INSERT INTO staff (new_time_start, new_time_end, id_table, id_staff) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, new_time_start);
        statement.setString(2, new_time_end);
        statement.setInt(3, Integer.parseInt(id_table));
        statement.setInt(4, Integer.parseInt(id_staff));

        statement.executeUpdate();
        statement.close();
    }


    protected void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
