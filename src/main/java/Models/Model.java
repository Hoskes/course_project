package Models;

public class Model {

    private int id;
    private String model_name;
    private int transmission_count;
    private String type;

    public Model(int id, String model_name, int transmission_count, String type) {
        this.id = id;
        this.model_name = model_name;
        this.transmission_count = transmission_count;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public int getTransmission_count() {
        return transmission_count;
    }

    public void setTransmission_count(int transmission_count) {
        this.transmission_count = transmission_count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
