package com.example.morpionsolitaire.views;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.Date;

import com.example.morpionsolitaire.models.Grid;
import com.example.morpionsolitaire.models.Link;
import com.example.morpionsolitaire.models.Score;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;

import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Handler;

public class GameBoardView {
    final static int BOARD_SIZE = 16;
    public Label scoreLabel;
    public ComboBox<String> gameComboBox;
    public Label highScoreLabel;
    public Button playButton;
    public ToggleButton humanMode;
    public ToggleButton randomMode;
    public ToggleButton nmcsMode;
    private GameBoardListener gameBoardListener;
    private Point[][] points = new Point[BOARD_SIZE][BOARD_SIZE];
    private int scoreValue = 0;
    @FXML
    public TilePane grid;
    private ToggleGroup playingMode = new ToggleGroup();
    @FXML
    public Group group;
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

    public void initializeCross() throws SQLException {
        this.setHighScore();
        this.humanMode.setToggleGroup(playingMode);
        this.randomMode.setToggleGroup(playingMode);
        this.nmcsMode.setToggleGroup(playingMode);
        humanMode.setSelected(true);
        this.gameComboBox.setItems(FXCollections.observableArrayList("5D Game", "5T Game"));
        this.updateBoard();

    }

    public void updateBoard(){
        for (int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                int pointValue = this.gameBoardListener.getCell(i,j);
                Point point = new Point(i,j, pointValue);
                point.toFront();
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

    private void setHighScore() throws SQLException {
        int highScore = this.gameBoardListener.getHighScore();
        highScoreLabel.setText(String.valueOf(highScore));

    }

    @FXML
    private void initialize() {
        this.drawGrid();
    }

    public void setGameBoardListener(GameBoardListener gameListener){
        this.gameBoardListener = gameListener;
    }

    public void reset() throws IOException {
        this.startGame = false;
        playButton.setText("Start");
        this.gameBoardListener.resetGrid();
        this.scoreValue = 0;
        this.scoreLabel.setText(String.valueOf(scoreValue));
        this.update();
    }

    public void undo(){
        this.gameBoardListener.undo();
        scoreValue--;
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

    public void startGame() throws IOException, SQLException, InterruptedException {
        if (Objects.equals(gameComboBox.getValue(),null)){
            System.out.println("Combobox Free!!");
            return;

        } if(!startGame && humanMode.isSelected()){
            this.startGame = true;
            int game = Objects.equals(gameComboBox.getValue(), "5D Game") ? GAME_5D : GAME_5T;
            this.gameBoardListener.startGame(game);
            playButton.setText("Restart");
        } else if (!startGame && randomMode.isSelected()) {
            this.startGame = true;
            int game = Objects.equals(gameComboBox.getValue(), "5D Game") ? GAME_5D : GAME_5T;
            this.gameBoardListener.startGame(game);
            this.randomScenario();
        } else {
            this.reset();
        }
    }

    public void hint() {
        if(humanMode.isSelected()){
            List<Link> possibleLinks = gameBoardListener.getAllPossibleLinks();
            if (possibleLinks.size()>0){
                Random rand = new Random();
                int int_random = rand.nextInt(possibleLinks.size());
                Link link = possibleLinks.get(int_random);
                points[link.getRoot().getI()][link.getRoot().getJ()].setFill(Color.YELLOW);
            }
        }
    }

    public void checkGameOver() throws SQLException, IOException {
        ScoreDialogBox dialog = new ScoreDialogBox(this.scoreValue);
        dialog.showAndWait();
        this.gameBoardListener.insertScore(new Score(dialog.getPlayerName(),this.scoreValue));
        this.reset();
    }

    public void randomScenario() throws InterruptedException, SQLException, IOException {
        if (randomMode.isSelected() && startGame) {
            List<Link> moves = gameBoardListener.getAllPossibleLinks();
            int i = 0;
            while (moves.size() > 0){
                Random rand = new Random();
                int int_random = rand.nextInt(moves.size());

                Link move = moves.get(int_random);
                gameBoardListener.playRandom(move.getRoot().getI(), move.getRoot().getJ());
                this.scoreValue = gameBoardListener.getScoreValue();
                this.update();

                i++;
                moves = gameBoardListener.getAllPossibleLinks();
            }
            this.checkGameOver();
        }
    }


    private void delay(){

    }

    public interface GameBoardListener{
        void setCell(int i, int j, int val);
        int getCell(int i, int j);
        Grid getUpdatedGrid();
        List<Link> canLink(int i, int j);
        void resetCell(int i, int j);
        void playMove(int i, int j);
        void playRandom(int i, int j);
        void undo();
        List<Link> getHistory();
        void startGame(int gameType) throws IOException;
        int getScoreValue();
        int getHighScore() throws SQLException;
        List<Link> getAllPossibleLinks();

        List<Link> getRandomSenario();

        void resetGrid() throws IOException;
        void insertScore(Score s) throws SQLException;
    }
}
