package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.models.Grid;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;


public class GameBoardView {

    final static int BOARD_SIZE = 17;
    private GameBoardListener gameBoardListener;

    @FXML
    public TilePane grid;
    @FXML
    public Group group;

    public GameBoardView(){
        System.out.println("constructor called");
    }

    private void initializeGrid(){
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                Tile tile = new Tile(i, j);
                group.getChildren().add(tile);
            }
        }
    }

    private void initializeCross(){
        for (int i = 1; i < BOARD_SIZE; i++){
            for(int j = 1; j < BOARD_SIZE; j++){
                Dot dot = new Dot(j, i, false);
                dot.addEventHandler(MouseEvent.MOUSE_PRESSED,
                        new EventHandler<MouseEvent>() {
                            public void handle(MouseEvent me) {
                                dot.onDotClick();
                                gameBoardListener.setCell(1,2, 1);
                            }
                        });
                group.getChildren().add(dot);
            }
        }
    }

    @FXML
    private void initialize() {
        this.initializeGrid();
        this.initializeCross();
        System.out.println(gameBoardListener);
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
