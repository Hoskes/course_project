package com.example.course_project;

public class Order {
    private int id;
    private int user_id;
    private int bike_id;
    private String status;
    private String point_created_name;
    private String user_bike_model;
    private int point_id;

    public Order(int id, int user_id, int bike_id, String status, String point_created_name, String user_bike_model, int point_id) {
        this.id = id;
        this.user_id = user_id;
        this.bike_id = bike_id;
        this.status = status;
        this.point_created_name = point_created_name;
        this.user_bike_model = user_bike_model;
        this.point_id = point_id;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getBike_id() {
        return bike_id;
    }

    public String getStatus() {
        return status;
    }

    public String getPoint_created_name() {
        return point_created_name;
    }

    public String getUser_bike_model() {
        return user_bike_model;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setBike_id(int bike_id) {
        this.bike_id = bike_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPoint_created_name(String point_created_name) {
        this.point_created_name = point_created_name;
    }

    public void setUser_bike_model(String user_bike_model) {
        this.user_bike_model = user_bike_model;
    }
// Конструктор, геттеры и сеттеры
}
