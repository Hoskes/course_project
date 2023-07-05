package Controllers;

import Models.TableModels.Order;
import Models.TableModels.Profile;
import Models.TableModels.UserOrderModel;
import com.example.course_project.Config;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminController extends ClientController{
    @FXML
    public TableColumn<Order,String> user_id;

    @Override
    protected void tableInit(){
        model = new UserOrderModel("SELECT orders.id, user_id, bike_id, orders_status.name, points.adress, models.model_name as user_bike_model,points.id FROM orders JOIN orders_status ON orders_status.id = status_id JOIN points ON point_created = points.id JOIN bikes ON bike_id =bikes.id JOIN models ON bikes.model_id = models.id  WHERE ?",""+ 1);
        people_table.setItems(model.getItems());
        order_id.setCellValueFactory(new PropertyValueFactory<>("id"));;
        user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        bike_id.setCellValueFactory(new PropertyValueFactory<>("bike_id"));
        point_string.setCellValueFactory(new PropertyValueFactory<>("point_created_name"));
        state.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
}
