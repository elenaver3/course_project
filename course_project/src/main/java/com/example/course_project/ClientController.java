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

public class ClientController implements Initializable {
    //Text field
    @FXML
    public TextField userLogin;
    public PasswordField userPassword;
    public TextField enterF;
    public TextField enterI;
    public TextField enterO;
    public TextField enterInn;
    public TextField enterAddress;

    // Label
    public Label rightEnter;

    // Button
    public Button backBtn;
    public Button updateTableClients;
    public Button addData;
    public Button updateClient;
    public Button deleteClient;

    // Table Column
    public TableColumn<Client, String> first_name;
    public TableColumn<Client, String> last_name;
    public TableColumn<Client, String> second_name;
    public TableColumn<Client, String> inn;
    public TableColumn<Client, String> address;
    public TableColumn<Client, String> idClient;

    // Table view
    public TableView<Client> clientsTable;

    //selected
    private int selectedClient;

    //other variables
    private Stage stage;
    private Scene scene;
    private Parent root;

    private int role;

    //Models
    private ClientsModel clientsModel;
    private Connection connection;


    public ClientController() throws Exception {
        ConnectionTry connectionTry = new ConnectionTry();
        connection = connectionTry.getConnection();

        clientsTable = new TableView<Client>();

        enterF = new TextField();
        enterI = new TextField();
        enterO = new TextField();
        enterInn = new TextField();
        enterAddress = new TextField();

        try {
            clientsModel = new ClientsModel(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void backToMenu(ActionEvent event) throws Exception {
    connection.close();
    root = FXMLLoader.load(getClass().getResource("admin-view.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    public void onUpdateTableClients(ActionEvent event) {
        ObservableList<Client> temp = FXCollections.observableArrayList();
        Posts temp_posts = clientsModel.getClients();
        for (Post post:temp_posts.getResult()) {
            int id = (int)post.getValue("id");
            String l_name = (String) post.getValue("last_name");
            String f_name = (String) post.getValue("first_name");
            String s_name = (String) post.getValue("second_name");
            String inn = (String) post.getValue("inn");
            String address = (String) post.getValue("address");
            temp.add(new Client(id, l_name, f_name, s_name, address, inn));
        }

        clientsTable.getColumns();

        idClient.setCellValueFactory(new PropertyValueFactory<>("id"));
        first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        second_name.setCellValueFactory(new PropertyValueFactory<>("second_name"));
        inn.setCellValueFactory(new PropertyValueFactory<>("inn"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));

        clientsTable.setItems(temp);
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

    public void updatePostInClients(ActionEvent event) throws Exception {
        clientsModel.updateClients(selectedClient, enterF.getText(), enterI.getText(), enterO.getText(), enterInn.getText(), enterAddress.getText());
    }

    public void updateClient(ActionEvent event) throws IOException {

        selectedClient = clientsTable.getSelectionModel().getSelectedItem().getId();

        root = FXMLLoader.load(getClass().getResource("update-client-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }

    public void deleteClient(ActionEvent event) throws Exception {
        selectedClient = clientsTable.getSelectionModel().getSelectedItem().getId();
        clientsModel.deleteClient(selectedClient);
    }

    public void addNewClient(ActionEvent event) throws SQLException {
        clientsModel.insertClient(enterF.getText(), enterI.getText(), enterO.getText(), enterInn.getText(), enterAddress.getText());
    }

}
