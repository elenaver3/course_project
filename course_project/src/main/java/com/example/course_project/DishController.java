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
import java.util.ResourceBundle;

public class DishController implements Initializable {

    public TextField enterNameDish;

    public Button backBtn;
    public Button addData;
    public Button updatePost;
    public Button addNewPost;
    public Button addDataIngInDish;
    public Button updateDish;
    public Button deleteDish;
    public Button updateTableDish;


    // Table Column
    public TableColumn<Client, String> first_name;
    public TableColumn<Client, String> last_name;
    public TableColumn<Client, String> second_name;
    public TableColumn<Client, String> address;

    public TableColumn<Dish, String> dishName;
    public TableColumn<Dish, String> idDish;


    // Table view
    public TableView<Client> clientsTable;
    public TableView<Dish> dishesTable;
    public TableView<Staff> staffTable;
    public TableView<Table> tablesTable;
    public Button backBtnChef;


    //selected
    private int selectedDish;

    //other variables
    private Stage stage;
    private Scene scene;
    private Parent root;

    //Models
    private DishModel dishModel;
    private Connection connection;


    public DishController() throws Exception {
        ConnectionTry connectionTry = new ConnectionTry();
        connection = connectionTry.getConnection();

        dishesTable = new TableView<Dish>();

        try {
            dishModel = new DishModel(connection);
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
        /*


         */
    }

    public void addIngredientsInDish(ActionEvent event) throws IOException {
        selectedDish = dishesTable.getSelectionModel().getSelectedItem().getId();
        root = FXMLLoader.load(getClass().getResource("ingredientsInDishes.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addDishView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-dish-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateDishes(ActionEvent event) throws SQLException, IOException {
        selectedDish = dishesTable.getSelectionModel().getSelectedItem().getId();

        root = FXMLLoader.load(getClass().getResource("update-dish-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void deleteDishes(ActionEvent event) throws SQLException {
        selectedDish = dishesTable.getSelectionModel().getSelectedItem().getId();
        dishModel.deleteDishes(selectedDish);
    }

    public void onUpdateTableDishes(ActionEvent event) {
        ObservableList<Dish> temp = FXCollections.observableArrayList();
        Posts temp_posts = dishModel.getDishes();
        for (Post post:temp_posts.getResult()) {
            int id = (int)post.getValue("id");
            String name = (String) post.getValue("name");
            temp.add(new Dish(id, name));
        }

        idDish.setCellValueFactory(new PropertyValueFactory<>("id"));
        dishName.setCellValueFactory(new PropertyValueFactory<>("name"));

        dishesTable.setItems(temp);
    }

    public void updatePostInDishes(ActionEvent event) throws SQLException {
        dishModel.updateDishes(selectedDish, enterNameDish.getText());
    }

    public void backToDish(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("dishes-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void addNewDish(ActionEvent event) throws SQLException {
        dishModel.insertDish(enterNameDish.getText());
    }

    public void backToMenuGuest(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("guest-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void backToDishChef(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("dishes-view-chef.fxml"));
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

    public void addDishViewChef(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-dish-view-chef.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateDishesChef(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("update-dish-view-chef.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void backToMenuWaiter(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("waiter-view-waiter.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
