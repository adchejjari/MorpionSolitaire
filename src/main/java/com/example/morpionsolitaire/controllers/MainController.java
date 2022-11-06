package com.example.morpionsolitaire.controllers;

import com.example.morpionsolitaire.Main;
import com.example.morpionsolitaire.views.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends Application {



    public void setupStage(Stage stage) throws IOException {
        Scene scene = new Scene(new Pane(), View.SCENE_HEIGHT, View.SCENE_WIDTH);
        ViewSwitcher.setScene(scene);
        Parent parent = ViewSwitcher.switchTo(View.MainView);
        ViewSwitcher.setScene(parent);
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
