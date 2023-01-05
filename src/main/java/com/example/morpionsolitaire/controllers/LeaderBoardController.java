package com.example.morpionsolitaire.controllers;

import com.example.morpionsolitaire.db.ScoreDataAccessObject;
import com.example.morpionsolitaire.models.Grid;
import com.example.morpionsolitaire.models.Score;
import com.example.morpionsolitaire.views.LeaderBoardView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LeaderBoardController implements LeaderBoardView.LeaderBoardViewListener {

    private LeaderBoardView viewController;

    private ScoreDataAccessObject scoreDao = new ScoreDataAccessObject();


    public LeaderBoardController(LeaderBoardView _viewController) throws SQLException, IOException {
        this.viewController = _viewController;
        viewController.setLeaderBoardListener(this);
        viewController.init();
    }

    @Override
    public List<Score> getScores() throws SQLException {
        return scoreDao.getAll();
    }
}
