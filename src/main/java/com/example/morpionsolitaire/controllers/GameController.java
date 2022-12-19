package com.example.morpionsolitaire.controllers;

import com.example.morpionsolitaire.models.Grid;
import com.example.morpionsolitaire.views.GameBoardView;

import java.io.IOException;

public class GameController implements GameBoardView.GameBoardListener{

    private GameBoardView viewController = null;
    private Grid grid;

    public GameController(GameBoardView ctrl) throws IOException {
        grid = new Grid();

        viewController = ctrl;
        viewController.setGameBoardListener(this);
    }

    @Override
    public boolean isCellEmpty(int i, int j) {
        return false;
    }

    @Override
    public void updateGrid(Grid g) {

    }

    @Override
    public void setCell(int i, int j, int val) {
        this.grid.setCell(i,j,val);

    }

    @Override
    public int getCell(int i, int j) {
        return grid.getCellValue(i,j);
    }
}
