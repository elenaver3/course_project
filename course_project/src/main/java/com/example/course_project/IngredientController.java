package com.example.course_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class IngredientController implements Initializable {

    //Text field
    public TextField enterIngName;
    public TextField enterIngMU;

    // Button
    public Button backBtn;
    public Button addData;
    public Button updatePost;
    public Button addNewPost;
    public Button updateIng;
    public Button deleteIng;
    public Button updateTableIng;
    // Table Column
    public TableColumn<Client, String> first_name;
    public TableColumn<Client, String> last_name;
    public TableColumn<Client, String> second_name;
    public TableColumn<Client, String> address;
    public TableColumn<Ingredient, String> idIngredient;
    public TableColumn<Ingredient, String> ingredientName;
    public TableColumn<Ingredient, String> muName;

    // Table view

    public TableView<Ingredient> ingTable;
    public Button backBtnChef;
    public TableColumn<Ingredient, String> ingAmount;
    public TextField enterIngAmount;


    //selected
    private int selectedIng;

    //other variables
    private Stage stage;
    private Scene scene;
    private Parent root;


    //Models

    private IngredientsModel ingredientsModel;
    private IngredientsInDishModel ingredientsInDishModel;
    private Connection connection;


    public IngredientController() throws Exception {
        ConnectionTry connectionTry = new ConnectionTry();
        connection = connectionTry.getConnection();

        try {
            ingredientsModel = new IngredientsModel(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            ingredientsInDishModel = new IngredientsInDishModel(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void backToMenu(ActionEvent event) throws IOException, SQLException {
        connection.close();
        root = FXMLLoader.load(getClass().getResource("admin-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


    public void addIngredientView(ActionEvent event) throws SQLException, IOException {
        root = FXMLLoader.load(getClass().getResource("add-ingredient-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void updateIngredients(ActionEvent event) throws IOException {
        selectedIng = ingTable.getSelectionModel().getSelectedItem().getId();

        root = FXMLLoader.load(getClass().getResource("update-ingredient-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void deleteIngredients(ActionEvent event) throws SQLException {
        selectedIng = ingTable.getSelectionModel().getSelectedItem().getId();
        ingredientsModel.deleteIngredients(selectedIng);
    }

    public void onUpdateTableIng(ActionEvent event) {
        ObservableList<Ingredient> temp = FXCollections.observableArrayList();
        Posts temp_posts = ingredientsModel.getIngredients();
        for (Post post:temp_posts.getResult()) {
            int id = (int)post.getValue("id");
            String name = (String) post.getValue("name");
            String measurementUnit = (String) post.getValue("measurementUnit");
            int amount = (int) post.getValue("amount");
            temp.add(new Ingredient(id, name, measurementUnit));
        }

        idIngredient.setCellValueFactory(new PropertyValueFactory<>("id"));
        ingredientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        muName.setCellValueFactory(new PropertyValueFactory<>("measurementUnit"));
        ingAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        ingTable.setItems(temp);
    }

    public void addNewIng(ActionEvent event) throws SQLException {
        ingredientsModel.insertIngredients(enterIngName.getText(), enterIngMU.getText(), enterIngAmount.getText());
    }

    public void backToIng(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ingredients-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void updatePostInIng(ActionEvent event) throws SQLException {
        ingredientsModel.updateIngredients(selectedIng, enterIngName.getText(), enterIngMU.getText(), enterIngAmount.getText());
    }


    public void backToIngChef(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ingredients-view-chef.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void backToMenuChef(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("chef-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void addIngredientViewChef(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-ingredient-view-chef.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void updateIngredientsChef(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("update-ingredient-view-chef.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }
}
