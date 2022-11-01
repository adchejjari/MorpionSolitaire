package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class Tile extends Pane {
    final static String FXML_FILE = "Tile.fxml";

    public Tile(){
        super();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource(FXML_FILE)));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
