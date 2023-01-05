/**
 * Class representing a move in the Morpion Solitaire game.
 *
 * A move consists of two positions (A and B) and a boolean value indicating
 * whether the move is valid.
 *
 * @author  Adnan Mathuschan
 * @version 1.0
 * @since   2023/01/05
 */

package com.example.morpionsolitaire.models;

import javafx.geometry.Pos;

public class Move {
    private boolean canMakeMove;
    private Position positionA;
    private Position positionB;

    /**
     * Class representing a move in the Morpion Solitaire game.
     *
     * A move consists of two positions (A and B) and a boolean value indicating
     * whether the move is valid.
     */
    public Move(boolean _canMove, Position a, Position b){
        canMakeMove = _canMove;
        positionA = a;
        positionB = b;
    }

    /**
     * Returns a boolean value indicating whether the move can be made.
     * @return true if the move is valid, false otherwise.
     */

    public boolean canMakeMove(){
        return canMakeMove;
    }

    /**
     * Returns position A of the move.
     * @return the position A of the move.
     */
    public Position getPositionA(){
        return positionA;
    }

    /**
     * Returns position B of the move.
     * @return the position B of the move.
     */
    public  Position getPositionB(){
        return positionB;
    }
}
