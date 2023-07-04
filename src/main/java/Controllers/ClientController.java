package Controllers;

import Models.Model;
import Models.Profile;
import Models.UserOrderModel;
import com.example.course_project.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ClientController {
    public TableColumn<Order,String> order_id;
    public TableColumn<Order,String> bike_id;
    public TableColumn<Order,String> point_string;
    public TableColumn<Order,String> state;
    public Button save_button;
    public Label transmission_count_label;
    public Label type_label;
    public ChoiceBox<String> choicebox;
    public Button save_changes;
    public Button upd_button;
    public ChoiceBox<String> adress_point;
    @FXML
    private TextField set_role;
    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField f_name;
    @FXML
    private TextField adress;
    @FXML
    private TableView people_table;
    private Model found = null;
    private ChoiceBikeList choice_box;
    private UserOrderModel model;

    @FXML
    void initialize() {
        tableInit();

        set_role.setText(Profile.getCurrentRole());
        first_name.setText(Profile.getName()[0]);
        last_name.setText(Profile.getName()[1]);
        f_name.setText(Profile.getName()[2]);
        adress.setText(Profile.getAdress());
        set_role.setText(Profile.getCurrentRole());

        addListenerNameFormat(first_name,Config.regex_name);
        addListenerNameFormat(last_name,Config.regex_name);
        addListenerNameFormat(f_name,"|"+Config.regex_name);
        addListenerNameFormat(adress,Config.adress);
        initChoice();
    }
    private void initChoice(){
        choice_box = new ChoiceBikeList();
        adress_point.setItems(choice_box.getPointItems());
        choicebox.setItems(choice_box.getItems());
        choicebox.setValue("No choice");
        //adress_point.setValue();
    }
    private void tableInit(){
        model = new UserOrderModel(Config.find_user_orders,""+Profile.getId());
        people_table.setItems(model.getItems());
        order_id.setCellValueFactory(new PropertyValueFactory<>("id"));;
        bike_id.setCellValueFactory(new PropertyValueFactory<>("bike_id"));
        point_string.setCellValueFactory(new PropertyValueFactory<>("point_created_name"));
        state.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private static void addListenerNameFormat(TextField t,String regex){
        t.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches(regex)) {
                t.setStyle("-fx-text-fill: black; ");
            }else {
                t.setStyle("-fx-text-fill: red; ");
            }
        });
    }
    private static boolean checkFormat(String str,String regex){
        if(str.matches(regex)){
            return true;
        }else{
            return false;
        }
    }
    @FXML
    public void updateRole(ActionEvent e){
            set_role.setText(Profile.getCurrentRole());

    }

    @FXML
    public void save_changes(ActionEvent actionEvent) {
        //TextField[] t = {first_name, last_name, f_name};
        String changing_list = "";
        ArrayList<String> values= new ArrayList<>();
        if (checkFormat(first_name.getText(), Config.regex_name) & !first_name.getText().equals(Profile.getName()[0])) {
            Profile.getName()[0] = first_name.getText();
            changing_list+="first_name=?, ";
            values.add(first_name.getText());
        }
        if (checkFormat(last_name.getText(), Config.regex_name) & !last_name.getText().equals(Profile.getName()[1])) {
            Profile.getName()[1] = last_name.getText();
            changing_list+="last_name=?, ";
            values.add(last_name.getText());
        }
        if (checkFormat(f_name.getText(), Config.regex_name) & !f_name.getText().equals(Profile.getName()[2])) {
            Profile.getName()[2] = f_name.getText();
            changing_list+="f_name=?, ";
            values.add(f_name.getText());
        }
        if (checkFormat(adress.getText(), Config.adress) & !adress.getText().equals(Profile.getAdress())) {
            Profile.setAdress(adress.getText());
            changing_list+="adress=?, ";
            values.add(adress.getText());
        }
        if(!changing_list.equals("")) {
            Profile.updateUserInfo(changing_list.substring(0, changing_list.length() - 2), values);
            Profile.setNewToDefaultValues();
        }
        //System.out.println(User.getName()[0]);
    }

    public void updatePanel(Event event) {
    }
    @FXML
    public void update_bike_status(ActionEvent actionEvent) {
        if(found!=null) {
            choice_box.sendCurrentBikeUpdateStatus(2, found.getId());
            model.addOrder(found,adress_point.getValue());
            initChoice();
            tableInit();
        }
    }
    @FXML
    public void chamge_list(ActionEvent contextMenuEvent) {
        if(choicebox.getSelectionModel().getSelectedItem()!=null)
            found = choice_box.findInfo(choicebox.getSelectionModel().getSelectedItem());
        else{
            found=null;
        }
        if (found != null) {
            System.out.println(found.getModel_name());
            transmission_count_label.setText("" + found.getTransmission_count());
            type_label.setText(found.getType());transmission_count_label.setStyle("-fx-text-fill: black; ");
            type_label.setStyle("-fx-text-fill: black; ");

        }else{
            transmission_count_label.setText("Модель не выбрана");
            transmission_count_label.setStyle("-fx-text-fill: red; ");
            type_label.setText("Модель не выбрана");
            type_label.setStyle("-fx-text-fill: red; ");
        }
    }
    @FXML
    public void update_choice_list(ActionEvent actionEvent) {
        initChoice();
    }

    public void update_bikes_list(Event event) {
    }
}
