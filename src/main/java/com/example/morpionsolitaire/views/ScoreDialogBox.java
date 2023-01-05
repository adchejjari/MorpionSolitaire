package com.example.morpionsolitaire.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class ScoreDialogBox extends Dialog{

    String player;
    TextField playerName;

    ButtonType submitButton;

    public int score;

    public ScoreDialogBox(int score){
        super();
        this.setTitle("Game Over");

        submitButton = new ButtonType("Save your score", ButtonBar.ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(submitButton, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        Label lbl = new Label("Your final score is " + String.valueOf(score));

        playerName = new TextField();
        playerName.setPromptText("Your name : ");
        gridPane.add(lbl, 0,0);
        gridPane.add(playerName, 1, 1);


        this.getDialogPane().setContent(gridPane);
    }

    public String getPlayerName(){
        return playerName.getText();
    }



}
