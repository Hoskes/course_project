package Controllers;

import Models.Server;
import Models.TableModels.Order;
import Models.TableModels.Profile;
import Models.TableModels.Record;
import Models.TableModels.UserOrderModel;
import com.example.course_project.Config;
import com.example.course_project.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ManagerController extends ClientController {
    @FXML
    public TableColumn<Order, String> user_id;
    public Label username_label;
    @FXML
    public ChoiceBox<String> state_box;
    public Button allow_button;
    public TextField zakaz_on_user_id;
    @FXML
    private Label id_text_label;


    @Override
    protected void tableInit() {
        model = new UserOrderModel("SELECT orders.id, user_id, bike_id, orders_status.name, points.adress, models.model_name as user_bike_model,points.id FROM orders JOIN orders_status ON orders_status.id = status_id JOIN points ON point_created = points.id JOIN bikes ON bike_id =bikes.id JOIN models ON bikes.model_id = models.id  WHERE ?", "" + 1);
        people_table.setItems(model.getItems());
        order_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ;

        state_box.setItems(Server.getServer().getStates(Config.all_order_states));

        user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        bike_id.setCellValueFactory(new PropertyValueFactory<>("bike_id"));
        point_string.setCellValueFactory(new PropertyValueFactory<>("point_created_name"));
        state.setCellValueFactory(new PropertyValueFactory<>("status"));
        TableView.TableViewSelectionModel<Order> selectionModel = people_table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Order>() {
            @Override
            public void changed(ObservableValue<? extends Order> observableValue, Order order, Order t1) {
                if(t1!=null) {
                    if (t1.getId() != 0) {

                        id_text_label.setText("" + t1.getId());
                        try {
                            Record cur_user = new Profile(t1.getUser_id(), false).getFound_user();
                            zakaz_on_user_id.setText(cur_user.get("id"));
                            username_label.setText(cur_user.get("first_name") + " " + cur_user.get("last_name"));


                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
    }
    @FXML
    public void allow(ActionEvent actionEvent) {
        if (people_table.getSelectionModel().getSelectedItem().getStatus()!=null & state_box.getValue()!=null & !people_table.getSelectionModel().getSelectedItem().getStatus().equals(state_box.getValue())) {
            //System.out.println(people_table.getSelectionModel().getSelectedItem().getStatus());
            //System.out.println(state_box.getValue());
            UserOrderModel.updateOrderState(people_table.getSelectionModel().getSelectedItem().getId(),state_box.getValue());
            tableInit();
        }
    }

    @FXML
    public void update_bike_status(ActionEvent actionEvent) { //отправляем запросы на изменения статуса велосипеда и создание нового заказа

        if(found!=null) {
            if(zakaz_on_user_id.getText().matches("[1-9][0-9]*")) {
                if(Server.getServer().isUserInSystem(zakaz_on_user_id.getText())) {
                    choice_box.sendCurrentBikeUpdateStatus(2, found.getId());
                    model.addOrder(Integer.parseInt(zakaz_on_user_id.getText()), found, adress_point.getValue());
                    initChoice();
                    tableInit();

                }
            }
        }
    }
}
