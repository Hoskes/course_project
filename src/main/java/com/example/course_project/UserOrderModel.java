package com.example.course_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOrderModel {

        private ObservableList<Order> items;
        private ResultSet resultSet;

        public UserOrderModel() {
            items = FXCollections.observableArrayList();
            PreparedStatement query = null;
            String result_string = null;
            try {
                query = Server.getConnection().prepareStatement(Config.find_user_orders);
                query.setInt(1, Profile.getId());
                resultSet = query.executeQuery();
                while (resultSet.next()) {
                    items.add(new Order(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6)));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    public void addItem(Order item) {
        items.add(item);
    }

    public ObservableList<Order> getItems() {
        return items;
    }
}