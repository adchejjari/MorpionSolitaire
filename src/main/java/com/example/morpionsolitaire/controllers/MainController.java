package com.example.morpionsolitaire.controllers;

import com.example.morpionsolitaire.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends Application {
    private static final String FXML_FILE = "MainPage.fxml";
    private static final String WINDOW_ICON = "logo.png";



    public void setupStage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FXML_FILE));
        Scene scene = new Scene(fxmlLoader.load(), 580, 600);
        stage.setTitle("Morpion Solitaire");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.setupStage(stage);

    }

    public static void main(String[] args) {
        launch();
    }
}
