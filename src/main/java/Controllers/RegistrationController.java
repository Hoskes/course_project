package Controllers;

import Models.TableModels.Profile;
import com.example.course_project.Config;
import com.example.course_project.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

import java.sql.SQLException;

public class RegistrationController {
    public TextField first_name;
    public TextField last_name;
    public Button registration;
    public TextField f_name;
    public TextField login;
    public TextField password;
    public TextField password_again;
    public Button to_enter;
    public TextField adress_page;

    @FXML
    public void initialize(){
        addListenerNameFormat(first_name, Config.regex_name);
        addListenerNameFormat(last_name,Config.regex_name);
        addListenerNameFormat(f_name,"|"+Config.regex_name);//отчество
        addListenerNameFormat(login,Config.regex_login);
        addListenerNameFormat(password,Config.regex_login);
        addListenerNameFormat(adress_page,Config.adress);
        checkIsEquals(password_again,password.getText(),password_again.getText());
    }
    private static void addListenerNameFormat(TextField t,String regex){ //форматирование текста
        t.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches(regex)) {
                t.setStyle("-fx-text-fill: black; ");
            }else {
                t.setStyle("-fx-text-fill: red; ");
            }
        });
    }
    private static void checkIsEquals(TextField t, String pass,String double_pass){//повтор пароля валиден?
        t.textProperty().addListener((observable, oldValue, newValue) -> {
            if (pass.equals(double_pass)) {
                t.setStyle("-fx-text-fill: black; ");
            }else {
                t.setStyle("-fx-text-fill: red; ");
            }
        });
    }
    public void checkFirstNameFormat(InputMethodEvent inputMethodEvent) {
        System.out.println("###");
    }

    public void checkLarstNameFormat(InputMethodEvent inputMethodEvent) {
    }
    @FXML
    public void registrate(ActionEvent actionEvent) {
        try {
            Profile p = new Profile(first_name.getText(),last_name.getText(),f_name.getText(),adress_page.getText(),3,login.getText(),password.getText());
            Main.loadScene(actionEvent,"authorization_panel.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkFNameFormat(InputMethodEvent inputMethodEvent) {
    }

    public void checkLogin(InputMethodEvent inputMethodEvent) {
    }

    public void checkPassword(InputMethodEvent inputMethodEvent) {
    }

    public void checkIsEqual(InputMethodEvent inputMethodEvent) {
    }

    public void toEnter(ActionEvent actionEvent) {
        Main.loadScene(actionEvent,"authorization_panel.fxml");
    }
}
