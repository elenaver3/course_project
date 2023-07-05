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
    public Button updateTableClients;
    public Button addData;
    public Button updateClient;
    public Button deleteClient;
    public TableColumn<Client, String> first_name;
    public TableColumn<Client, String> last_name;
    public TableColumn<Client, String> second_name;
    public TableColumn<Client, String> inn;
    public TableColumn<Client, String> address;
    public TableView<Client> clientsTable;
    public TextField enterF;
    public TextField enterI;
    public TextField enterO;
    public TextField enterInn;
    public TextField enterAddress;
    public TableColumn<Client, String> idClient;
    public Button updatePost;
    public Button addNewPost;
    public TableView<Dish> dishesTable;
    public TableView<Staff> staffTable;

    public Button updateStaff;
    public Button deleteStaff;
    public Button updateTableStaff;
    public TextField enterPhone;
    public TableColumn<Staff, String> idStaff;
    public TableColumn<Staff, String> addressStaff;
    public TableColumn<Staff, String> second_nameStaff;
    public TableColumn<Staff, String> last_nameStaff;
    public TableColumn<Staff, String> first_nameStaff;
    public TableColumn<Staff, String> phoneNumber;
    public TableColumn<Dish, String> dishName;
    public TableColumn<Dish, String> idDish;
    public Button addDataIngInDish;
    public Button updateDish;
    public Button deleteDish;
    public Button updateTableDish;
    public TextField enterNameDish;
    public TableView tablesTable;
    public TableColumn idTable;
    public TableColumn tableNumber;
    public TableColumn tableMaxPeople;
    public Button updateTables;
    public Button deleteTables;
    public Button updateTableTables;

    private Client selectedClient;
    private Staff selectedStaff;
    private Dish selectedDish;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private boolean isConnected = false;
    private int role;

    private Authorization authorization;
    private ClientsModel clientsModel;
    private StaffModel staffModel;
    private DishModel dishModel;
    private Connection connection;

    @FXML
    private TextField login;


    public Controller() throws Exception {
        isConnected = false;
        ConnectionTry connectionTry = new ConnectionTry();
        connection = connectionTry.getConnection();

        clientsTable = new TableView<Client>();
        staffTable = new TableView<Staff>();
        dishesTable = new TableView<Dish>();

        enterF = new TextField();
        enterI = new TextField();
        enterO = new TextField();
        enterInn = new TextField();
        enterAddress = new TextField();
        selectedClient = new Client();
        selectedStaff = new Staff();
        selectedDish = new Dish();

        try {
            clientsModel = new ClientsModel(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            staffModel = new StaffModel(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dishModel = new DishModel(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

        selectedClient = new Client(clientsTable.getSelectionModel().getSelectedItem().getId(), clientsTable.getSelectionModel().getSelectedItem().getLast_name(), clientsTable.getSelectionModel().getSelectedItem().getFirst_name(), clientsTable.getSelectionModel().getSelectedItem().getSecond_name(), clientsTable.getSelectionModel().getSelectedItem().getInn(), clientsTable.getSelectionModel().getSelectedItem().getAddress());

        root = FXMLLoader.load(getClass().getResource("update-client-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }

    public void deleteClient(ActionEvent event) throws Exception {
        selectedClient = clientsTable.getSelectionModel().getSelectedItem();
        clientsModel.deleteClient(selectedClient);
    }

    public void addNewClient(ActionEvent event) throws SQLException {
        clientsModel.insertClient(enterF.getText(), enterI.getText(), enterO.getText(), enterInn.getText(), enterAddress.getText());
    }

    public void openStaff(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("staff-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addStaffView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-staff-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateStaff(ActionEvent event) throws IOException {
        selectedStaff = new Staff(staffTable.getSelectionModel().getSelectedItem().getId(),
                staffTable.getSelectionModel().getSelectedItem().getLast_name(),
                staffTable.getSelectionModel().getSelectedItem().getFirst_name(),
                staffTable.getSelectionModel().getSelectedItem().getSecond_name(),
                staffTable.getSelectionModel().getSelectedItem().getAddress(),
                staffTable.getSelectionModel().getSelectedItem().getPhone_number());

        root = FXMLLoader.load(getClass().getResource("update-staff-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void deleteStaff(ActionEvent event) throws SQLException {
        selectedStaff = staffTable.getSelectionModel().getSelectedItem();
        staffModel.deleteStaff(selectedStaff);
    }

    public void onUpdateTableStaff(ActionEvent event) {
        ObservableList<Staff> temp = FXCollections.observableArrayList();
        Posts temp_posts = staffModel.getStaff();
        for (Post post:temp_posts.getResult()) {
            int id = (int)post.getValue("id");
            String l_name = (String) post.getValue("last_name");
            String f_name = (String) post.getValue("first_name");
            String s_name = (String) post.getValue("second_name");
            String address = (String) post.getValue("address");
            String phone_number = (String) post.getValue("phone_number");
            temp.add(new Staff(id, l_name, f_name, s_name, address, phone_number));
        }

        staffTable.getColumns();

        idStaff.setCellValueFactory(new PropertyValueFactory<>("id"));
        last_nameStaff.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        first_nameStaff.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        second_nameStaff.setCellValueFactory(new PropertyValueFactory<>("second_name"));
        addressStaff.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phone_number"));

        staffTable.setItems(temp);
    }

    public void addNewStaff(ActionEvent event) throws SQLException {
        staffModel.insertStaff(enterF.getText(), enterI.getText(), enterO.getText(), enterAddress.getText(), enterPhone.getText());
    }

    public void backToStaff(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("staff-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updatePostInStaff(ActionEvent event) throws SQLException {
        staffModel.updateStaff(selectedStaff, enterF.getText(), enterI.getText(), enterO.getText(), enterAddress.getText(), enterPhone.getText());
    }

    public void addIngredientsInDish(ActionEvent event) {
    }

    public void addDishView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-dish-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateDishes(ActionEvent event) throws SQLException, IOException {
        selectedDish = new Dish(dishesTable.getSelectionModel().getSelectedItem().getId(), dishesTable.getSelectionModel().getSelectedItem().getName());

        root = FXMLLoader.load(getClass().getResource("update-dish-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void deleteDishes(ActionEvent event) throws SQLException {
        selectedDish = dishesTable.getSelectionModel().getSelectedItem();
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

    public void addTablesView(ActionEvent event) {
    }

    public void updateTables(ActionEvent event) {
    }

    public void deleteTables(ActionEvent event) {
    }

    public void onUpdateTableTables(ActionEvent event) {
    }

    public void addNewTable(ActionEvent event) {
    }

    public void backToTable(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("tables-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void updatePostInTable(ActionEvent event) {
    }
}