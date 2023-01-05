package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.RankingItem;
import com.example.morpionsolitaire.models.Score;
import com.example.morpionsolitaire.utils.View;
import com.example.morpionsolitaire.utils.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class LeaderBoardView {
    public VBox titleBox;
    public VBox mainContainer;
    public VBox rankingListView;

    private LeaderBoardViewListener viewListener;


    public void init() throws SQLException, IOException {
        for (int i = 0; i<viewListener.getScores().size(); i++){
            FXMLLoader loader = ViewSwitcher.load(View.RankingItem);
            rankingListView.getChildren().add((Parent) loader.load());
            RankingItem item = loader.getController();
            item.setScore(viewListener.getScores().get(i));
            item.setMedal(i);
        }
    }

    public void setLeaderBoardListener(LeaderBoardViewListener listener){
        viewListener = listener;
    }


    public interface LeaderBoardViewListener{
        List<Score> getScores() throws SQLException;

    }
}
