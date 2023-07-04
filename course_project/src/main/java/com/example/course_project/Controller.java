package com.example.course_project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {
    public TextField userLogin;
    public PasswordField userPassword;
    public Button exitBtn;
    public Label rightEnter;
    public Button usersBtn;
    public Button clientsBtn;
    public Button visitsBtn;
    public Button tablesBtn;
    public Button dishesBtn;
    public Button ingredientsBtn;
    public Button backBtn;
    public ListView clientsList;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private boolean isConnected;
    private int role;

    private Authorization authorization;
    private Connection connection;

    @FXML
    private Label welcomeText;
    @FXML
    private TextField login;


    public Controller() {
        isConnected = false;
    }

    @FXML
    protected void login(ActionEvent event) throws Exception {
        ConnectionTry connectionTry = new ConnectionTry();
        connection = connectionTry.getConnection();
        authorization = new Authorization(connection, userLogin.getText(), userPassword.getText());
        role = authorization.getRole();

        if (authorization.getRole() != -1) {
            isConnected = true;
            rightEnter.setText("");
            switch (role) {
                case 1:
                    root = FXMLLoader.load(getClass().getResource("admin-view.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    break;
            }
        }
        else {
            isConnected = false;
            rightEnter.setText("Неверный логин или пароль");
        }
    }

    public void exit(ActionEvent event) throws IOException {
        isConnected = false;
        role = -1;
        root = FXMLLoader.load(getClass().getResource("first-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void openUsers(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("users-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openClients(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("clients-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openVisits(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("visits-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openTables(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("tables-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openDishes(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("dishes-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openIngredients(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ingredients-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void backToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("admin-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}