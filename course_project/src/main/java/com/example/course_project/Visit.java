package com.example.course_project;

import javafx.beans.property.SimpleStringProperty;

import java.util.Date;
import java.time.DateTimeException;

public class Visit {
    private SimpleStringProperty id;
    private SimpleStringProperty dateTimeStart;
    private SimpleStringProperty dateTimeEnd;
    private SimpleStringProperty table;
    private SimpleStringProperty waiter;
    private int id_staff;
    private int id_table;

    public Visit() {

    }

    public Visit(int id, String dateTimeStart, String dateTimeEnd, String table, String waiter, int id_staff, int id_table) {
        this.id = new SimpleStringProperty();
        this.dateTimeStart = new SimpleStringProperty();
        this.dateTimeEnd = new SimpleStringProperty();
        this.table = new SimpleStringProperty();
        this.waiter = new SimpleStringProperty();
        this.setId(id);
        this.setDateTimeStart(dateTimeStart);
        this.setDateTimeEnd(dateTimeEnd);
        this.setTable(table);
        this.setWaiter(waiter);
        this.id_staff = id_staff;
        this.id_table = id_table;
    }

    public int getId() {
        return Integer.parseInt(id.getValue());
    }

    public void setId(int id) {
        this.id.set(String.valueOf(id));
    }

    public void setDateTimeEnd(String dateTimeEnd) {
        this.dateTimeEnd.set(dateTimeEnd.toString());
    }

    public void setDateTimeStart(String dateTimeStart) {
        this.dateTimeStart.set(dateTimeStart.toString());
    }

    public void setTable(String table) {
        this.table.set(table);
    }
    public void setWaiter(String waiter) {
        this.waiter.set(waiter);
    }

    public String getDateTimeStart() {
        return dateTimeStart.getValue();
    }

    public String getDateTimeEnd() {
        return dateTimeEnd.getValue();
    }

    public String getTable() {
        return table.getValue();
    }

    public String getWaiter() {
        return waiter.getValue();
    }

    public void setId_staff(int id_staff) {
        this.id_staff = id_staff;
    }

    public int getId_staff() {
        return id_staff;
    }

    public int getId_table() {
        return id_table;
    }

    public void setId_table(int id_table) {
        this.id_table = id_table;
    }
}
