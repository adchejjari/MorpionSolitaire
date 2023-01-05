package com.example.morpionsolitaire;

import com.example.morpionsolitaire.models.Medal;
import com.example.morpionsolitaire.models.Score;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class RankingItem {
    public Label playerName;
    public Label dateLabel;
    public Label scoreValue;
    public ImageView medal;

    public Image goldMedal;
    public Image silverMedal;
    public Image bronzeMedal;

    public void setScore(Score score){
        this.setPlayerName(score.getPlayerName());
        this.setScoreValue(String.valueOf(score.getValue()));
        this.setDate(score.getDate());

    }
    private void setPlayerName(String playerName) {
        this.playerName.setText(playerName);
    }

    private void setScoreValue(String score){
        this.scoreValue.setText(score);
    }

    private void setDate(String date){
        this.dateLabel.setText(date);
    }

    public void setMedal(int rank){
        if (rank == Medal.GOLD_MEDAL.getValue()){
            medal.setImage(goldMedal);
        } else if (rank == Medal.SILVER_MEDAL.getValue()) {
            medal.setImage(silverMedal);
        } else if (rank == Medal.BRONZE_MEDAL.getValue()) {
            medal.setImage(bronzeMedal);
        }
    }
}
