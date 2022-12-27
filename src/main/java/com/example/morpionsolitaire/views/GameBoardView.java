package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.models.Grid;
import com.example.morpionsolitaire.models.Move;
import com.example.morpionsolitaire.models.Position;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;


public class GameBoardView {
    List<Point> pointsQueue = new ArrayList<>();
    List<Link> linksQueue = new ArrayList<>();
    final static int BOARD_SIZE = 16;
    public Label scoreLabel;
    private GameBoardListener gameBoardListener;

    private int scoreValue = 0;

    @FXML
    public TilePane grid;
    @FXML
    public Group group;

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
        for (int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                Point point = new Point(i, j, !gameBoardListener.isCellEmpty(i,j));
                int tempI = i; int tempJ = j;
                point.setOnMouseClicked(event -> {

                    Move moveToBePlayed = gameBoardListener.canLink(tempI, tempJ);
                    System.out.println("hellooo!! can play " + moveToBePlayed.canMakeMove());
                    if (moveToBePlayed.canMakeMove()) {

                        Link link = new Link(moveToBePlayed.getPositionA(), moveToBePlayed.getPositionB());
                        point.onDotClick();
                        point.setValue(++scoreValue);
                        scoreLabel.setText(Integer.toString(scoreValue));
                        gameBoardListener.setCell(tempI, tempJ, 1);
                        group.getChildren().add(link);
                        link.toBack();
                        group.getChildren().add(point.getValue());
                        pointsQueue.add(point);
                        linksQueue.add(link);
                    }
                });
                group.getChildren().add(point);
            }
        }
    }


    private void linkTwoPoints(Point p, Position a, Position b){

    }


    @FXML
    private void initialize() {
        this.drawGrid();
    }

    public void setGameBoardListener(GameBoardListener gameListener){
        this.gameBoardListener = gameListener;
    }

    public void displayPopup(ActionEvent actionEvent) {

    }

    public void reset() {
        scoreValue = 0;
        for(int i = 0; i<this.linksQueue.size();i++){
            this.group.getChildren().removeAll(this.linksQueue.get(i));
            this.pointsQueue.get(i).setVisibility();
            this.group.getChildren().removeAll();
            this.group.getChildren().removeAll(this.pointsQueue.get(i).getValue());
        }
        this.linksQueue.clear();
        this.pointsQueue.clear();
        this.drawGrid();
        this.scoreLabel.setText(Integer.toString(0));
    }

    public void undo(){
        int lastIndex = this.linksQueue.size() - 1;
        if(lastIndex>=0){
            this.group.getChildren().removeAll(this.linksQueue.get(lastIndex));
            Point p =  this.pointsQueue.get(lastIndex);
            p.hide();
            this.group.getChildren().removeAll(this.pointsQueue.get(lastIndex).getValue());
            this.linksQueue.remove(lastIndex);
            this.pointsQueue.remove(lastIndex);
            this.scoreLabel.setText(Integer.toString(--scoreValue));
            this.gameBoardListener.resetCell((int)p.getCoordinateY(), (int)p.getCoordinateX());
        }
    }

    public interface GameBoardListener{
        void setCell(int i, int j, int val);
        int getCell(int i, int j);
        boolean isCellEmpty(int i, int j);
        void updateGrid(Grid g);
        Move canLink(int i, int j);
        void resetCell(int i, int j);
    }
}
