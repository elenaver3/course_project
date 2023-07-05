package com.example.course_project;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {
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
    public Button updateTableClients;
    public Button addData;
    public Button updateClient;
    public Button deleteClient;
    public TableColumn first_name;
    public TableColumn last_name;
    public TableColumn second_name;
    public TableColumn inn;
    public TableColumn address;
    public TableView<Post> clientsTable;
    public TextField enterF;
    public TextField enterI;
    public TextField enterO;
    public TextField enterInn;
    public TextField enterAddress;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private boolean isConnected = false;
    private int role;

    private Authorization authorization;
    private ClientsModel clientsModel;
    private Connection connection;

    @FXML
    private Label welcomeText;
    @FXML
    private TextField login;


    public Controller() throws Exception {
        isConnected = false;
        ConnectionTry connectionTry = new ConnectionTry();
        connection = connectionTry.getConnection();
        clientsTable = new TableView<Post>();

    }



    @FXML
    protected void login(ActionEvent event) throws Exception {

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

    public void openClients(ActionEvent event) throws Exception {

        /*
        clientsModel = new ClientsModel(connection);
        ObservableList<Post> temp = FXCollections.observableArrayList();
        Posts temp_posts = clientsModel.getClients();
        temp.addAll(temp_posts.getResult());
        clientsTable.setItems(temp);
        //clientsList.setItems(temp);
        //clientsList = FXCollections.observableArrayList(clientsModel.getClients());

         */

        root = FXMLLoader.load(getClass().getResource("clients-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
/*
        clientsModel = new ClientsModel(connection);
        ObservableList<Post> temp = FXCollections.observableArrayList();
        Posts temp_posts = clientsModel.getClients();
        temp.addAll(temp_posts.getResult());
        clientsTable.setItems(temp);

        StringProperty first_name_prop = new SimpleStringProperty();
        StringProperty last_name_prop = new SimpleStringProperty();
        StringProperty second_name_prop = new SimpleStringProperty();
        StringProperty inn_name_prop = new SimpleStringProperty();
        StringProperty address_name_prop = new SimpleStringProperty();

        first_name_prop.set((String) temp_posts.getResult().get(0).get("first_name"));
        last_name_prop.set((String) temp_posts.getResult().get(0).get("last_name"));
        second_name_prop.set((String) temp_posts.getResult().get(0).get("second_name"));
        inn_name_prop.set((String) temp_posts.getResult().get(0).get("inn"));
        address_name_prop.set((String) temp_posts.getResult().get(0).get("address"));

        first_name.setCellFactory(new PropertyValueFactory<>("first_name_prop"));
        last_name.setCellFactory(new PropertyValueFactory<>("last_name"));
        second_name.setCellFactory(new PropertyValueFactory<>("second_name"));
        inn.setCellFactory(new PropertyValueFactory<>("inn"));
        address.setCellFactory(new PropertyValueFactory<>("address"));*/


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

    public void onUpdateTableClients(ActionEvent event) {

        try {
            clientsModel = new ClientsModel(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ObservableList<Post> temp = FXCollections.observableArrayList();
        Posts temp_posts = clientsModel.getClients();
        temp.addAll(temp_posts.getResult());

        clientsTable.getColumns();

        /*
        StringProperty first_name_prop = new SimpleStringProperty((String) temp.get(0).get("first_name"));
        StringProperty last_name_prop = new SimpleStringProperty((String) temp.get(0).get("last_name"));
        StringProperty second_name_prop = new SimpleStringProperty((String) temp.get(0).get("second_name"));
        StringProperty inn_prop = new SimpleStringProperty((String) temp.get(0).get("inn"));
        StringProperty address_prop = new SimpleStringProperty((String) temp.get(0).get("address"));

         */


        SimpleStringProperty first_name_prop = new SimpleStringProperty((String) temp.get(0).get("first_name"));
        SimpleStringProperty last_name_prop = new SimpleStringProperty((String) temp.get(0).get("last_name"));
        SimpleStringProperty second_name_prop = new SimpleStringProperty((String) temp.get(0).get("second_name"));
        SimpleStringProperty inn_prop = new SimpleStringProperty((String) temp.get(0).get("inn"));
        SimpleStringProperty address_prop = new SimpleStringProperty((String) temp.get(0).get("address"));

        String fname = (String) temp.get(0).get("last_name");
        String iname = (String) temp.get(0).get("first_name");
        String sname = (String) temp.get(0).get("second_name");
        String innname = (String) temp.get(0).get("inn");
        String addressname = (String) temp.get(0).get("address");


        //favoriteBookCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFavoriteBook().getTitle()));
        first_name.setCellValueFactory(data -> new SimpleStringProperty(iname));
        last_name.setCellValueFactory(data -> new SimpleStringProperty(fname));
        second_name.setCellValueFactory(data -> new SimpleStringProperty(sname));
        inn.setCellValueFactory(data -> new SimpleStringProperty(innname));
        address.setCellValueFactory(data -> new SimpleStringProperty(addressname));
        /*
        first_name.setCellValueFactory(new PropertyValueFactory<String, String>("first_name_prop"));
        last_name.setCellValueFactory(new PropertyValueFactory<String, String>("last_name_prop"));
        second_name.setCellValueFactory(new PropertyValueFactory<String, String>("second_name_prop"));
        inn.setCellValueFactory(new PropertyValueFactory<String, String>("inn_prop"));
        address.setCellValueFactory(new PropertyValueFactory<String, String>("address_prop"));

         */


        clientsTable.setItems(temp);

        //clientsTable.getColumns();
        //clientsTable.getItems();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*


         */


    }

    public void addClientView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-client-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void backToClient(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("clients-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        clientsTable.getItems();
    }
}