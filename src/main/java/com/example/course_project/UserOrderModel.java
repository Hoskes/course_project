package com.example.course_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOrderModel {

        private ObservableList<Order> items;
        private ResultSet resultSet;

        public UserOrderModel(String users_get_querry,String choice_value) {
            items = FXCollections.observableArrayList();
            PreparedStatement query = null;
            String result_string = null;
            try {
                query = Server.getConnection().prepareStatement(users_get_querry);
                query.setString(1, choice_value);
                resultSet = query.executeQuery();
                while (resultSet.next()) {
                    items.add(new Order(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
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
    public void addOrder(Model model,String current_adress){
//        String pre_query = "INSERT INTO `orders`(`user_id`, `bike_id`, `status_id`, `point_created`, `user_bike_model`) \n" +
//                "VALUES \n" +
//                "(?,?,?,?,?)";
//        try {
//            PreparedStatement query = Server.getConnection().prepareStatement(pre_query);
//            query.setInt(1, Profile.getId());
//            query.setInt(2, model.getId());
//            query.setInt(1, );
//            query.setInt(2, bike_id);
//            query.setInt(1, cur_state_id);
//            query.executeUpdate();
//            System.out.println("Task completed");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    }

}