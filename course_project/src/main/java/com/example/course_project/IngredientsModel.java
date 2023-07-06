package com.example.course_project;

import java.sql.*;

public class IngredientsModel {
    private Connection connection;
    private Posts ingredients;

    public IngredientsModel() {

    }

    public IngredientsModel(Connection connection) throws Exception {
        this.connection = connection;

        Statement statement = connection.createStatement();
        String query = "SELECT ingredients.id, ingredients.name, measurementUnits.unit\n" +
                "FROM ingredients JOIN measurementUnits ON ingredients.id_MU = measurementUnits.id";
        ResultSet resultSet = statement.executeQuery(query);

        ingredients = new Posts();

        while (resultSet.next()) {
            Post post = new Post();
            post.addValue("id", resultSet.getInt("id"));
            post.addValue("name", resultSet.getString("name"));
            post.addValue("measurementUnit", resultSet.getString("unit"));
            ingredients.addPost(post);
        }

    }

    public Posts getIngredients() {
        return ingredients;
    }

    public void updateIngredients(Ingredient ingredient, String new_name, String mUnit) throws SQLException {
        int id_unit = 1;
        String[] temp1 = mUnit.split(";");
        String sql = "SELECT id FROM measurementUnits WHERE unit = '" + temp1[0]  + "'";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            id_unit = resultSet.getInt("id");
            statement.close();
        }
        else {
            statement.close();
            sql = "INSERT INTO measurementUnits (unit) VALUES (?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, mUnit);

            statement.executeUpdate();
            statement.close();

            sql = "SELECT id FROM measurementUnits WHERE unit = '" + temp1[0]  + "'";

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            id_unit = resultSet.getInt("id");
            statement.close();
        }

        sql = "UPDATE ingredients SET name = ?, id_MU = ?";
        sql += " WHERE id = ?";

        statement = connection.prepareStatement(sql);

        if (new_name.isBlank())
            statement.setString(1, ingredient.getName());
        else
            statement.setString(1, new_name);

        statement.setInt(2, id_unit);
        statement.setInt(3, ingredient.getId());

        statement.executeUpdate();
        statement.close();
    }

    public void deleteIngredients(Ingredient ing) throws SQLException {
        String sql = "DELETE FROM ingredients where id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ing.getId());

        statement.executeUpdate();
        statement.close();

    }

    public void insertIngredients(String name, String mUnit) throws SQLException {
        int id_unit = 1;
        String sql = "SELECT id FROM measurementUnits";
        sql += " WHERE unit = '" + "'";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, mUnit);

        ResultSet resultSet = statement.executeQuery(sql);
        statement.close();
        if (resultSet.next()) {
            id_unit = resultSet.getInt("id");
        }
        else {
            sql = "INSERT INTO measurementUnits (unit) VALUES (?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, mUnit);

            statement.executeUpdate();
            statement.close();

            sql = "SELECT id FROM measurementUnits";
            sql += " WHERE unit  = '?'";

            statement = connection.prepareStatement(sql);
            statement.setString(1, mUnit);
            resultSet = statement.executeQuery(sql);
            id_unit = resultSet.getInt("id");
            statement.close();
        }

        sql = "INSERT INTO ingredients (name, id_MU) VALUES (?, ?)";

        statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, id_unit);

        statement.executeUpdate();
        statement.close();
    }
}
