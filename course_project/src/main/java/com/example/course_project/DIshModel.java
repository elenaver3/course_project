package com.example.course_project;

import java.sql.*;

public class DishModel {

    private Connection connection;
    private Posts dishes;

    public DishModel() {

    }

    public DishModel(Connection connection) throws Exception {
        this.connection = connection;

        Statement statement = connection.createStatement();
        String query = "SELECT * FROM dishes";
        ResultSet resultSet = statement.executeQuery(query);

        dishes = new Posts();

        while (resultSet.next()) {
            Post post = new Post();
            post.addValue("id", resultSet.getInt("id"));
            post.addValue("name", resultSet.getString("name"));
            dishes.addPost(post);
        }
        /*
        statement = connection.createStatement();
        query = "SELECT dishes.name AS dish_name, ingredients.name AS inredients_name, amount\n" +
                "FROM dishes JOIN ingredientsInDish ON dishes.id = ingredientsInDish.id_dish JOIN ingredients ON ingredients.id = ingredientsInDish.id_ingredient";
        resultSet = statement.executeQuery(query);

         */

    }

    public Posts getDishes() {
        return dishes;
    }

    public void updateDishes(Dish dish, String new_name) throws SQLException {
        String sql = "UPDATE dishes SET name = ?";
        sql += " WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        if (new_name.isBlank())
            statement.setString(1, dish.getName());
        else
            statement.setString(1, new_name);

        statement.setInt(2, dish.getId());

        statement.executeUpdate();
        statement.close();
    }

    public void deleteDishes(Dish dish) throws SQLException {
        String sql = "DELETE FROM dish where id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, dish.getId());

        statement.executeUpdate();
        statement.close();

    }

    public void insertDish(String name) throws SQLException {

        String sql = "INSERT INTO dish (name) VALUES (?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);

        statement.executeUpdate();
        statement.close();
    }
}
