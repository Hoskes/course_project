package Models.TableModels;

public class User {
    private int role_id;
    private int id;
    private String first_name;
    private String last_name;
    private String f_name;
    private int pass_id;

    public User(int role_id, int id, String first_name, String last_name, String f_name, int pass_id) {
        this.role_id = role_id;
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.f_name = f_name;
        this.pass_id = pass_id;
    }

    private static String adress;


    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public int getPass_id() {
        return pass_id;
    }

    public void setPass_id(int pass_id) {
        this.pass_id = pass_id;
    }

    public static String getAdress() {
        return adress;
    }

    public static void setAdress(String adress) {
        User.adress = adress;
    }
}
