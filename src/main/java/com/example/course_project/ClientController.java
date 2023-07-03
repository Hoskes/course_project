package com.example.course_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

import java.sql.SQLException;

public class ClientController {
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
    void initialize() {
        set_role.setText(User.getCurrentRole());
        first_name.setText(User.getName()[0]);
        last_name.setText(User.getName()[1]);
        f_name.setText(User.getName()[2]);
        adress.setText(User.getAdress());
    }
    @FXML
    public void updateRole(ActionEvent e){
            set_role.setText(User.getCurrentRole());

    }
    @FXML
    private void changeFirstName(ActionEvent actionEvent) {
    }
    @FXML
    private void checkFirstNameFormat(InputMethodEvent inputMethodEvent) {
//        System.out.println("#");
//        if (first_name.getText()==null | !first_name.getText().matches("[A-z]+|[А-я]+}")) {
//            first_name.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
//        }
    }

    public void checkLastNameFormat(InputMethodEvent inputMethodEvent) {
    }

    public void changeLastName(ActionEvent actionEvent) {
    }

    public void changeFName(ActionEvent actionEvent) {
    }

    public void checkFNameFormat(InputMethodEvent inputMethodEvent) {
    }

    public void ChangeAdress(ActionEvent actionEvent) {
    }

    public void checkAdressFormat(InputMethodEvent inputMethodEvent) {
    }

    public void save_changes(ActionEvent actionEvent) {
    }
}
