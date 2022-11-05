package com.example.morpionsolitaire.views;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    final static int SIZE = 35;
    final static int STROKE_WIDTH = 2;

    public Tile(int i, int j) {
        super(i * SIZE,j * SIZE, SIZE, SIZE);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.BLUEVIOLET);
        this.setStrokeWidth(STROKE_WIDTH);
    }
}
