package Models;

import Models.TableModels.Profile;
import com.example.course_project.Config;
import com.example.course_project.PasswordHashing;

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
            //System.out.println(e);
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
            new Profile(result.getInt(1));
            return true;
        }
        return false;
    }

}
