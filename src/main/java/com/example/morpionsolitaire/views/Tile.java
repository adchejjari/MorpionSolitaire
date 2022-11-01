package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Objects;

public class Tile extends Rectangle {
    final static String FXML_FILE = "Tile.fxml";

    public Tile(int i, int j){
        super(35,35,i,j);

        /*FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource(FXML_FILE)));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.BLUEVIOLET);
        this.setStrokeWidth(2);
    }
}
