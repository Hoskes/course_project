package Controllers;

import com.example.course_project.Main;
import Models.TableModels.Profile;
import Models.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
                //Main.loadScene(actionEvent,"client_view.fxml"); ///ВОТКНУТЬ В SWITCH
                try {
                    System.out.println(Profile.getCurrentRole());
                    switch (Profile.getCurrentRole()) {

                        case "Клиент":
                            Main.loadScene(actionEvent, "client_view.fxml");
                            break;
                        case "Менеджер проката":
                            break;
                        case "Администратор":
                            Main.loadScene(actionEvent, "administrator_panel.fxml");
                            break;
                    }
                }catch (Exception e){
                    System.out.println("ОШИБКА В checkisValid");
                    throw new RuntimeException();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
