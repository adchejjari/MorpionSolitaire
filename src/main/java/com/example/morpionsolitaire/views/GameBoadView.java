package com.example.morpionsolitaire.views;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class GameBoadView {

    final static int BOARD_WIDTH = 17;
    final static int BOARD_HEIGHT = 18;

    @FXML
    public TilePane grid;
    public Group group;

    @FXML
    void initialize() {
        for (int i=0; i<BOARD_HEIGHT; i++){
            for (int j=0; j<BOARD_WIDTH; j++){
                Tile b = new Tile(i * 35, j*35);
                group.getChildren().add(b);
            }
        }
        Circle circle = new Circle(350, 350, Dot.RADIUS, Paint.valueOf("green"));
        Circle circle2 = new Circle(105, 105, Dot.RADIUS, Paint.valueOf("green"));
        group.getChildren().add(circle);
        group.getChildren().add(circle2);
    }
}
