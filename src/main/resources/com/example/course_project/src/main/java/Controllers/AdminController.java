package Controllers;

import Models.Server;
import Models.TableModels.Profile;
import com.example.course_project.Config;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AdminController extends ManagerController {

    public TextField user_change_id;
    public TextField login_change;
    public TextField password_change;
    public ChoiceBox<String> role_change_id;
    public TextField ser_pass_field;
    public TextField num_pass_id;
    public Button user_changes_save;
        @Override
    void initialize() {
        tableInit(); //инициируем список переменных посредством запроса
        //задаем начальные значения
        set_role.setText(Profile.getCurrentRole());
        first_name.setText(Profile.getName()[0]);
        last_name.setText(Profile.getName()[1]);
        f_name.setText(Profile.getName()[2]);
        adress.setText(Profile.getAdress());
        set_role.setText(Profile.getCurrentRole());
        //добавляем форматирование полей
        addListenerNameFormat(first_name, Config.regex_name);
        addListenerNameFormat(last_name,Config.regex_name);
        addListenerNameFormat(f_name,"|"+Config.regex_name);
        addListenerNameFormat(new_password,Config.regex_login); //новый пароль на формат


        addListenerNameFormat(login_change, Config.regex_login);
        addListenerNameFormat(password_change,Config.regex_login);

        role_change_id.setItems(Server.getServer().getStates("SELECT * FROM roles"));
        role_change_id.setValue("");

        addListenerNameFormat(ser_pass_field,"([0-9]{4})");
        addListenerNameFormat(num_pass_id,"([0-9]{6})");


        set_id.setText(""+Profile.getId());

        initChoice(); //инициируем список посредством запроса
    }
    public void saveUserChangedInfo(ActionEvent actionEvent) {
            String id_user = user_change_id.getText(); //
            String login = login_change.getText(); //
            String password = password_change.getText(); ///
            String role = role_change_id.getSelectionModel().getSelectedItem();
            //System.out.println(role);
            String seria = ser_pass_field.getText();
            String nomer = num_pass_id.getText();
            if(id_user!=null){
                if (Server.getServer().isUserInSystem(id_user)) {
                    ///
                    if(login!=null){
                        if(login.matches(Config.regex_login)){
                            Profile.ChangeAuthorizeElement(true,login,id_user);
                            ////System.out.println("login");
                        }
                    }
                    if(password!=null){
                        if(password.matches(Config.regex_login)){
                            Profile.ChangeAuthorizeElement(false,password,id_user);
                            //System.out.println("password");
                        }
                    }
                    if(role!=null&!role.equals("")){
                        Profile.setRole(""+Server.getServer().getRoleId(role),id_user);
                    }
                    if(seria!=null & nomer!=null){
                        if(seria.matches("([0-9]{4})")&nomer.matches("([0-9]{6})")){
                            Profile.sendDocs(id_user,seria,nomer);
                            ////System.out.println("seria+nomer");
                        }
                    }
                }
            }
    }
}
