package com.example.morpionsolitaire.views;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class GameBoadView {

    final static int BOARD_WIDTH = 17;
    final static int BOARD_HEIGHT = 17;

    @FXML
    public GridPane grid;

    @FXML
    void initialize() {
        for (int i=0; i<BOARD_HEIGHT; i++){
            for (int j=0; j<BOARD_WIDTH; j++){
                Tile b = new Tile();
                grid.add(b, i, j);
            }
        }
    }
}
