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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


public class GameBoardView {

    final static int BOARD_SIZE = 16;
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
                tile.toBack();
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
                    Move moveToBePlayed = gameBoardListener.canLink(tempI, tempJ);
                    if (moveToBePlayed.canMakeMove()) {
                        Link link = new Link(moveToBePlayed.getPositionA(), moveToBePlayed.getPositionB());
                        point.onDotClick();
                        point.setValue(++scoreValue);
                        gameBoardListener.setCell(tempI, tempJ, 1);
                        group.getChildren().add(link);
                        link.toBack();
                        group.getChildren().add(point.getValue());
                    }
                });
                group.getChildren().add(point);
            }
        }
    }

    private void initInfoButton(){
        Circle shape = new Circle();
        Image img = new Image("/image/rifat.jpg");
        ImagePattern n;
        shape.setFill(new ImagePattern(img));
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

    public interface GameBoardListener{
        void setCell(int i, int j, int val);
        int getCell(int i, int j);
        boolean isCellEmpty(int i, int j);
        void updateGrid(Grid g);
        Move canLink(int i, int j);
    }
}
