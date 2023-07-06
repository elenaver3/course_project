package com.example.course_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class RegistrationController {
    public Button backBtn;
    public Button submitReg;
    public PasswordField enterPassword;
    public TextField enterLogin;
    public TextField enterData;
    public TextField enterFIO;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Connection connection;

    public RegistrationController() {
        ConnectionTry connectionTry = new ConnectionTry();
        try {
            connection = connectionTry.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void registration(ActionEvent event) throws Exception {


        Registration registration = new Registration(connection, enterFIO.getText(), enterLogin.getText(), enterData.getText(), enterPassword.getText());
    }

    public void backToMenu(ActionEvent event) throws IOException, SQLException {
        connection.close();
        root = FXMLLoader.load(getClass().getResource("first-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
