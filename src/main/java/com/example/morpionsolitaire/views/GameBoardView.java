package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.models.Grid;
import com.example.morpionsolitaire.models.Link;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GameBoardView {
    final static int BOARD_SIZE = 16;
    public Label scoreLabel;
    public ComboBox<String> gameComboBox;
    private GameBoardListener gameBoardListener;
    private Point[][] points = new Point[BOARD_SIZE][BOARD_SIZE];
    private int scoreValue = 0;
    @FXML
    public TilePane grid;
    @FXML
    public Group group;

    int score = 0;

    private boolean startGame = false;

    public static int GAME_5D = 0;
    public static int GAME_5T = 1;


    private void drawGrid(){
        for (int i = 0; i <= BOARD_SIZE; i++){
            for (int j = 0; j <= BOARD_SIZE; j++){
                Tile tile = new Tile(i, j);
                group.getChildren().add(tile);
                tile.toBack();
            }
        }
    }

    public void initializeCross(){

        this.gameComboBox.setItems(FXCollections.observableArrayList("5D Game", "5T Game"));
        this.updateBoard();
    }

    public void updateBoard(){
        for (int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                int pointValue = this.gameBoardListener.getCell(i,j);
                Point point = new Point(i,j, pointValue);
                int finalJ = j;
                int finalI = i;
                point.setOnMouseClicked(event -> {
                    if (startGame){
                        gameBoardListener.playMove(finalI, finalJ);
                        this.update();
                    }
                });
                group.getChildren().add(point);
                points[i][j] = point;
                point.toFront();
            }
        }
    }

    public void drawLink(Link l){
        LinkWidget linkWidget = new LinkWidget(l.getFirstNode(), l.getLastNode());
        linkWidget.toBack();
        this.group.getChildren().add(linkWidget);
        updateScore(l);

    }

    private void updateScore(Link l){
        int score = this.gameBoardListener.getScoreValue();
        scoreLabel.setText(Integer.toString(score));
        int i = l.getRoot().getI();
        int j = l.getRoot().getJ();
        points[i][j].setValueText(gameBoardListener.getCell(i,j) - 2);
        group.getChildren().add(points[i][j].getTextValue());
    }

    @FXML
    private void initialize() {
        this.drawGrid();
    }

    public void setGameBoardListener(GameBoardListener gameListener){
        this.gameBoardListener = gameListener;
    }

    public void reset() {
    }

    public void undo(){
        this.gameBoardListener.undo();
        this.update();
    }

    public void update(){
        group.getChildren().removeAll(group.getChildren());
        drawGrid();
        updateBoard();
        Grid g = gameBoardListener.getUpdatedGrid();
        for (int i = 0; i<BOARD_SIZE; i++){
            for (int j = 0; j<BOARD_SIZE; j++){
                if (g.getCell(i,j).canBeSelected()){
                    this.points[i][j].setFill(Color.RED);
                } else if (g.getCell(i,j).getValue()==0) {
                    this.points[i][j].setFill(Color.TRANSPARENT);
                } else if (g.getCell(i,j).getValue()>0){
                    this.points[i][j].setFill(Color.BLACK);
                }
                this.points[i][j].toFront();
            }
        }
        for (Link l : gameBoardListener.getHistory()){
            drawLink(l);
        }
    }

    public void startGame() throws IOException {
        if (!Objects.equals(gameComboBox.getValue(), "")){
            this.startGame = true;
            int game = Objects.equals(gameComboBox.getValue(), "5D Game") ? GAME_5D : GAME_5T;
            this.gameBoardListener.startGame(game);
        }
    }

    public interface GameBoardListener{
        void setCell(int i, int j, int val);
        int getCell(int i, int j);
        Grid getUpdatedGrid();
        List<Link> canLink(int i, int j);
        void resetCell(int i, int j);

        void playMove(int i, int j);

        void undo();
        List<Link> getHistory();

        void startGame(int gameType) throws IOException;

        int getScoreValue();

    }
}
