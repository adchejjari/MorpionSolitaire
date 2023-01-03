package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.models.Cell;
import com.example.morpionsolitaire.models.Grid;
import com.example.morpionsolitaire.models.Link;
import javafx.fxml.FXML;
import javafx.scene.Group;

import javafx.scene.control.Label;

import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameBoardView {
    List<Point> pointsQueue = new ArrayList<>();
    List<LinkWidget> linksQueue = new ArrayList<>();
    List<Link> linkList = new ArrayList<>();
    final static int BOARD_SIZE = 16;
    public Label scoreLabel;
    private GameBoardListener gameBoardListener;
    private Point[][] points = new Point[BOARD_SIZE][BOARD_SIZE];
    private int scoreValue = 0;
    @FXML
    public TilePane grid;
    @FXML
    public Group group;

    List<Link> possibleMoves;

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
                    gameBoardListener.playMove(finalI, finalJ);
                    this.update();
                });
                group.getChildren().add(point);
                points[i][j] = point;
            }
        }
    }



    public void drawLink(Link l){
        LinkWidget linkWidget = new LinkWidget(l.getFirstNode(), l.getLastNode());
        this.group.getChildren().add(linkWidget);
    }


    private void updateScore(int val){
        scoreLabel.setText(Integer.toString(scoreValue));
    }

    @FXML
    private void initialize() {
        this.drawGrid();
    }

    public void setGameBoardListener(GameBoardListener gameListener){
        this.gameBoardListener = gameListener;
    }

    public void reset() {
        int played = this.linksQueue.size();
        for (int i = 0; i<played; i++){
            this.undo();
        }
    }

    public void undo(){

    }

    public void update(){
        Grid g = gameBoardListener.getUpdatedGrid();
        for (int i = 0; i<BOARD_SIZE; i++){
            for (int j = 0; j<BOARD_SIZE; j++){
                if (g.getCell(i,j).isLinking()){
                    this.drawLink(g.getCell(i,j).getLinkedNodes());
                }
                if (g.getCell(i,j).canBeSelected()){
                    this.points[i][j].setFill(Color.RED);
                } else if (g.getCell(i,j).getValue()==0) {
                    this.points[i][j].setFill(Color.TRANSPARENT);
                }else if (g.getCell(i,j).getValue()>0){
                    this.points[i][j].setFill(Color.BLACK);
                }
            }

        }

    }

    public interface GameBoardListener{
        void setCell(int i, int j, int val);
        int getCell(int i, int j);
        Grid getUpdatedGrid();
        List<Link> canLink(int i, int j);
        void resetCell(int i, int j);

        void playMove(int i, int j);



        Cell getCellParent(int i, int j);
        Link getCellLink(int i, int j);



    }
}
