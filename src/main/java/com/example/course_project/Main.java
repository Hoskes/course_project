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
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("authorization_panel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Server.getServer();
        Main.stage = stage;
        stage.setTitle("Hello");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
    public static Stage getStage(){
        return Main.stage;
    }
    public static void loadScene(ActionEvent e, String path)  {
//        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//        Parent root = FXMLLoader.load(Main.class.getResource(path));
//        Scene scene = new Scene(root);
//        scene.setRoot(root);
//        stage.setScene(scene);
//        stage.show();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene new_scene = null;
        try {
            new_scene = new Scene(fxmlLoader.load());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
//        new_scene.setRoot(fxmlLoader.load());
        stage.setScene(new_scene);
        stage.show();
    }
}