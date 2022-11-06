package com.example.morpionsolitaire.controllers;

import com.example.morpionsolitaire.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class ViewSwitcher {

    private static Scene scene;

    public static void setScene(Scene scene){
        ViewSwitcher.scene = scene;
    }

    public static Parent switchTo(View view){
        Parent root = null;
        try{
            root = FXMLLoader.load(
                    Objects.requireNonNull(Main.class.getResource(view.getFileName()))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    public static void setScene(Parent r){
        scene.setRoot(r);
    }
}
