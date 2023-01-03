package com.example.morpionsolitaire.controllers;

import com.example.morpionsolitaire.models.Cell;
import com.example.morpionsolitaire.models.Grid;
import com.example.morpionsolitaire.models.Link;
import com.example.morpionsolitaire.models.Score;
import com.example.morpionsolitaire.views.GameBoardView;

import java.io.IOException;
import java.util.List;

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
    public Grid getUpdatedGrid() {
        return grid;
    }
     @Override
    public void undo(){
        this.grid.undoLastMove();

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
        return grid.getCell(i,j).getValue();
    }
    @Override
    public Cell getCellParent(int i, int j){
        return grid.getCell(i,j).getParent();
    }

    @Override
    public Link getCellLink(int i, int j){
        return grid.getCell(i,j).getParent().getLinkedNodes();
    }
}
