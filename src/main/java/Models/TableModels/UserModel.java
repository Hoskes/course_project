package Models.TableModels;


import Models.Server;
import com.example.course_project.Config;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {

    private ObservableList<User> items;
    private ResultSet resultSet;

    public UserModel(String users_get_querry, String choice_value) {
        items = FXCollections.observableArrayList();
        PreparedStatement query = null;
        String result_string = null;
        try {
            query = Server.getConnection().prepareStatement(users_get_querry);
            query.setString(1, choice_value);
            resultSet = query.executeQuery();
            while (resultSet.next()) {
                items.add(new User(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),resultSet.getInt(7)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addItem(User item) {
        items.add(item);
    }

    public ObservableList<User> getItems() {
        return items;
    }
    public void addOrder(int user_id,Model model,String adress_point){
        int adress_point_id = findOrderId(adress_point);
        String pre_query = "INSERT INTO `orders`(`user_id`, `bike_id`, `status_id`, `point_created`) VALUES (?,?,?,?)";
        try {
            PreparedStatement query = Server.getConnection().prepareStatement(pre_query);
            query.setInt(1, user_id);
            query.setInt(2, model.getId());
            query.setInt(3, 1);
            query.setInt(4, adress_point_id);
            query.executeUpdate();
            //System.out.println("Task completed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private int findOrderId(String str) {
        try {
            String pre_query = Config.find_point_by_adress;
            PreparedStatement query = Server.getConnection().prepareStatement(pre_query);
            query.setString(1, str);
            ResultSet result = query.executeQuery();
            result.next();
            return result.getInt(1);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    public static void updateOrderState(int order_id, String state){
        int state_id = Server.getServer().getStatesId(state);
        String pre_query = Config.update_orders_state;
        try {
            PreparedStatement query = Server.getConnection().prepareStatement(pre_query);
            query.setInt(1, state_id);
            query.setInt(2, order_id);
            query.executeUpdate();
            //System.out.println("Update order status Task completed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
