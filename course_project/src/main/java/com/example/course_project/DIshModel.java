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

    public Dish getDish(int id_dish) throws SQLException {
        Dish dish = new Dish();
        String sql = "SELECT * FROM dishes WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_dish);
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            dish = new Dish(resultSet.getInt("id"),
                    resultSet.getString("name"));
        }
        statement.close();
        return dish;
    }

    public void updateDishes(int dish_id, String new_name) throws SQLException {
        String sql = "UPDATE dishes SET name = ?";
        sql += " WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        if (new_name.isBlank())
            //statement.setString(1, dish.getName());
            statement.setString(1, " ");
        else
            statement.setString(1, new_name);

        statement.setInt(2, dish_id);

        statement.executeUpdate();
        statement.close();
    }

    public void deleteDishes(int dish_id) throws SQLException {
        String sql = "DELETE FROM dishes where id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, dish_id);

        statement.executeUpdate();
        statement.close();

    }

    public void insertDish(String name) throws SQLException {

        String sql = "INSERT INTO dishes (name) VALUES (?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);

        statement.executeUpdate();
        statement.close();
    }
}
