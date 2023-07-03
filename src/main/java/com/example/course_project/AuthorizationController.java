package com.example.course_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class AuthorizationController {
    @FXML
    private TextField login_value;
    @FXML
    private PasswordField password_value;
    @FXML
    private void toRegistrationPage(ActionEvent actionEvent) {
        Main.loadScene(actionEvent,"registration_panel.fxml");
    }
    @FXML
    private void checkIsValid(ActionEvent actionEvent) {
        try {
            if(Server.enterTheSystem(login_value.getText(),password_value.getText())){
                System.out.println("You are entered!");
                Main.loadScene(actionEvent,"client_view.fxml"); ///ВОТКНУТЬ В SWITCH
                try {
                    switch (User.getCurrentRole()) {
                        case "Клиент":
                            Main.loadScene(actionEvent, "");
                            break;
                        case "Менеджер проката":
                            break;
                        case "Администратор":
                            break;
                    }
                }catch (Exception e){
                    System.out.println("ОШИБКА В checkisValid");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
