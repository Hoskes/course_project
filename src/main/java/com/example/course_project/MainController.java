package com.example.course_project;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHellButtonClick(ActionEvent e) {
        welcomeText.setText("#SCC");
        try {
            Main.loadScene(e,"authorization_panel.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        };

    }
}