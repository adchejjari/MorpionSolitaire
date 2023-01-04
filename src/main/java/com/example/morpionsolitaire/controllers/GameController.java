package com.example.morpionsolitaire.controllers;
import com.example.morpionsolitaire.models.Grid;
import com.example.morpionsolitaire.models.Grid5D;
import com.example.morpionsolitaire.models.Grid5T;
import com.example.morpionsolitaire.models.Link;
import com.example.morpionsolitaire.models.Score;
import com.example.morpionsolitaire.views.GameBoardView;

import java.io.IOException;
import java.util.List;

public class GameController implements GameBoardView.GameBoardListener{

    private GameBoardView viewController;
    private Grid grid;
    private Score score = new Score();

    public GameController(GameBoardView _viewController) throws IOException {
        this.viewController = _viewController;
        viewController.setGameBoardListener(this);
        Grid.load();
        viewController.initializeCross();
    }

    @Override
    public Grid getUpdatedGrid() {
        return grid;
    }
     @Override
    public void undo(){
        this.grid.undoLastMove();

    }

    @Override
    public List<Link> getHistory() {
        return grid.getMovesHistory();
    }

    @Override
    public void startGame(int gameType) throws IOException {
        if (gameType == GameBoardView.GAME_5D){
            grid = new Grid5D();
        }else{
            grid = new Grid5T();
        }
    }

    @Override
    public int getScoreValue() {
        return this.grid.getScoreValue();
    }

    @Override
    public List<Link> canLink(int i, int j) {
        return this.grid.canLink(i,j);
    }

    @Override
    public void resetCell(int i, int j) {
        this.grid.resetCell(i , j );
    }

    @Override
    public void playMove(int i, int j) {
        this.grid.playMove(i,j);

    }

    @Override
    public void setCell(int i, int j, int val) {
        this.grid.setCell(i,j,val);
    }

    @Override
    public int getCell(int i, int j) {
        System.out.println("score is : " + Grid.getCell(i,j).getValue());
        return Grid.getCell(i,j).getValue();
    }

}
