package Controllers;

import Models.Server;
import Models.TableModels.Profile;
import Models.TableModels.User;
import Models.TableModels.UserModel;

import com.example.course_project.Config;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminController extends ManagerController {

    public TextField user_change_id;
    public TextField login_change;
    public TextField password_change;
    public ChoiceBox<String> role_change_id;
    public TextField ser_pass_field;
    public TextField num_pass_id;
    public Button user_changes_save;
    public TableColumn<User, String> first_name_set;
    public TableColumn<User, String> last_name_set;
    public TableView<User> p_table;

    @Override
    void initialize() {
        //UserTable p = new UserTable();
        UserTableInit();
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
    private void UserTableInit(){
        UserModel p= new UserModel("SELECT * FROM users WHERE ?","1");
        p_table.setItems(p.getItems());
        first_name_set.setCellValueFactory(new PropertyValueFactory<User,String>("first_name"));
        last_name_set.setCellValueFactory(new PropertyValueFactory<User,String>("last_name"));
        //p_table.getColumns().add(first_name_set);
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
//        if(Server.getServer().isUserDocsInSystem(Integer.parseInt(user_change_id.getText()))) {
//            try {
//                String s = "";
//                Profile p = new Profile(Integer.parseInt(user_change_id.getId()),false);
//                if (role_change_id.getSelectionModel().getSelectedItem() != null) {
//                    s += "role_id =?";//+Server.getServer().getRoleId(role_change_id.getSelectionModel().getSelectedItem().toString());
//                    //System.out.println("role checked");
//                    if (login_change.getText() != null & password_change.getText() != null) {
//                        if (login_change.getText().matches(Config.regex_login) & password_change.getText().matches(Config.regex_login)) {
////                        //копипаста
////                        String pre_query = Config.create_authorization_record;
////                        PreparedStatement query = null;
////                        String result_string = null;
////                        try {
////                            query = Server.getConnection().prepareStatement(pre_query);
////                            query.setInt(1,Integer.parseInt(user_change_id.getId()));
////                            query.setString(2,login_change.getText());
////                            query.setString(3, PasswordHashing.hashPassword(password_change.getText()));
////                            query.executeUpdate();
////
////                        } catch (SQLException e) {
////                            throw new RuntimeException(e);
////                        }
//                            //System.out.println("login_condition is true");
//                        }
//                    }
//                    if (ser_pass_field.getText() != null & num_pass_id.getText() != null) {
//                        if (ser_pass_field.getText().matches("([0-9]{4})") & num_pass_id.getText().matches("([0-9]{6})")) {
//                            //System.out.println("docs win");
//
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
//                        }
//                    }
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
}
