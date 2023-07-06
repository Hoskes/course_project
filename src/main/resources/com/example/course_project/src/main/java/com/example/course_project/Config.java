package com.example.course_project;

import java.security.SecureRandom;

public class Config {
    public static final String db_url = "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2260_kursach";
    public static final String db_user = "std_2260_kursach";
    public static final String db_pass = "9999Send";
    public static final String regex_name = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
    public static final String regex_login = "^[a-zA-Z0-9_-]{3,16}$";
    public static final String find_user_by_id = "SELECT * FROM `users` WHERE id=?";
    public static final String find_role_by_user_id = "SELECT name FROM roles WHERE id =(SELECT role_id FROM users WHERE id=?)";
    public static final String adress = "[А-я|.| |]+[0-9]*";
    public static final String find_id_by_state ="SELECT id FROM orders_status WHERE name=?";
    public static final String update_orders_state = "UPDATE orders SET orders.status_id=? WHERE id =?";
    public static final String find_user_orders = "SELECT orders.id, user_id, bike_id, orders_status.name, points.adress, models.model_name as user_bike_model,points.id FROM orders JOIN orders_status ON orders_status.id = status_id JOIN points ON point_created = points.id JOIN bikes ON bike_id =bikes.id JOIN models ON bikes.model_id = models.id  WHERE user_id=?";
    public static final String find_avalible_models = " SELECT bikes.id,model_name,transmission_count,bike_types.name as type,models.id FROM bikes JOIN bike_status ON cur_state = bike_status.id JOIN models ON model_id = models.id JOIN bike_types ON type = bike_types.id WHERE bike_status.name =?";
    public static final String all_points = "SELECT adress FROM points";
    public static final String all_roles = "SELECT name FROM points";
    public static final String find_user_docs = "SELECT pass_id FROM users WHERE id=?";
    public static final String update_user_docs_id = "UPDATE `users` SET `pass_id`=? WHERE id=?";
    public static final String all_order_states = "SELECT * FROM orders_status";
    public static final String find_point_by_adress = "SELECT id FROM points WHERE adress=?";
    public static final String find_new_generated_id = "SELECT id FROM users WHERE first_name=? AND last_name =? AND f_name=? AND adress =? ";
    public static final String create_user = "INSERT INTO `users`( `first_name`, `last_name`, `f_name`, `adress`, `role_id`) VALUES (?,?,?,?,?)";
    public static final String change_password = "SELECT user_id FROM authorization WHERE user_id =? AND password=?";
    public static final String update_password = "UPDATE `authorization` SET `password`=? WHERE user_id=?";
    public static final String create_authorization_record = "INSERT INTO `authorization`(`user_id`, `login`, `password`) VALUES (?,?,?)";
    public static final String find_docs_id = "SELECT id FROM docs_data WHERE pass_ser=? AND pass_num=?";
    public static final String send_docs = "INSERT INTO `docs_data`(`pass_ser`, `pass_num`) VALUES (?,?)";

}
