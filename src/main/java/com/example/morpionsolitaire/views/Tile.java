/**
 * Represents a tile in the game.
 *
 * @author Adnan Mathuschan
 * @version 1.0
 * @since 2023-01-05
 */

package com.example.morpionsolitaire.views;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    final static int SIZE = 35;
    final static int STROKE_WIDTH = 2;

    /**
     * Creates a new tile at the given position.
     * @param i The x position of the tile.
     * @param j The y position of the tile.
     */
    public Tile(int i, int j) {
        super(i * SIZE,j * SIZE, SIZE, SIZE);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.BLUEVIOLET);
        this.setStrokeWidth(STROKE_WIDTH);
    }
}
