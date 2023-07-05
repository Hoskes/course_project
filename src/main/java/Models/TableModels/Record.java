package Models.TableModels;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class Record extends LinkedHashMap<String,String>{ //обернуть ответ от сервера
    public Record(){};
    public Record(String id,String first_name,String last_name,String f_name,String adress){
        put("id",id);
        put("first_name",first_name);
        put("last_name",last_name);
        put("f_name",f_name);
        put("adress",adress);
    }
    public Record(ResultSet resultSet)throws SQLException {
        try {
            while (resultSet.next()){
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    this.addRecord(resultSet.getMetaData().getColumnName(i),resultSet.getString(i));
                }
            }
        }catch (java.sql.SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }

    public void addRecord(String column_name, String column_value){
        put(column_name,column_value);
    }
    public void view(){
            System.out.println(entrySet());
    }
    public String find(String str){
        return this.get(str);
    }
}
