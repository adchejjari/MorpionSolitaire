/**
 * This class represents the graphical interface for the game board of the Morpion Solitaire game. It
 * handles drawing the grid, placing cells on the grid, and handling user input.
 *
 * @author  Adnan Mathuschan
 * @version 1.0
 * @since   2023/01/05
 */

package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.models.Grid;
import com.example.morpionsolitaire.models.Cell;
import com.example.morpionsolitaire.models.Grid5D;
import com.example.morpionsolitaire.models.Grid5T;
import com.example.morpionsolitaire.models.Link;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
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

    private boolean startGame = false;

    public static int GAME_5D = 0;
    public static int GAME_5T = 1;


    /**
     * Draws the grid on the game board.
     */
    private void drawGrid(){
        for (int i = 0; i <= BOARD_SIZE; i++){
            for (int j = 0; j <= BOARD_SIZE; j++){
                Tile tile = new Tile(i, j);
                group.getChildren().add(tile);
                tile.toBack();
            }
        }
    }

    /**
     * Initializes the game board for the Morpion Solitaire game. This includes setting up the game
     * mode combo box and updating the board.
     */
    public void initializeCross(){

        this.gameComboBox.setItems(FXCollections.observableArrayList("5D Game", "5T Game"));
        this.updateBoard();
    }

    /**
     * Updates the game board by placing cells on the grid according to the game logic.
     */
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

    /**
     * Draws a link on the game board.
     *
     * @param l the link to draw
     */
    public void drawLink(Link l){
        LinkWidget linkWidget = new LinkWidget(l.getFirstNode(), l.getLastNode());
        linkWidget.toBack();
        this.group.getChildren().add(linkWidget);
    }

    /**
     * Updates the score display on the game board.
     *
     * @param val the new score value
     */
    private void updateScore(int val){
        scoreLabel.setText(Integer.toString(scoreValue));
    }

    /**
     * Initializes the game board by drawing the grid.
     */
    @FXML
    private void initialize() {
        this.drawGrid();
    }

    /**
     * Sets the listener for game board events.
     *
     * @param gameListener the game board listener
     */
    public void setGameBoardListener(GameBoardListener gameListener){
        this.gameBoardListener = gameListener;
    }

    /**
     * Resets the game board to its initial state.
     */
    public void reset() {
    }

    /**
     * Undoes the last move made on the game board.
     */
    public void undo(){
        this.gameBoardListener.undo();
        this.update();
    }

    /**
     * Updates the game board by redrawing it and applying the latest game state.
     */
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

    /**
     * Starts the game.
     *
     * @throws IOException if there is an error reading from the input stream
     */
    public void startGame() throws IOException {
        if (!Objects.equals(gameComboBox.getValue(), "")){
            this.startGame = true;
            int game = Objects.equals(gameComboBox.getValue(), "5D Game") ? GAME_5D : GAME_5T;
            this.gameBoardListener.startGame(game);
        }
    }

    /**
     * This interface represents a listener for game board events in the Morpion Solitaire game. It
     * provides methods for starting and resetting the game, playing a move, and undoing the last move.
     */
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





    }
}
