package com.example.course_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ChoiceList {

        private ObservableList<String> items;
        private ArrayList<Model> info;
        private ResultSet resultSet;

        public ChoiceList() {
            items = FXCollections.observableArrayList();
            PreparedStatement query = null;
            String result_string = null;
            try {
                query = Server.getConnection().prepareStatement(Config.find_avalible_models);
                query.setString(1, "Свободен");
                resultSet = query.executeQuery();
                HashSet<String> models1 = new HashSet<>();
                info = new ArrayList<>();
                while (resultSet.next()) {
                    info.add(new Model(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4)));
                    models1.add(resultSet.getString(2));
                }
                    items.addAll(models1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    public void addItem(String item) {
        items.add(item);
    }

    public ObservableList<String> getItems() {
        return items;
    }
    public ArrayList getList(){
            return info;
    }
    public Model findInfo(String selected){
        for (int i = 0; i < info.size(); i++) {
            if (selected.equals(info.get(i).getModel_name())){
                return info.get(i);
            }
        }
        return null;
    }
}