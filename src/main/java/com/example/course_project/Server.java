package com.example.course_project;

import java.sql.*;

public class Server {
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
    public static Server getServer() {
        if (server == null) {
            server = new Server();
        }
        return server;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static String returnDefaultQuery(String _query)  {
        String returnStatement = "";
        try{
        String query =_query; ///ТАК НЕЛЬЗЯ, ПЕРЕДЕЛАТЬ ЗАПРОСЫ
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(query);

        while(result.next()){
            int id = result.getInt("id");
            String name = result.getString("name");
            returnStatement += id+" "+name+" "+"\n";
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //connection.close();
        return returnStatement;
    }
    public String returnQuery(String query){
        return query;
    }
    //#####################переделать######################3
    public boolean checkLogin(String login){

        if(returnDefaultQuery(PasswordHashing.hashPassword(Querry.check_admin_login)).equals(login)){
            return true;
        }
        return false;
    }
    public static boolean enterTheSystem(String user_login,String user_password) throws SQLException {
        String pre_query = "SELECT * FROM `authorization` WHERE login=? AND password=?";
        PreparedStatement query =connection.prepareStatement(pre_query);
        query.setString(1,user_login);
        query.setString(2,PasswordHashing.hashPassword(user_password));
        ResultSet result = query.executeQuery();
        if (result.next()==true) {
            new Profile(result.getInt(1));
            return true;
        }
        return false;
    }

}
