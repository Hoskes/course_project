package com.example.course_project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
    private static User user;
    private static int role_id = 0;
    private static String role = null;
    private static int id;
    private static String[] name = new String[3];
    private static int pass_id = 0;
    private static String adress;
    private static RecordTable t;

    public User(int user_id) throws SQLException {
        String pre_query = Config.find_user_by_id;
        PreparedStatement query = Server.getConnection().prepareStatement(pre_query);
        query.setInt(1,user_id);
        t = new RecordTable(query.executeQuery());
        setDefaultValues();
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
    public static void setAdress(String s){
        adress = s;
    }
    public static String getCurrentRole() {
        if(role==null) {
            String pre_query = Config.find_role_by_user_id;
            PreparedStatement query = null;
            String result_string = null;
            try {
                query = Server.getConnection().prepareStatement(pre_query);
                query.setInt(1, id);
                RecordTable t = new RecordTable(query.executeQuery());
                t.view();
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
    public void UpdateUserValue(String key,String value){
        t.get(0).replace(key, value);
    }
    public static void setDefaultValues(){
        id = Integer.parseInt(t.getValue(0,"id"));
        name[0] = t.getValue(0,"first_name");
        name[1] = t.getValue(0,"last_name");
        name[2] = t.getValue(0,"f_name");
        adress = t.getValue(0,"adress");
        role_id = Integer.parseInt(t.getValue(0,"role_id"));;
    }
    public static void setNewToDefaultValues(){
        t.get(0).replace("id",id+"");
        t.get(0).replace("first_name",name[0]);
        t.get(0).replace("last_name",name[1]);
        t.get(0).replace("last_name",name[2]);
        t.get(0).replace("last_name",adress);
    }
    public static boolean updateUserInfo(String column, ArrayList<String> values){
        if(values.size()>0 & column.split(" ").length==values.size()) {
            String pre_query = "UPDATE users SET " + column + " WHERE id=" + id;
            PreparedStatement query = null;
            try {
                query = Server.getConnection().prepareStatement(pre_query);
                for (int i = 0; i < values.size(); i++) {
                    query.setString(i+1, values.get(i));
                }
                System.out.println(pre_query);
                query.executeUpdate();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
