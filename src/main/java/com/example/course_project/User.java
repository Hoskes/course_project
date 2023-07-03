package com.example.course_project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private static User user;
    private static int role_id = 0;
    private static String role = null;
    private static int id;
    private static String[] name = new String[3];
    private static int pass_id = 0;
    private static String adress;

    public User(int user_id) throws SQLException {
        String pre_query = Config.find_user_by_id;
        PreparedStatement query = Server.getConnection().prepareStatement(pre_query);
        query.setInt(1,user_id);
        ResultSet result = query.executeQuery();
        result.next();
        id = result.getInt(1);
        name[0] = result.getString(2);
        name[1] = result.getString(3);
        name[2] = result.getString(4);
        adress = result.getString(5);
        role_id = result.getInt(6);
        role_id = result.getInt(7);
        //System.out.println(id+" "+name[0]+" "+name[1]+" "+name[2]+" "+adress+" "+role);
    }

    public static User getUser() {
        return user;
    }

    public static int getRole_id() {
        return role_id;
    }

    public static int getId() {
        return id;
    }

    public static String[] getName() {
        return name;
    }

    public static String getAdress() {
        return adress;
    }
    public static String getCurrentRole() {
        if(role==null) {
            String pre_query = Config.find_role_by_user_id;
            PreparedStatement query = null;
            String result_string = null;
            try {
                query = Server.getConnection().prepareStatement(pre_query);
            query.setInt(1, id);
            ResultSet result = query.executeQuery();
            result.next();
            result_string = result.getString(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return result_string;
        }else
            return role;
    }
}
