package com.example.course_project;

import java.security.SecureRandom;

public class Config {
    public static final String db_url = "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2260_kursach";
    public static final String db_user = "std_2260_kursach";
    public static final String db_pass = "9999Send";
    public static final String find_user_by_id = "SELECT * FROM `users` WHERE id=?";
    public static final String find_role_by_user_id = "SELECT name FROM roles WHERE id =(SELECT role_id FROM users WHERE id=?)";
}
