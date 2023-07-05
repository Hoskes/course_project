package com.example.course_project;

import Models.Server;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException { //начальная страница
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("authorization_panel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Server.getServer();
        Main.stage = stage;
        stage.setTitle("Welcome to our system!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
    public static void loadScene(ActionEvent e, String path)  { //переключатель сцен

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene new_scene = null;
        try {
            new_scene = new Scene(fxmlLoader.load());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        stage.setScene(new_scene);
        stage.show();
    }
}