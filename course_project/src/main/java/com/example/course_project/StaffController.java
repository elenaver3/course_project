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

public class StaffController implements Initializable {
    //Text field

    public TextField enterF;
    public TextField enterI;
    public TextField enterO;
    public TextField enterInn;
    public TextField enterAddress;
    public TextField enterPhone;

    // Label
    public Label rightEnter;

    // Button
    public Button backBtn;
    public Button addData;
    public Button updatePost;
    public Button addNewPost;
    public Button updateStaff;
    public Button deleteStaff;
    public Button updateTableStaff;

    // Table Column
    public TableColumn<Staff, String> idStaff;
    public TableColumn<Staff, String> addressStaff;
    public TableColumn<Staff, String> second_nameStaff;
    public TableColumn<Staff, String> last_nameStaff;
    public TableColumn<Staff, String> first_nameStaff;
    public TableColumn<Staff, String> phoneNumber;


    // Table view
    public TableView<Staff> staffTable;


    //selected
    private int selectedStaff;
    //other variables
    private Stage stage;
    private Scene scene;
    private Parent root;


    //Models
    private StaffModel staffModel;
    private Connection connection;


    public StaffController() throws Exception {
        ConnectionTry connectionTry = new ConnectionTry();
        connection = connectionTry.getConnection();

        staffTable = new TableView<Staff>();

        enterF = new TextField();
        enterI = new TextField();
        enterO = new TextField();
        enterInn = new TextField();
        enterAddress = new TextField();

        try {
            staffModel = new StaffModel(connection);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*


         */
    }


    public void addStaffView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-staff-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateStaff(ActionEvent event) throws IOException {
        selectedStaff = staffTable.getSelectionModel().getSelectedItem().getId();

        root = FXMLLoader.load(getClass().getResource("update-staff-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void deleteStaff(ActionEvent event) throws SQLException {
        selectedStaff = staffTable.getSelectionModel().getSelectedItem().getId();
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

}
