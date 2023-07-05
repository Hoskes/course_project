package Models.TableModels;

import Models.Server;
import com.example.course_project.Config;
import com.example.course_project.PasswordHashing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Profile {
    private static Profile profile;
    private static int role_id = 0;
    private static String role = null;
    private static int id;
    private static String[] name = new String[3];
    private static int pass_id = 0;
    private static String adress;
    private static Record t;
    private Record found_user;

    public Profile(String reg_first_name, String reg_last_name, String reg_f_name, String adress, int role_id,String login,String password) throws SQLException{
        name[0]=reg_first_name;
        name[1]=reg_last_name;
        name[2]=reg_f_name;
        this.adress=adress;
        this.role_id=role_id;
        try {
            String pre_query = Config.create_user;
            PreparedStatement query = Server.getConnection().prepareStatement(pre_query);
            query.setString(1,reg_first_name);
            query.setString(2,reg_last_name);
            query.setString(3,reg_f_name);
            query.setString(4,adress);
            query.setString(5,""+role_id);
            query.executeUpdate();
            id = getGeneratedProfileId();

            t = new Record(""+id,reg_first_name,reg_last_name,reg_f_name,adress);
            setAuthorizationInfo(Profile.getId(),login,password);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    public Profile(int user_id,boolean is_your_profile) throws SQLException {
        String pre_query = Config.find_user_by_id;
        PreparedStatement query = Server.getConnection().prepareStatement(pre_query);
        query.setInt(1,user_id);
        if (is_your_profile ) {
            t = new Record(query.executeQuery());
        }else{
            found_user = new Record(query.executeQuery());
        }
        setDefaultValues();
        ////System.out.println(id+" "+name[0]+" "+name[1]+" "+name[2]+" "+adress+" "+role);
    }

    public static Profile getUser() {
        return profile;
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
        id = Integer.parseInt(t.get("id"));
        name[0] = t.get("first_name");
        name[1] = t.get("last_name");
        name[2] = t.get("f_name");
        adress = t.get("adress");
        role_id = Integer.parseInt(t.get("role_id"));

    }
    public static void setNewToDefaultValues(){
        t.replace("id",id+"");
        t.replace("first_name",name[0]);
        t.replace("last_name",name[1]);
        t.replace("last_name",name[2]);
        t.replace("last_name",adress);
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
                query.executeUpdate();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
    private int getGeneratedProfileId() throws SQLException{
        String pre_query = Config.find_new_generated_id;
        PreparedStatement query = Server.getConnection().prepareStatement(pre_query);
        query.setString(1,name[0]);
        query.setString(2,name[1]);
        query.setString(3,name[2]);
        query.setString(4,adress);
        ResultSet r =query.executeQuery();
        r.next();
        return r.getInt(1);
    }

    public Record getFound_user() {
        return found_user;
    }

    public static boolean IsUpdatePassword(int user_id, String input_old_password){
        String pre_query = Config.change_password;
        String hashed_old_password = PasswordHashing.hashPassword(input_old_password);
        PreparedStatement query = null;
        String result_string = null;
        try {
            query = Server.getConnection().prepareStatement(pre_query);
            query.setInt(1, user_id);
            query.setString(2,hashed_old_password);
            ResultSet result = query.executeQuery();
            if(result.next()){
                result_string = result.getString(1);
                //System.out.println("Password can be changed!");
                return true;
            }else{
                //System.out.println("Password cant be changed!");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void UpdatePassword(int user_id, String input_new_password){
        String pre_query = Config.update_password;
        String hashed_old_password = PasswordHashing.hashPassword(input_new_password);
        PreparedStatement query = null;
        String result_string = null;
        try {
            query = Server.getConnection().prepareStatement(pre_query);
            query.setString(1, hashed_old_password);
            query.setInt(2,user_id);
            query.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void setAuthorizationInfo(int id,String login,String password){
        String pre_query = Config.create_authorization_record;
        String hashed_old_password = PasswordHashing.hashPassword(password);
        PreparedStatement query = null;
        String result_string = null;
        try {
            query = Server.getConnection().prepareStatement(pre_query);
            query.setInt(1,id);
            query.setString(2,login);
            query.setString(3,hashed_old_password);
            query.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void sendNewDocs(String pass_ser, String pass_num){
        PreparedStatement query = null;
        PreparedStatement new_query = null;
        PreparedStatement second_query = null;
        String result_string = null;
        int docs_id =0;
        try {
            query = Server.getConnection().prepareStatement(Config.send_docs);
            query.setString(1,pass_ser);
            query.setString(2,pass_num);
            query.executeUpdate();
            new_query = Server.getConnection().prepareStatement(Config.find_docs_id);
            new_query.setString(1,pass_ser);
            new_query.setString(2,pass_num);
            ResultSet resultSet = new_query.executeQuery();
            resultSet.next();
            docs_id = resultSet.getInt(1);
            pass_id = docs_id;
            second_query = Server.getConnection().prepareStatement(Config.update_user_docs_id);
            second_query.setInt(1,docs_id);
            second_query.setInt(2,Profile.getId());
            second_query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void ChangeAuthorizeElement(boolean is_login, String val,String id){
        try {
            String t = "UPDATE authorization SET ";
            String value = "";
            PreparedStatement query = null;
            if (is_login) {
                t += "login=?";
                value = val;
            } else {
                t += "password=?";
                value = PasswordHashing.hashPassword(val);
            }
            t+="WHERE user_id =?";
            query = Server.getConnection().prepareStatement(t);
            query.setString(1, value);
            query.setString(2, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void sendDocs(String id,String ser,String num){
        if(Server.getServer().isUserDocsInSystem(Integer.parseInt(id))){
            try {
                String t = "UPDATE docs_data SET pass_ser=?,pass_num=? WHERE id=(SELECT pass_id FROM users WHERE id=?)";
                PreparedStatement query = null;
                query = Server.getConnection().prepareStatement(t);
                query.setString(1, ser);
                query.setString(2, num);
                query.setString(3, id);
                query.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            sendNewDocs(ser,num);
        }
    }
    public static void setRole(String role_id,String id_user){
        try {
            String t = "UPDATE users SET role_id=? WHERE id=?";
            PreparedStatement query = null;
            query = Server.getConnection().prepareStatement(t);
            query.setString(1, role_id);
            query.setString(2, id_user);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
