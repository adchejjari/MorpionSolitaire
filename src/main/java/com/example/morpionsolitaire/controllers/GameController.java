package com.example.morpionsolitaire.controllers;

import com.example.morpionsolitaire.models.Grid;
import com.example.morpionsolitaire.models.Link;
import com.example.morpionsolitaire.models.Move;
import com.example.morpionsolitaire.models.Score;
import com.example.morpionsolitaire.views.GameBoardView;
import com.example.morpionsolitaire.views.Point;

import java.io.IOException;

public class GameController implements GameBoardView.GameBoardListener{

    private GameBoardView viewController;
    private Grid grid = new Grid();
    private Score score = new Score();

    public GameController(GameBoardView _viewController) throws IOException {
        this.viewController = _viewController;
        viewController.setGameBoardListener(this);
        viewController.initializeCross();
    }

    @Override
    public boolean isCellEmpty(int i, int j) {
        return this.grid.isCellEmpty(i,j);
    }

    @Override
    public void updateGrid(Grid g) {

    }

    public void undo(int i, int j){

    }

    @Override
    public Link canLink(int i, int j) {
        return this.grid.canLink(i,j);
    }

    @Override
    public void resetCell(int i, int j) {
        this.grid.resetCell(i / Point.SCALE - 1, j / Point.SCALE - 1);
    }

    @Override
    public void setCell(int i, int j, int val) {
        this.grid.setCell(i,j,val);
    }

    @Override
    public int getCell(int i, int j) {
        return grid.getCell(i,j).getValue();
    }
}
