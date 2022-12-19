package com.example.morpionsolitaire.utils;

import com.example.morpionsolitaire.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.Objects;

public class ViewSwitcher {

    private static Scene scene;

    public static FXMLLoader loader;

    public static void setScene(Scene scene){
        ViewSwitcher.scene = scene;
    }

    public static FXMLLoader load(View view){
        FXMLLoader loader = new FXMLLoader(
                Objects.requireNonNull(Main.class.getResource(view.getFileName()))
        );
        return loader;
    }


    public static void setScene(Parent r){
        scene.setRoot(r);
    }
}
