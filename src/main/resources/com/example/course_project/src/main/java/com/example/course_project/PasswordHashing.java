package com.example.course_project;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {
    public static String hashPassword(String password) {
        try {
            // Создаем объект MessageDigest с использованием MD5 алгоритма
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Переводим пароль в массив байтов
            byte[] passwordBytes = password.getBytes();

            // Вычисляем хеш пароля
            byte[] hashedBytes = md.digest(passwordBytes);

            // Преобразуем массив байтов в строку в шестнадцатеричном формате
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String password = "admin";
        String hashedPassword = hashPassword(password);
        //System.out.println(hashedPassword);
    }
}
