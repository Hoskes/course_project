package com.example.course_project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    @FXML
    void initialize() {
        UserOrderModel model = new UserOrderModel();
        people_table.setItems(model.getItems());
        order_id.setCellValueFactory(new PropertyValueFactory<>("id"));;
        bike_id.setCellValueFactory(new PropertyValueFactory<>("bike_id"));
        point_string.setCellValueFactory(new PropertyValueFactory<>("status"));
        state.setCellValueFactory(new PropertyValueFactory<>("point_created_name"));

        ChoiceList choice_box = new ChoiceList();
        choicebox.setItems(choice_box.getItems());
        choicebox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Model found = choice_box.findInfo(choicebox.getItems().get(t1.intValue()));
                transmission_count_label.setText(""+found.getTransmission_count());
                type_label.setText(found.getType());
            }
        });
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
        Profile.updateUserInfo(changing_list.substring(0,changing_list.length()-2),values);
        Profile.setNewToDefaultValues();
        //System.out.println(User.getName()[0]);
    }

    public void updatePanel(Event event) {
    }
}
