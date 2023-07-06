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

public class TableController implements Initializable {

    //Text field
    public TextField enterTableNumber;
    public TextField enterMaxPeople;

    // Button
    public Button backBtn;
    public Button addData;

    public Button updatePost;
    public Button addNewPost;
    public Button updateTables;
    public Button deleteTables;
    public Button updateTableTables;

    // Table Column
    public TableColumn<Client, String> first_name;
    public TableColumn<Client, String> last_name;
    public TableColumn<Client, String> second_name;
    public TableColumn<Client, String> address;
    public TableColumn<Table, String> idTable;
    public TableColumn<Table, String> tableNumber;
    public TableColumn<Table, String> tableMaxPeople;

    // Table view
    public TableView<Client> clientsTable;
    public TableView<Dish> dishesTable;
    public TableView<Staff> staffTable;
    public TableView<Table> tablesTable;


    //selected
    private int selectedTable;

    //other variables
    private Stage stage;
    private Scene scene;
    private Parent root;
    //Model
    private TableModel tableModel;
    private Connection connection;


    public TableController() throws Exception {
        ConnectionTry connectionTry = new ConnectionTry();
        connection = connectionTry.getConnection();

        clientsTable = new TableView<Client>();
        staffTable = new TableView<Staff>();
        dishesTable = new TableView<Dish>();
        tablesTable = new TableView<Table>();

        try {
            tableModel = new TableModel(connection);
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


    public void addTablesView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-table-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateTables(ActionEvent event) throws IOException {
        selectedTable = tablesTable.getSelectionModel().getSelectedItem().getId();
        root = FXMLLoader.load(getClass().getResource("update-table-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void deleteTables(ActionEvent event) throws SQLException {
        selectedTable = tablesTable.getSelectionModel().getSelectedItem().getId();
        tableModel.deleteTables(selectedTable);
    }

    public void onUpdateTableTables(ActionEvent event) {
        ObservableList<Table> temp = FXCollections.observableArrayList();
        Posts temp_posts = tableModel.getTables();
        for (Post post:temp_posts.getResult()) {
            int id = (int)post.getValue("id");
            String table_number = (String) post.getValue("table_number");
            int max_people = (int) post.getValue("max_people");
            temp.add(new Table(id, table_number, max_people));
        }

        idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableNumber.setCellValueFactory(new PropertyValueFactory<>("table_number"));
        tableMaxPeople.setCellValueFactory(new PropertyValueFactory<>("max_people"));

        tablesTable.setItems(temp);
    }

    public void addNewTable(ActionEvent event) throws SQLException {
        tableModel.insertTables(enterTableNumber.getText(), enterMaxPeople.getText());
    }

    public void backToTable(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("tables-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void updatePostInTable(ActionEvent event) throws SQLException {
        tableModel.updateTables(selectedTable, enterTableNumber.getText(), enterMaxPeople.getText());
    }

    public void backToMenuGuest(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("guest-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void backToMenuWaiter(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("waiter-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
