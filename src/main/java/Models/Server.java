package Models;

import Models.TableModels.Order;
import Models.TableModels.Profile;

import com.example.course_project.Config;
import com.example.course_project.PasswordHashing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Server { //установка соединения с бд + проверка валидности данных при авторизации
    private static Server server;
    private static Connection connection;
    private Server() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    Config.db_url,
                    Config.db_user, Config.db_pass);
        }
        catch(ClassNotFoundException | SQLException e){
            ////System.out.println(e);
        }
    }
    public static Server getServer() { //создаем\получаем текущую ссылку на сервер
        if (server == null) {
            server = new Server();
        }
        return server;
    }

    public static Connection getConnection() {
        return connection;
    } //получаем текущее подключение


    public static boolean enterTheSystem(String user_login,String user_password) throws SQLException { //отправка запроса
        String pre_query = "SELECT user_id FROM `authorization` WHERE login=? AND password=?";
        PreparedStatement query =connection.prepareStatement(pre_query);
        query.setString(1,user_login);
        query.setString(2,PasswordHashing.hashPassword(user_password));
        ResultSet result = query.executeQuery();
        if (result.next()) {
            new Profile(result.getInt(1),true);
            return true;
        }
        return false;
    }
    public ObservableList<String> getStates(String str){
        ObservableList<String> items = FXCollections.observableArrayList();
        try {
            Statement statement = Server.getConnection().createStatement();
            ResultSet result = statement.executeQuery(str);
            while (result.next()){
                items.add(result.getString(2));
                //System.out.println(result.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }
    public int getStatesId(String s){
        try {
            PreparedStatement query = connection.prepareStatement(Config.find_id_by_state);

            query.setString(1, s);
            ResultSet result = query.executeQuery();
            result.next();
            return result.getInt(1);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    public int getRoleId(String s){
        try {
            PreparedStatement query = connection.prepareStatement("SELECT id FROM roles WHERE name =?");

            query.setString(1, s);
            ResultSet result = query.executeQuery();
            result.next();
            return result.getInt(1);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    public boolean isUserInSystem(String s){
        try {
            PreparedStatement query = connection.prepareStatement(Config.find_user_by_id);
            query.setString(1, s);
            ResultSet result = query.executeQuery();
            if(result.next()){
                return true;
            }
            else return false;
        }catch (SQLException e){
            return false;
        }
    }
    public boolean isUserDocsInSystem(int id){
        try {
            PreparedStatement query = connection.prepareStatement(Config.find_user_docs);
            query.setInt(1, id);
            ResultSet result = query.executeQuery();
            result.next();
            if(result.getString(1)!="" & result.getString(1)!=null){
                return true;
            }
            else return false;
        }catch (SQLException e){
            return false;
        }

    }
}
