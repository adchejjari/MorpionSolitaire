/**
 * Abstract class representing a grid of {@link Cell} objects in a game. Contains a two-dimensional array
 * of {@link Cell} objects and various methods for manipulating the cells in the grid.
 *
 * @author  Adnan Mathuschan
 * @version 1.0
 * @since   2023/01/05
 */

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

    /**
     * Returns the {@link Cell} object at the specified position in the grid.
     *
     * @param i The row index of the cell.
     * @param j The column index of the cell.
     * @return The {@link Cell} object at the specified position in the grid.
     */
    public static Cell getCell(int i, int j){
        return matrix[i][j];
    }

    /**
     * Loads the default grid from a file and stores it in the {@code matrix} field.
     *
     * @throws IOException If there is an error reading from the file.
     */
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

    /**
     * Sets the value of the {@link Cell} at the specified position in the grid.
     *
     * @param line The row index of the cell.
     * @param column The column index of the cell.
     * @param value The value to set for the cell.
     */
    public void setCell(int line, int column, int value){
        matrix[line][column].setValue(value);
    }


    /**
     * Undoes the last move made in the game by removing the last {@link Link} from the {@code movesHistory} list
     * and unsetting the main link and links of the {@link Cell} objects involved in the move.
     */
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

    /**
     * Returns the list of moves made in the game.
     *
     * @return The list of moves made in the game.
     */
    public List<Link> getMovesHistory(){
        return movesHistory;
    }

    /**
     * Resets the specified {@link Cell} in the grid to its initial state by unlinking it from any other cells and setting
     * its value to 0.
     *
     * @param i The row index of the cell.
     * @param j The column index of the cell.
     */
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

    /**
     * Plays a move in the game by finding the possible {@link Link} objects that can be created from the specified {@link Cell}
     * and either creating a single link if there is only one possibility or setting the possible links as selectable
     * if there are multiple possibilities. If a selection process is already in progress, the method will attempt to find
     * the {@link Link} object corresponding to the selected {@link Cell} and create a single link using it.
     *
     * @param i The row index of the cell.
     * @param j The column index of the cell.
     */
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


    /**
     * Removes the ability to select cells in the grid by setting the {@code canBeSelected} field of all cells to false.
     */
    public void removeSelection(){
        for(int i = 0; i < HEIGHT; i++){
            for (int j = 0; j<WIDTH;j++){
                this.matrix[i][j].setCanBeSelected(false);
            }
        }
    }

    /**
     * Sets the specified {@link Link} as the main link for the {@link Cell} objects it connects and adds it to the
     * {@code movesHistory} list. Also sets the value of the root cell of the link to the link type.
     *
     * @param link The link to set as the main link for the connected cells.
     */
    public void setSingleLink(Link l){
        int i = l.getRoot().getI();
        int j = l.getRoot().getJ();
        for (Cell c: l.getNodes()){
            c.link(l.getLinkType());
        }
        this.matrix[i][j].setLink(l);
        this.matrix[i][j].setValue(1);
        this.movesHistory.add(l);

    }

    /**
     * Finds the {@link Link} object corresponding to the specified {@link Cell} object from the list of possible moves.
     *
     * @param cell The cell to find the corresponding link for.
     * @return The {@link Link} object corresponding to the specified cell.
     */
    public Link getLinkFromSelectedcell(Cell c){
        for(Link link: this.possibleMoves){
            if (c==link.getFirstNode() || c==link.getLastNode()){
                return link;
            }
        }
        return null;

    }

    /**
     * Sets the cells involved in the possible moves for the current turn as selectable by setting their
     * {@code canBeSelected} field to true.
     */
    public void setSelectionCells(){
        for(Link move: possibleMoves){
            if(move.getFirstNode() == move.getRoot()){
                move.getLastNode().setCanBeSelected(true);
            }else{
                move.getFirstNode().setCanBeSelected(true);
            }
        }
    }

    /**
     * Finds the possible {@link Link} objects that can be created from the specified {@link Cell} object.
     *
     * @param i The row index of the cell.
     * @param j The column index of the cell.
     * @return A list of possible {@link Link} objects that can be created from the specified cell.
     */
    public abstract List<Link> canLink(int line, int column);

    protected abstract List<Link> horizontalJoin(int line, int column);
    protected abstract List<Link> canJoinVertically(int line, int column);
    protected abstract List<Link> canJoinSecondDiagonal(int line, int column);
    protected abstract List<Link> canJoinFirstDiagonal(int line, int column);
}
