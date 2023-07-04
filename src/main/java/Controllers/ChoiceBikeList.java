package Controllers;

import Models.Model;
import Models.Server;
import com.example.course_project.Config;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

public class ChoiceBikeList {

        private ObservableList<String> items;
        private ObservableList<String> points;
        private ArrayList<Model> info;
        private ResultSet resultSet;

        public ChoiceBikeList() {
            points = FXCollections.observableArrayList();
            items = FXCollections.observableArrayList();
            items.add("No choice");
            PreparedStatement query = null;
            String result_string = null;
            try {
                query = Server.getConnection().prepareStatement(Config.find_avalible_models);
                query.setString(1, "Свободен");
                resultSet = query.executeQuery();
                HashSet<String> models1 = new HashSet<>();
                info = new ArrayList<>();
                //info.add(null);
                while (resultSet.next()) {
                    info.add(new Model(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4)));
                    models1.add(resultSet.getString(2));
                }
                    items.addAll(models1);

                Statement statement = Server.getConnection().createStatement();
                ResultSet result = statement.executeQuery(Config.all_points);
                while (result.next()){
                    System.out.println("#");
                    points.add(result.getString("adress"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    public void addItem(String item) {
        items.add(item);
    }
    public void addPointItem(String item) {
        points.add(item);
    }
    public ObservableList<String> getItems() {
        return items;
    }
    public ObservableList<String> getPointItems() {
        return points;
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
    public static void sendCurrentBikeUpdateStatus(int cur_state_id,int bike_id){
        String pre_query = "UPDATE `bikes` SET cur_state=? WHERE id=?";
        try {
            PreparedStatement query = Server.getConnection().prepareStatement(pre_query);
            query.setInt(1, cur_state_id);
            query.setInt(2, bike_id);
            query.executeUpdate();
            System.out.println("Task completed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}