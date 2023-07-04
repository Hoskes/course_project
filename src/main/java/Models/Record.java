package Models;

import java.util.LinkedHashMap;

public class Record extends LinkedHashMap<String,String>{ //обернуть ответ от сервера

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
