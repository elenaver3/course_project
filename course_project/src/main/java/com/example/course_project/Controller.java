package com.example.course_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {
    public TextField userLogin;
    public PasswordField userPassword;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private boolean isConnected;
    private int role;
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
        Connection connection = connectionTry.getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT user_role FROM users WHERE user_name='" + userLogin.getText() + "' AND user_password='" + userPassword.getText() + "'";
            Posts result = new Posts(query, statement);

            if (result.getResult().size() == 1) {
                isConnected = true;
                role = (int)result.getResult().get(0).getValue("user_role");
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
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    private void switchToAdmin(ActionEvent event) throws IOException {

    }
}