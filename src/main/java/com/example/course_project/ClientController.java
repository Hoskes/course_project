package com.example.course_project;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClientController {
    public TableColumn order_id;
    public TableColumn user_id;
    public TableColumn bike_id;
    public TableColumn state;
    public TableColumn from_point;
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
        
        set_role.setText(User.getCurrentRole());
        first_name.setText(User.getName()[0]);
        last_name.setText(User.getName()[1]);
        f_name.setText(User.getName()[2]);
        adress.setText(User.getAdress());
        set_role.setText(User.getCurrentRole());

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
            set_role.setText(User.getCurrentRole());

    }

    @FXML
    public void save_changes(ActionEvent actionEvent) {
        //TextField[] t = {first_name, last_name, f_name};
        String changing_list = "";
        ArrayList<String> values= new ArrayList<>();
        if (checkFormat(first_name.getText(), Config.regex_name) & !first_name.getText().equals(User.getName()[0])) {
            User.getName()[0] = first_name.getText();
            changing_list+="first_name=?, ";
            values.add(first_name.getText());
        }
        if (checkFormat(last_name.getText(), Config.regex_name) & !last_name.getText().equals(User.getName()[1])) {
            User.getName()[1] = last_name.getText();
            changing_list+="last_name=?, ";
            values.add(last_name.getText());
        }
        if (checkFormat(f_name.getText(), Config.regex_name) & !f_name.getText().equals(User.getName()[2])) {
            User.getName()[2] = f_name.getText();
            changing_list+="f_name=?, ";
            values.add(f_name.getText());
        }
        if (checkFormat(adress.getText(), Config.adress) & !adress.getText().equals(User.getAdress())) {
            User.setAdress(adress.getText());
            changing_list+="adress=?, ";
            values.add(adress.getText());
        }
        User.updateUserInfo(changing_list.substring(0,changing_list.length()-2),values);
        User.setNewToDefaultValues();
        //System.out.println(User.getName()[0]);
    }

}
