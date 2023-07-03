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
    public void updateRole(ActionEvent e){
        try {
            set_role.setText(User.getCurrentRole());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void changeFirstName(ActionEvent actionEvent) {
    }

    public void checkFirstNameFormat(InputMethodEvent inputMethodEvent) {
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
}
