package Controllers;

import Controllers.ClientController;
import Models.Server;
import Models.TableModels.Profile;
import com.example.course_project.Config;
import com.example.course_project.PasswordHashing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class admin_pannel extends ManagerController {


    public Button user_changes__save;
    public TextField num_pass_id;
    public TextField ser_pass_field;
    public ChoiceBox role_change_id;
    public TextField password_change;
    public TextField login_change;
    public TextField user_change_id;

//    @Override
//    void initialize() {
//        tableInit(); //инициируем список переменных посредством запроса
//        //задаем начальные значения
//        set_role.setText(Profile.getCurrentRole());
//        first_name.setText(Profile.getName()[0]);
//        last_name.setText(Profile.getName()[1]);
//        f_name.setText(Profile.getName()[2]);
//        adress.setText(Profile.getAdress());
//        set_role.setText(Profile.getCurrentRole());
//        //добавляем форматирование полей
//        addListenerNameFormat(first_name, Config.regex_name);
//        addListenerNameFormat(last_name,Config.regex_name);
//        addListenerNameFormat(f_name,"|"+Config.regex_name);
//        addListenerNameFormat(adress,Config.adress);
//        addListenerNameFormat(new_password,Config.regex_login); //новый пароль на формат
//
//
//        addListenerNameFormat(login_change,Config.regex_login);
//        addListenerNameFormat(password_change,Config.regex_login);
//
//        role_change_id.setItems(Server.getServer().getStates(Config.all_roles));
//        addListenerNameFormat(ser_pass_field,"([0-9]{4})");
//        addListenerNameFormat(num_pass_id,"([0-9]{6})");
//
//
//        set_id.setText(""+Profile.getId());
//
//        initChoice(); //инициируем список посредством запроса
//    }
////    public void allow(ActionEvent actionEvent) {
////    }
//    public void saveUserChangedInfo(ActionEvent actionEvent) {
////        if(Server.getServer().isUserDocsInSystem(Integer.parseInt(user_change_id.getId()))) {
////            try {
////                String s = "";
////                Profile p = new Profile(Integer.parseInt(user_change_id.getId()),false);
////                if (role_change_id.getSelectionModel().getSelectedItem() != null) {
////                    s+="role_id =?";//+Server.getServer().getRoleId(role_change_id.getSelectionModel().getSelectedItem().toString());
////                if(login_change.getText()!=null & password_change.getText()!=null){
////                    if (login_change.getText().matches(Config.regex_login) & password_change.getText().matches(Config.regex_login)){
////                        //копипаста
////                        String pre_query = Config.create_authorization_record;
////                        PreparedStatement query = null;
////                        String result_string = null;
////                        try {
////                            query = Server.getConnection().prepareStatement(pre_query);
////                            query.setInt(1,Integer.parseInt(user_change_id.getId()));
////                            query.setString(2,login_change.getText());
////                            query.setString(3,PasswordHashing.hashPassword(password_change.getText()));
////                            query.executeUpdate();
////
////                        } catch (SQLException e) {
////                            throw new RuntimeException(e);
////                        }
////                    }
////                }
////                if (ser_pass_field.getText()!=null & num_pass_id.getText()!=null){
////                    if (ser_pass_field.getText().matches("([0-9]{4})") & num_pass_id.getText().matches("([0-9]{6})")){
////                        PreparedStatement query = null;
////                        PreparedStatement new_query = null;
////                        PreparedStatement second_query = null;
////                        String result_string = null;
////                        int docs_id =0;
////                        try {
////                            query = Server.getConnection().prepareStatement(Config.send_docs);
////                            query.setString(1,ser_pass_field.getText());
////                            query.setString(2,num_pass_id.getText());
////                            query.executeUpdate();
////                            new_query = Server.getConnection().prepareStatement(Config.find_docs_id);
////                            new_query.setString(1,ser_pass_field.getText());
////                            new_query.setString(2,num_pass_id.getText());
////                            ResultSet resultSet = new_query.executeQuery();
////                            resultSet.next();
////                            docs_id = resultSet.getInt(1);
////
////
////                            second_query = Server.getConnection().prepareStatement(Config.update_user_docs_id);
////                            second_query.setInt(1,docs_id);
////                            second_query.setInt(2,Integer.parseInt(user_change_id.getText()));
////                            second_query.executeUpdate();
////                        } catch (SQLException e) {
////                            throw new RuntimeException(e);
////                        }
////                    }
////                    }
////                }
////            } catch (SQLException e) {
////                throw new RuntimeException(e);
////            }
////        }
//    }
}
