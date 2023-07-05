package com.example.course_project;

public class Client extends Human  {

    private String inn;

    public Client(String last_name, String first_name, String second_name, String address, String inn) {
        this.setFirst_name(first_name);
        this.setLast_name(last_name);
        this.setSecond_name(second_name);
        this.setAddress(address);
        this.inn = inn;
    }


    public String getInn() {
        return inn;
    }

}
