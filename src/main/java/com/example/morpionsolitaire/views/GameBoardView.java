package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.models.Grid;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;


public class GameBoardView {

    final static int BOARD_SIZE = 16;
    private GameBoardListener gameBoardListener;

    @FXML
    public TilePane grid;
    @FXML
    public Group group;

    private void drawGrid(){
        for (int i = 0; i <= BOARD_SIZE; i++){
            for (int j = 0; j <= BOARD_SIZE; j++){
                Tile tile = new Tile(i, j);
                group.getChildren().add(tile);
            }
        }
    }

    public void initializeCross(){
        for (int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                Point point = new Point(i, j, !gameBoardListener.isCellEmpty(i,j));
                int tempI = i; int tempJ = j;
                point.setOnMouseClicked(event -> {
                    point.onDotClick();
                    gameBoardListener.setCell(tempI, tempJ, 1);
                });
                group.getChildren().add(point);
            }
        }
    }

    @FXML
    private void initialize() {
        this.drawGrid();
    }

    public void setGameBoardListener(GameBoardListener gameListener){
        this.gameBoardListener = gameListener;
    }

    public interface GameBoardListener{
        void setCell(int i, int j, int val);
        int getCell(int i, int j);
        boolean isCellEmpty(int i, int j);
        void updateGrid(Grid g);
    }
}
