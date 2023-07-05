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

    public void updateClients(Client client, String new_last, String new_first, String new_second, String new_inn, String new_address) throws SQLException {
        String sql = "UPDATE clients SET last_name = ?, first_name = ?, second_name = ?, inn = ?, address = ?";
        sql += " WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        if (new_last.isBlank())
            statement.setString(1, client.getLast_name());
        else
            statement.setString(1, new_last);
        if (new_first.isBlank())
            statement.setString(2, client.getFirst_name());
        else
            statement.setString(2, new_first);
        if (new_second.isBlank())
            statement.setString(3, client.getSecond_name());
        else
            statement.setString(3, new_second);
        if (new_inn.isBlank())
            statement.setString(4, client.getInn());
        else
            statement.setString(4, new_inn);
        if (new_address.isBlank())
            statement.setString(5, client.getAddress());
        else
            statement.setString(5, new_address);

        statement.setInt(6, client.getId());

        statement.executeUpdate();
        statement.close();
    }

    public void deleteClient(Client client) throws SQLException {
        String sql = "DELETE FROM clients where id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, client.getId());

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

/*
    public ObservableList<Client> listAllClients() throws SQLException {

        String sql = "SELECT * FROM book";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");

            Book book = new Book(id, title, author, price);
            listBook.add(book);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listBook;
    }

    /*
    protected void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }*/

    /*
    public boolean insertUsers() throws SQLException {

        String sql = "INSERT INTO clients (last_name, first_name, second_name, inn, address) VALUES (?, ?, ?, ?, ?)";


        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setFloat(3, book.getPrice());
        //last_name	first_name	second_name	inn	address

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        return rowInserted;
    }

     */
/*
    public List<Book> listAllBooks() throws SQLException {
        List<Book> listBook = new ArrayList<>();

        String sql = "SELECT * FROM book";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");

            Book book = new Book(id, title, author, price);
            listBook.add(book);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listBook;
    }

    public boolean deleteBook(Book book) throws SQLException {
        String sql = "DELETE FROM book where book_id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, book.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateBook(Book book) throws SQLException {
        String sql = "UPDATE book SET title = ?, author = ?, price = ?";
        sql += " WHERE book_id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setFloat(3, book.getPrice());
        statement.setInt(4, book.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Book getBook(int id) throws SQLException {
        Book book = null;
        String sql = "SELECT * FROM book WHERE book_id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");

            book = new Book(id, title, author, price);
        }

        resultSet.close();
        statement.close();

        return book;
    }


 */
}


