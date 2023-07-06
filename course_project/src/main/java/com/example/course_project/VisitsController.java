package com.example.course_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class VisitsController implements Initializable {
    public Button backBtn;
    public Button addNewPost;
    public TextField enterDateTimeStart;
    public DatePicker enterDateEnd1;
    public TextField enterDateTimeEnd;
    public TextField enterTableInVisit;
    public TextField enterStaffInVisit;
    public Button updatePost;
    public TableColumn<Visit, String> idVisit;
    public TableColumn<Visit, String> visitTimeStart;
    public TableColumn<Visit, String> visitTimeEnd;
    public TableColumn<Visit, String> visitTable;
    public TableColumn<Visit, String> visitWaiter;
    public Button addVisitDishes;
    public Button addVisitClients;
    public Button addData;
    public Button updateVisit;
    public Button deleteVisit;
    public Button updateTableVisit;
    public TableView<Visit> visitsTable;
    //other variables
    private Stage stage;
    private Scene scene;
    private Parent root;
    private VisitModel visitModel;
    private int selectedVisit;
    private Connection connection;

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

    }

    public void backToVisit(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("visits-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addNewVisit(ActionEvent event) throws SQLException {
        visitModel.insertVisit(enterDateTimeStart.getText(), enterDateTimeEnd.getText(), enterTableInVisit.getText(), enterStaffInVisit.getText());
    }

    public void updatePostInVisits(ActionEvent event) throws SQLException {
        visitModel.updateVisit(selectedVisit, enterDateTimeStart.getText(), enterDateTimeEnd.getText(), enterTableInVisit.getText(), enterStaffInVisit.getText());

    }

    public void addDishesInVisitView(ActionEvent event) {
    }

    public void addClientsInVisitView(ActionEvent event) {
    }

    public void addVisitView(ActionEvent event) {
    }

    public void updateVisits(ActionEvent event) {
    }

    public void deleteVisits(ActionEvent event) {
    }

    public void onUpdateTableVisit(ActionEvent event) {
        ObservableList<Visit> temp = FXCollections.observableArrayList();
        Posts temp_posts = visitModel.getVisits();
        for (Post post:temp_posts.getResult()) {
            int id = (int)post.getValue("id");
            String time_start = (String) post.getValue("time_start");
            String time_end = (String) post.getValue("time_end");
            String table_number = (String) post.getValue("table_number");
            String waiter = (String) post.getValue("waiter");
            int staff_id = (int) post.getValue("staff_id");
            int table_id = (int) post.getValue("table_id");
            temp.add(new Visit(id, time_start, time_end, table_number, waiter, staff_id, table_id));
        }

        visitTable.getColumns();

        idVisit.setCellValueFactory(new PropertyValueFactory<>("id"));
        visitTimeStart.setCellValueFactory(new PropertyValueFactory<>("time_start"));
        visitTimeEnd.setCellValueFactory(new PropertyValueFactory<>("time_end"));
        visitTable.setCellValueFactory(new PropertyValueFactory<>("table_number"));
        visitWaiter.setCellValueFactory(new PropertyValueFactory<>("waiter"));
        visitWaiter.setCellValueFactory(new PropertyValueFactory<>("staff_id"));
        visitWaiter.setCellValueFactory(new PropertyValueFactory<>("table_id"));

        //visitTable.setItems(temp);
    }

    public void backToMenuWaiter(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("visits-view-waiter.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addDishesInVisitViewWaiter(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-dish-view-waiter.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addClientsInVisitViewWaiter(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-client-view-waiter.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addVisitViewWaiter(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-visits-view-waiter.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateVisitsWaiter(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("update-visits-view-waiter.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
