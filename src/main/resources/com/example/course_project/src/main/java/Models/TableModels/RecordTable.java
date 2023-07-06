package Models.TableModels;

import java.sql.ResultSet;
import java.util.ArrayList;

public class RecordTable extends ArrayList<Record> {
    public RecordTable(ResultSet resultSet){
        try {
            while (resultSet.next()){
                Record record = new Record();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    record.addRecord(resultSet.getMetaData().getColumnName(i),resultSet.getString(i));
                }
                add(record);
            }
        }catch (java.sql.SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }
    public void view(){
        for (int i = 0; i < size(); i++) {
            get(i).view();
        }
    }
    public String getValue(int row,String key){
        return get(row).find(key);
    }
}
