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

public class Controller implements Initializable {

    //Text field
    @FXML
    private TextField login;
    public TextField userLogin;
    public PasswordField userPassword;
    public TextField enterF;
    public TextField enterI;
    public TextField enterO;
    public TextField enterInn;
    public TextField enterAddress;
    public TextField enterPhone;
    public TextField enterNameDish;
    public TextField enterTableNumber;
    public TextField enterMaxPeople;
    public TextField enterIngName;
    public TextField enterIngMU;

    // Label
    public Label rightEnter;

    // Button
    public Button exitBtn;
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
    public Button updatePost;
    public Button addNewPost;
    public Button updateStaff;
    public Button deleteStaff;
    public Button updateTableStaff;
    public Button addDataIngInDish;
    public Button updateDish;
    public Button deleteDish;
    public Button updateTableDish;
    public Button updateTables;
    public Button deleteTables;
    public Button updateTableTables;
    public Button staffBtn;
    public Button updateIng;
    public Button deleteIng;
    public Button updateTableIng;
    public Button updateIngInDish;
    public Button deleteIngInDish;
    public Button updateTableIngInDish;

    // Table Column
    public TableColumn<Client, String> first_name;
    public TableColumn<Client, String> last_name;
    public TableColumn<Client, String> second_name;
    public TableColumn<Client, String> inn;
    public TableColumn<Client, String> address;
    public TableColumn<Client, String> idClient;
    public TableColumn<Staff, String> idStaff;
    public TableColumn<Staff, String> addressStaff;
    public TableColumn<Staff, String> second_nameStaff;
    public TableColumn<Staff, String> last_nameStaff;
    public TableColumn<Staff, String> first_nameStaff;
    public TableColumn<Staff, String> phoneNumber;
    public TableColumn<Dish, String> dishName;
    public TableColumn<Dish, String> idDish;
    public TableColumn<Table, String> idTable;
    public TableColumn<Table, String> tableNumber;
    public TableColumn<Table, String> tableMaxPeople;
    public TableColumn<Ingredient, String> idIngredient;
    public TableColumn<Ingredient, String> ingredientName;
    public TableColumn<Ingredient, String> muName;
    public TableColumn dishAmount;

    // Table view
    public TableView<Client> clientsTable;
    public TableView<Dish> dishesTable;
    public TableView<Staff> staffTable;
    public TableView<Table> tablesTable;
    public TableView<Ingredient> ingTable;


    //selected
    private Client selectedClient;
    private Staff selectedStaff;
    private Dish selectedDish;
    private Table selectedTable;
    private Ingredient selectedIng;

    //other variables
    private Stage stage;
    private Scene scene;
    private Parent root;

    private boolean isConnected = false;
    private int role;

    //Models
    private Authorization authorization;
    private ClientsModel clientsModel;
    private StaffModel staffModel;
    private DishModel dishModel;
    private TableModel tableModel;
    private IngredientsModel ingredientsModel;
    private IngredientsInDishModel ingredientsInDishModel;
    private Connection connection;


    public Controller() throws Exception {
        isConnected = false;
        ConnectionTry connectionTry = new ConnectionTry();
        connection = connectionTry.getConnection();

        clientsTable = new TableView<Client>();
        staffTable = new TableView<Staff>();
        dishesTable = new TableView<Dish>();
        tablesTable = new TableView<Table>();

        enterF = new TextField();
        enterI = new TextField();
        enterO = new TextField();
        enterInn = new TextField();
        enterAddress = new TextField();
        selectedClient = new Client();
        selectedStaff = new Staff();
        selectedDish = new Dish();
        selectedTable = new Table();
        selectedIng = new Ingredient();

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
        try {
            tableModel = new TableModel(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        //selectedStaff = staffTable.getSelectionModel().getSelectedItem();
        //staffModel.deleteStaff(selectedStaff);
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
        //staffModel.updateStaff(selectedStaff, enterF.getText(), enterI.getText(), enterO.getText(), enterAddress.getText(), enterPhone.getText());
    }

    public void addIngredientsInDish(ActionEvent event) throws IOException {
        selectedDish = new Dish(dishesTable.getSelectionModel().getSelectedItem().getId(), dishesTable.getSelectionModel().getSelectedItem().getName());
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
        selectedDish = new Dish(dishesTable.getSelectionModel().getSelectedItem().getId(), dishesTable.getSelectionModel().getSelectedItem().getName());

        root = FXMLLoader.load(getClass().getResource("update-dish-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void deleteDishes(ActionEvent event) throws SQLException {
        selectedDish = dishesTable.getSelectionModel().getSelectedItem();
        //dishModel.deleteDishes(selectedDish);
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
        //dishModel.updateDishes(selectedDish, enterNameDish.getText());
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

    public void addTablesView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-table-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateTables(ActionEvent event) throws IOException {
        selectedTable = new Table(tablesTable.getSelectionModel().getSelectedItem().getId(),
                tablesTable.getSelectionModel().getSelectedItem().getTableNumber(),
                tablesTable.getSelectionModel().getSelectedItem().getMaxPeople());
        root = FXMLLoader.load(getClass().getResource("update-table-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void deleteTables(ActionEvent event) throws SQLException {
        selectedTable = tablesTable.getSelectionModel().getSelectedItem();
        //tableModel.deleteTables(selectedTable);
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

    public void addIngredientView(ActionEvent event) throws SQLException {
        ingredientsModel.insertIngredients(enterIngName.getText(), enterIngMU.getText());
    }

    public void updateIngredients(ActionEvent event) throws IOException {
        selectedIng = new Ingredient(ingTable.getSelectionModel().getSelectedItem().getId(),
                ingTable.getSelectionModel().getSelectedItem().getName(),
                ingTable.getSelectionModel().getSelectedItem().getMeasurementUnit());

        root = FXMLLoader.load(getClass().getResource("update-ingredient-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void deleteIngredients(ActionEvent event) throws SQLException {
        selectedIng = ingTable.getSelectionModel().getSelectedItem();
        ingredientsModel.deleteIngredients(selectedIng);
    }

    public void onUpdateTableIng(ActionEvent event) {
        ObservableList<Ingredient> temp = FXCollections.observableArrayList();
        Posts temp_posts = ingredientsModel.getIngredients();
        for (Post post:temp_posts.getResult()) {
            int id = (int)post.getValue("id");
            String name = (String) post.getValue("name");
            String measurementUnit = (String) post.getValue("measurementUnit");
            temp.add(new Ingredient(id, name, measurementUnit));
        }

        idIngredient.setCellValueFactory(new PropertyValueFactory<>("id"));
        ingredientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        muName.setCellValueFactory(new PropertyValueFactory<>("measurementUnit"));

        ingTable.setItems(temp);
    }

    public void addNewIng(ActionEvent event) throws SQLException {
        ingredientsModel.insertIngredients(enterIngName.getText(), enterIngMU.getText());
    }

    public void backToIng(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ingredient-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void updatePostInIng(ActionEvent event) throws SQLException {
        ingredientsModel.updateIngredients(selectedIng, enterIngName.getText(), enterIngMU.getText());
    }

    public void backToDishes(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dishes-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void addIngInThatDish(ActionEvent event) throws Exception {
        //selectedDish = new Dish(dishesTable.getSelectionModel().getSelectedItem().getId(), dishesTable.getSelectionModel().getSelectedItem().getName());

        //ingredientsInDishModel = new IngredientsInDishModel(connection, selectedDish.getId());
        //ingredientsInDishModel.insertIngInDish(selectedDish, );
    }

    public void updateIngInDish(ActionEvent event) throws IOException {
       // selectedDish = new Dish(dishesTable.getSelectionModel().getSelectedItem().getId(), dishesTable.getSelectionModel().getSelectedItem().getName());

        root = FXMLLoader.load(getClass().getResource("update-ingredientInDish-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void deleteIngInDish(ActionEvent event) {
    }

    public void onUpdateTableIngInDish(ActionEvent event) {
    }
}