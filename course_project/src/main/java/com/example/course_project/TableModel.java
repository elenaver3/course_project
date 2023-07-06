package com.example.course_project;

import java.sql.*;

public class TableModel {
    private Posts tables;
    private Connection connection;

    public TableModel(Connection connection) throws Exception {
        this.connection = connection;

        Statement statement = connection.createStatement();
        String query = "SELECT * FROM tables";
        ResultSet resultSet = statement.executeQuery(query);

        tables = new Posts();

        while (resultSet.next()) {
            Post post = new Post();
            post.addValue("id", resultSet.getInt("id"));
            post.addValue("table_number", resultSet.getString("table_number"));
            post.addValue("max_people", resultSet.getInt("max_people"));
            tables.addPost(post);
        }

    }

    public Posts getTables() {
        return tables;
    }

    public Table getTable(int id_table) throws SQLException {
        Table table = new Table();
        String sql = "SELECT * FROM tables WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_table);
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            table = new Table(resultSet.getInt("id"),
                    resultSet.getString("table_number"),
                    resultSet.getInt("max_people"));
        }
        statement.close();
        return table;
    }

    public void updateTables(int id_table, String new_table_number, String new_max_people) throws SQLException {
        String sql = "UPDATE tables SET table_number = ?, max_people = ?";
        sql += " WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        if (new_table_number.isBlank())
            //statement.setString(1, table.getTableNumber());
            statement.setString(1, " ");
        else
            statement.setString(1, new_table_number);
        if (new_max_people.isBlank())
            //statement.setString(2, String.valueOf(table.getMaxPeople()));
            statement.setString(2, " ");
        else
            statement.setInt(2, Integer.parseInt(new_max_people));


        statement.setInt(3, id_table);

        statement.executeUpdate();
        statement.close();
    }

    public void deleteTables(int id_table) throws SQLException {
        String sql = "DELETE FROM tables where id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_table);

        statement.executeUpdate();
        statement.close();

    }

    public void insertTables(String table_number, String max_people) throws SQLException {

        String sql = "INSERT INTO tables (table_number, max_people) VALUES (?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, table_number);
        statement.setInt(2, Integer.parseInt(max_people));

        statement.executeUpdate() ;
        statement.close();
    }
}
