package com.example.course_project;

import java.sql.*;
import java.util.ArrayList;

public class ClientsModel {
    Connection connection;

    public ClientsModel() throws Exception {
        ConnectionTry connectionTry = new ConnectionTry();
        connection = connectionTry.getConnection();

        /*
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT user_role FROM users WHERE user_name='" + userLogin + "' AND user_password='" + userPassword + "'";
            ResultSet resultSet = statement.executeQuery(query);

            posts = new Posts(resultSet);

            if (posts.getResult().size() == 1) {
                Object temp = posts.getResult().get(0).getValue("user_role");
                role = (int)temp;
            }


            if (resultSet.next()) {
                role = resultSet.getInt("user_role");
            }

            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        */


    }


    protected void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

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


