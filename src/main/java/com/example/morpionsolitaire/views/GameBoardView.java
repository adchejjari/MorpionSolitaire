package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.models.Grid;
import com.example.morpionsolitaire.models.Link;
import com.example.morpionsolitaire.models.Move;
import com.example.morpionsolitaire.models.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;

import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.List;


public class GameBoardView {
    List<Point> pointsQueue = new ArrayList<>();
    List<LinkWidget> linksQueue = new ArrayList<>();
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

                    Link moveToBePlayed = gameBoardListener.canLink(tempI, tempJ);

                    if (moveToBePlayed != null) {

                        LinkWidget linkWidget = new LinkWidget(moveToBePlayed.getFirstNode(), moveToBePlayed.getLastNode());
                        point.onDotClick();
                        point.setValue(++scoreValue);
                        scoreLabel.setText(Integer.toString(scoreValue));
                        gameBoardListener.setCell(tempI, tempJ, 1);
                        group.getChildren().add(linkWidget);
                        linkWidget.toBack();
                        group.getChildren().add(point.getValue());
                        pointsQueue.add(point);
                        linksQueue.add(linkWidget);
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
        int played = this.linksQueue.size();
        for (int i = 0; i<played; i++){
            this.undo();
        }
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
        Link canLink(int i, int j);
        void resetCell(int i, int j);
    }
}
