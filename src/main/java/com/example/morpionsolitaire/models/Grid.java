package com.example.morpionsolitaire.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class Grid {

    final static int WIDTH = 16;
    final static int HEIGHT = 16;
    final private static String DEFAULT_GRID = "/src/main/java/com/example/morpionsolitaire/grids/default.grid";
    static protected Cell[][] matrix = new Cell[WIDTH][WIDTH];

    protected List<Link> possibleMoves;
    private boolean selectionInProcess = false;
    protected List<Link> movesHistory = new ArrayList<>();

    protected int scoreValue = 1;

    public static Cell getCell(int i, int j){
        return matrix[i][j];
    }

    public static void load() throws IOException {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString() + DEFAULT_GRID;
        for (int i = 0 ; i< HEIGHT; i++) {
            String read = Files.readAllLines(Paths.get(s)).get(i);
            String[] line = read.split("");
            for (int j = 0; j < WIDTH; j++){
                matrix[i][j] = new Cell(i, j, Integer.parseInt(line[j]));
            }
        }
    }

    public void setCell(int line, int column, int value){
        matrix[line][column].setValue(value);
    }

    public void undoLastMove(){
        int index = this.movesHistory.size() - 1;
        if (index>= 0) {
            Link linkToRemove = this.movesHistory.get(index);
            for (Cell c : linkToRemove.getNodes()) {
                c.unlink(linkToRemove.getLinkType());
            }
            Cell rootCell = linkToRemove.getRoot();
            rootCell.setMainLink(LinkType.NONE);
            this.matrix[rootCell.getI()][rootCell.getJ()].setValue(0);
            rootCell.setLink(null);
            movesHistory.remove(index);
        }
    }

    public List<Link> getMovesHistory(){
        return movesHistory;
    }



    public void resetCell(int i, int j){
        LinkType mainType = this.matrix[i][j].getMainLink();
        Cell[] link = this.matrix[i][j].getLinkedNodes().getNodes();
        for(Cell c : link){
            this.matrix[c.getI()][c.getJ()].unlink(mainType);
        }
        this.matrix[i][j].setMainLink(LinkType.NONE);
        this.matrix[i][j].unlink(mainType);
        this.matrix[i][j].setValue(0);
        //debug();
    }

    public void playMove(int i, int j) {
        List<Link> possiblities = canLink(i,j);
        if (possiblities.size() == 1 && this.matrix[i][j].getValue()==0){
            Link possibleLink = possiblities.get(0);
            setSingleLink(possibleLink);
        }
        else if(possiblities.size()>1){
            this.possibleMoves = possiblities;
            setSelectionCells();
        }
        if (this.matrix[i][j].canBeSelected()) {
            Link moveToPlay = getLinkFromSelectedcell(this.matrix[i][j]);
            removeSelection();
            setSingleLink(moveToPlay);
            this.possibleMoves = null;
        }
    }

    public void removeSelection(){
        for(int i = 0; i < HEIGHT; i++){
            for (int j = 0; j<WIDTH;j++){
                this.matrix[i][j].setCanBeSelected(false);
            }
        }
    }

    public void setSingleLink(Link l){
        int i = l.getRoot().getI();
        int j = l.getRoot().getJ();
        for (Cell c: l.getNodes()){
            c.link(l.getLinkType());
        }
        this.matrix[i][j].setLink(l);
        this.matrix[i][j].setValue(++scoreValue  + 1);
        this.movesHistory.add(l);
    }

    public Link getLinkFromSelectedcell(Cell c){
        for(Link link: this.possibleMoves){
            if (c==link.getFirstNode() || c==link.getLastNode()){
                return link;
            }
        }
        return null;

    }

    public void setSelectionCells(){
        for(Link move: possibleMoves){
            if(move.getFirstNode() == move.getRoot()){
                move.getLastNode().setCanBeSelected(true);
            }else{
                move.getFirstNode().setCanBeSelected(true);
            }
        }
    }

    public int getScoreValue(){
        return scoreValue - 1;
    }

    public abstract List<Link> canLink(int line, int column);

    protected abstract List<Link> horizontalJoin(int line, int column);
    protected abstract List<Link> canJoinVertically(int line, int column);
    protected abstract List<Link> canJoinSecondDiagonal(int line, int column);
    protected abstract List<Link> canJoinFirstDiagonal(int line, int column);

    public abstract List<Link> getAllPossibleMoves();
}
