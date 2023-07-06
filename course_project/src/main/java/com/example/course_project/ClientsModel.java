package com.example.course_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import java.sql.*;
import java.util.ArrayList;

public class ClientsModel {
    private Posts clients;
    private Connection connection;

    public ClientsModel(Connection connection) throws Exception {
        this.connection = connection;

        Statement statement = connection.createStatement();
        String query = "SELECT * FROM clients";
        ResultSet resultSet = statement.executeQuery(query);

        clients = new Posts();

        while (resultSet.next()) {
            Post post = new Post();
            post.addValue("id", resultSet.getInt("id"));
            post.addValue("last_name", resultSet.getString("last_name"));
            post.addValue("first_name", resultSet.getString("first_name"));
            post.addValue("second_name", resultSet.getString("second_name"));
            post.addValue("inn", resultSet.getString("inn"));
            post.addValue("address", resultSet.getString("address"));
            clients.addPost(post);
        }

    }

    public Posts getClients() {
        return clients;
    }

    public Client getClient(int id_client) throws SQLException {
        Client client = new Client();
        String sql = "SELECT * FROM clients WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_client);
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            client = new Client(resultSet.getInt("id"),
                    resultSet.getString("last_name"),
                    resultSet.getString("first_name"),
                    resultSet.getString("second_name"),
                    resultSet.getString("address"),
                    resultSet.getString("inn"));
        }
        statement.close();
        return client;
    }

    public void updateClients(int id_client, String new_last, String new_first, String new_second, String new_inn, String new_address) throws SQLException {
        String sql = "UPDATE clients SET last_name = ?, first_name = ?, second_name = ?, inn = ?, address = ?";
        sql += " WHERE id = ?";

        //Client client = getClient(id_client);
        PreparedStatement statement = connection.prepareStatement(sql);

        if (new_last.isBlank())
            statement.setString(1, " ");
            //statement.setString(1, client.getLast_name());
        else
            statement.setString(1, new_last);
        if (new_first.isBlank())
            statement.setString(2, " ");
            //statement.setString(2, client.getFirst_name());
        else
            statement.setString(2, new_first);
        if (new_second.isBlank())
            statement.setString(3, " ");
            //statement.setString(3, client.getSecond_name());
        else
            statement.setString(3, new_second);
        if (new_inn.isBlank())
            statement.setString(4, " ");
            //statement.setString(4, client.getInn());
        else
            statement.setString(4, new_inn);
        if (new_address.isBlank())
            statement.setString(5, " ");
            //statement.setString(5, client.getAddress());
        else
            statement.setString(5, new_address);

        statement.setInt(6, id_client);

        statement.executeUpdate();
        statement.close();
    }

    public void deleteClient(int id_client) throws SQLException {
        String sql = "DELETE FROM clients where id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_client);

        statement.executeUpdate();
        statement.close();

    }

    public void insertClient( String first_name, String last_name, String second_name, String inn, String address) throws SQLException {

        String sql = "INSERT INTO clients (last_name, first_name, second_name, inn, address) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, last_name);
        statement.setString(2, first_name);
        statement.setString(3, second_name);
        statement.setString(4, inn);
        statement.setString(5, address);

        statement.executeUpdate() ;
        statement.close();
    }
}


