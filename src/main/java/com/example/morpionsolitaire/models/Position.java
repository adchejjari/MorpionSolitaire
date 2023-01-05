/**
 * The `Position` class represents a position on a grid, specified by a line and column number.
 *
 * @author  Adnan Mathuschan
 * @version 1.0
 * @since   2023/01/05
 */

package com.example.morpionsolitaire.models;

public class Position {
    private int line;
    private int column;

    /**
     * Constructs a new `Position` object with the specified line and column numbers.
     *
     * @param i the line number of the position
     * @param j the column number of the position
     */
    public Position(int i, int j){
        this.line = i;
        this.column = j;
    }

    /**
     * Returns the line number of the position.
     *
     * @return the line number of the position
     */
    public int getLine(){
        return line;
    }

    /**
     * Returns the column number of the position.
     *
     * @return the column number of the position
     */
    public int getColumn(){
        return column;
    }
}
